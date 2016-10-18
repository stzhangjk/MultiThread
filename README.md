#Java多线程笔记

----------

##002-停止线程
>1. `interrupted()`，测试当前线程（运行代码的线程）是否已经中断。
1. `isInterrupted()`，测试线程是否已经中断。


##003-volatile关键字
###1、作用
强制从公共堆栈中取得变量的值，而不是从线程私有数据栈中取值。

>注意事项：
>**只能用于变量，不可用于方法**

###2、`volatile`与`synchronized`关键字
1. `volatile`不会发生阻塞，而`synchronized`会发生阻塞
2. `volatile`只保证数据的可见性，不保证原子性；
   `synchronized`可保证原子性，也可间接保证可见性。因为会将私有内存和公共内存的数据做同步。

###3、`volatile`的非原子性
假如对一个volatile变量count做`count++`操作，实际上具体的处理过程如下图所示：
![](http://i.imgur.com/phUr4WZ.jpg)
>而用了`volatile`关键字只保证JVM从主内存加载到线程工作内存的值是最新的，也就是说，可能线程A和线程B在read和load阶段读到的值是一样的值，到时更新到公共内存的时候将会发生错误。
>解决办法是使用`synchronized`关键字或者使用原子类`AtomicInteger`。
>`AtomicInteger`可以在不加锁的情况下做到原子性从而解决线程安全问题。

##005-等待通知机制
>实现：`wait()`和`notify()`方法

###1、`wait()`与`wait(long)`注意事项
1. `wait()`调用之前，线程必须获得该对象的对象级别锁，即只能在`synchronized`中调用。否则将抛出`IllegalMonitorStateException`异常。
2. `wait()`执行之后，线程释放锁。
3. 在`wait()`返回前，线程与其他线程竞争重新获得锁。
4. `wait(long)`方法的效果是等待某一时间内是否有线程对锁进行唤醒，如果超过指定时间则自动唤醒。当然在指定时间内也可以由其他线程唤醒，这一点和`wait()`一样。

###2、`notify()`与`notifyAll()`注意事项
1. `notify()`调用之前也必须获得对象级别锁，否则也抛出`IllegalMonitorStateException`异常。
2. 如果有多个线程等待，则由**线程规划器**随机挑选一个呈wait状态的线程发出notify通知，并使它获得对象锁。
3. `notify()`执行之后，并不会马上释放锁，要等到随处了`synchronized`代码块后才释放锁。
4. `notifyAll()`效果是唤醒某个锁下所有等待的线程。

###3、应用实例——生产者消费者模式
1. 目标效果：单消费者（Customer）单生产者（Productor）模式，（多生产者多消费者可能会引发死锁问题，后面解释）
2. 实现思路：利用`synchronized`关键字确保生产(put)/消费（get）操作的互斥性，即将它们放在`synchronized`代码块中，并且注意两者要争得同一个锁，而不是各自的Customer对象的锁或者Productor对象的锁，也就是说不能简单的在方法上加`synchronized`，这里传入一个字符串对象来作为锁。

**生产者代码：**
```
/**
 * 生产者
 */
public class Producer {

    /**同步锁*/
    private String lock;

    /**产品集合*/
    private List<Product> ps;

    /**
     * 构造函数
     * @param lock 由上层传入锁对象
     * @param ps 生产的目的地，也是消费者的消费来源
     */
    public Producer(String lock, List<Product> ps) {
        this.lock = lock;
        this.ps = ps;
    }

    /**
     * 生产
     */
    public void put() {
        try {
            synchronized (lock) {
                while (true) {
                    if (ps.size() != 0) {
                        /*等待，阻塞*/
                        lock.wait();
                    } else {
                        Thread.sleep(1000);
                        ps.add(new Product(String.valueOf(System.currentTimeMillis())));

                        /*唤醒消费者线程*/
                        lock.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

**消费者代码：**
```
/**
 * 消费者
 */
public class Customer {
    
    /**同步锁*/
    private String lock;
    /**产品集合*/
    private List<Product> ps;
    
    public Customer(String lock, List<Product> ps) {
        this.lock = lock;
        this.ps = ps;
    }

    /**
     * 消费
     */
    public void get() {
        try {
            synchronized (lock){
                while (true) {
                    if (ps.size() == 0) {
                        lock.wait();
                    }
                    System.out.println(ps.get(0).getData());
                    ps.remove(0);
                    lock.notify();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

**产品对象代码：**
```
/**
 * 产品对象
 */
public class Product {
    private String data;

    public Product(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
```
**main：**
```
public static void main(String[] args){

    final List<Product> ps = new ArrayList<Product>(1);
	/*充当锁对象*/
    String lock = new String("lock");

    final Producer producer = new Producer(lock,ps);
    final Customer customer = new Customer(lock,ps);

    /*启动一个生产者线程*/
    new Thread(() -> producer.put()).start();
    /*启动一个消费者线程*/
    new Thread(() -> customer.get()).start();
}
```
如果企图按照如上生产者消费者的代码实现多生产者多消费者，则可能出现“假死”的情况，即死锁问题（互相等待对方释放锁，结果都得不到锁，永久等待），原因是这些生产者和消费者竞争的是同一个锁，如果某一时刻，生产者执行生成操作后唤醒的是生产者线程，那么新唤醒的线程判定不需要生产而传入**`WAITING`**状态，这样所有的线程都处于**`WAITING`**状态了，形成死锁情况。
解决方法是把**`notify()`**换成**`notifyAll()`**，这样就不光通知同类线程，也包括了异类线程。

##008-利用管道流PipedStream进行线程通信
1. 原理：通过操作核心类`PipedInputStream`/`PipedReader`和`PipedOutputStream`/`	PipedWriter`,使得一个线程从管道一端写入，另一线程从另一端读取，实现线程之间的通信。
2. 使用步骤：
	1) 分别new出`PipedInputStream`/`PipedReader`和`PipedOutputStream`/`	PipedWriter`，并传入要通信的线程中供使用。
	2）调用`connect()`方法绑定输入端和输出端。
	3）像其他流一样使用……
3. 实例
main：
```
public static void main(String[] args) throws IOException, InterruptedException {

    /*创建管道输入端*/
    PipedInputStream in = new PipedInputStream();
    /*创建管道输出端*/
    PipedOutputStream out = new PipedOutputStream();
    /*绑定输入输出*/
    out.connect(in);

    ThreadWrite write = new ThreadWrite(out);
    ThreadRead read = new ThreadRead(in);

    /*启动线程*/
    write.start();
    read.start();
}
```
ThreadWrite：
```
/**
 * 写线程
 */
public class ThreadWrite extends Thread {

    private PipedOutputStream out;

    public ThreadWrite(PipedOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 300; i++) {
                out.write(new String("写:" + i).getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```
ThreadRead：
```
/**
 * 读线程
 */
public class ThreadRead extends Thread{
    private PipedInputStream in;

    public ThreadRead(PipedInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[20];
            int len = in.read(buffer);
            while(len != -1){
                System.out.println(new String(buffer,0,len));
                Arrays.fill(buffer, (byte) 0);
                len = in.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##010-线程私有数据——``ThreadLocal``类
###1. 使用方法
```	
ThreadLocal<T> tl = new ThreadLocal<>();//创建对象
tl.get();//获取值
tl.set(value);//设置值
```

###2. 注意事项
1. 如果不`set()`一个值，调用`get()`将返回null；
2. 可重写`initialValue()`方法设置初始值，该方法在线程第一次调用`get()`时调用；
```
 @Override
protected T initialValue() {
    //可修改初始值不为null
    return super.initialValue();
}
```
>每个线程都会各自执行这个方法，意味着如果返回`new Date()`，那么每个线程都获得一个**不同**的`Date`对象。

##011-`InheritableThreadLocal`
可以让子线程从父线程继承下来值。
###注意事项
- 重写`initialValue()`和`childValue(T parentValue)`方法设置初始值。
- 如果不重写`childValue(T parentValue)`，那么不改变`parentValue`，其默认实现就是直接返回`parentValue`。
```
protected T childValue(T parentValue) {
        return parentValue;
}
```
- `childValue()`方法在**创建子线程后、启动子线程前**于**父线程中**调用，这里的父子概念是相对的；
- 如果子线程`get()`之前，父线程`set()`一个新的值，那么子线程获得的**父线程的initialValue初始值**会是**旧**的值，子线程获取的值满足`childValue()`设置的规则，即修饰；
- 如果父线程`set()`了新值再**创建**子线程，那么新子线程获得的**父线程的initialValue初始值**是**新**的值；
- 孙线程会获得**父线程的initialValue初始值**+**父线程的childValue修饰值**+**子线程的childValue修饰值**，以此类推；

###实例1——测试父线程set()新值的效果
main:
```
public class Main {
    public static InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return " 父线程设置的初始值";
        }

        @Override
        protected String childValue(String parentValue) {
            System.out.println(Thread.currentThread().getName() + "执行了childValue");
            return parentValue + "  父线程修饰的值";
        }
    };

    public static void main(String[] args){

        Runnable service = new Service();
        service.run();

        new Thread(service).start();
        itl.set(" 新的值");
        new Thread(service).start();

    }
}

```
Service:
```
public class Service implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + Main.itl.get());
    }
}
```

输出：
```
main 父线程设置的初始值
main执行了childValue
main执行了childValue
Thread-0 父线程设置的初始值  父线程修饰的值
Thread-1 新的值  父线程修饰的值
```
###实例2——测试孙线程获取到的值
main:
```
public class Main {
    public static InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName() + "设置的初始值";
        }

        @Override
        protected String childValue(String parentValue) {
            System.out.println(Thread.currentThread().getName() + "执行了childValue");
            return parentValue + Thread.currentThread().getName() + "修饰的值";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
        new Thread(new Service()).start();
    }
}

