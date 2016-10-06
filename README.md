#Java多线程笔记

----------

##002-停止线程
>1. `interrupted()`，测试当前线程（运行代码的线程）是否已经中断。
1. `isInterrupted()`，测试线程是否已经中断。

###同步与锁
>1. `sleep()`不释放锁。
1. `join()`会释放锁。
1. `join(long)`内部使用`wait(long)`实现。

##016-懒汉式单例

###1、在方法上加synchronized修饰
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
