package way3;

/**
 * 双重锁
 */
public class Singleton {


    private volatile static Singleton INSTANCE;

    private Singleton(){

    }

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
}