```
Service:
```
public class Service implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"获得" + Main.itl.get());

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
        }).start();
    }
}
```
输出：
```
main获得main设置的初始值
main执行了childValue
Thread-0获得main设置的初始值main修饰的值
Thread-0执行了childValue
Thread-1获得main设置的初始值main修饰的值Thread-0修饰的值
```

##016-懒汉式单例

###1、在方法上加`synchronized`修饰
```
public class Singleton {

    private static Singleton INSTANCE;

    private Singleton(){

    }

    /**
     * 设置同步方法，整个方法被上锁
     * 缺点效率低
     * @return 单例对象
     */
    public synchronized static Singleton getInstance(){
        try{
            if(INSTANCE == null){
                //模拟准备工作
                Thread.sleep(3000);
                INSTANCE = new Singleton();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

```
###*2、同步代码块
```
/**
 * 等同于在方法加sync关键字，因为都是获取class对象的锁。
 */
public static Singleton getInstance(){
    try{
        synchronized (Singleton.class){
            if(INSTANCE == null){
                /*模拟构造对象*/
                Thread.sleep(3000);
                INSTANCE = new Singleton();
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return INSTANCE;
}
```
###3、双重锁DCL
```
/**
 * 如果new之前不加null判断，非第一个进入临界区的线程还是会执行new语句，造成多个对象被创建。
 */
public static Singleton getInstance(){
    try{
        if(INSTANCE == null){
            /*模拟构造对象*/
            Thread.sleep(3000);
            synchronized (Singleton.class){
                System.out.println(1);
                if(INSTANCE == null){
                    INSTANCE = new Singleton();
                }
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return INSTANCE;
}
```



#####待整理

###同步与锁
>1. `sleep()`不释放锁。
1. `join()`会释放锁。
1. `join(long)`内部使用`wait(long)`实现。