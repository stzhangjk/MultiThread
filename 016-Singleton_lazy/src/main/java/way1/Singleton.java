package way1;

/**
 * Created by STZHANGJK on 2016.10.6.
 */
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
