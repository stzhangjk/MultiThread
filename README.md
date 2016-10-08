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
>而JVM只保证从主内存加载到线程工作内存的值是最新的，也就是说，可能线程A和线程B在read和load阶段读到的值是一样的值，到时更新到公共内存的时候将会发生错误。
>解决办法是使用`synchronized`关键字或者使用原子类`AtomicInteger`。
>`AtomicInteger`可以在不加锁的情况下做到原子性从而解决线程安全问题。

##005-等待通知机制
>实现：`wait()`和`notify()`方法

###1、`wait()`注意事项
1. `wait()`调用之前，线程必须获得该对象的对象级别锁，即只能在`synchronized`中调用。否则将抛出`IllegalMonitorStateException`异常。
2. `wait()`执行之后，线程释放锁。
3. 在`wait()`返回前，线程与其他线程竞争重新获得锁。

###2、`notify()`注意事项
1. `notify()`调用之前也必须获得对象级别锁，否则也抛出`IllegalMonitorStateException`异常。
2. 如果有多个线程等待，则由**线程规划器**随机挑选一个呈wait状态的线程发出notify通知，并使它获得对象锁。
3. `notify()`执行之后，并不会马上释放锁，要等到随处了`synchronized`代码块后才释放锁。


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