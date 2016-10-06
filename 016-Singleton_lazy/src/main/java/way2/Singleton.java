package way2;


/**
 * Created by STZHANGJK on 2016.10.6.
 */
public class Singleton {

    private static Singleton INSTANCE;

    private Singleton(){

    }

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
}
