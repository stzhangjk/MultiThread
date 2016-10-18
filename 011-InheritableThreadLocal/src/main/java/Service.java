/**
 * Created by Grady on 2016.8.13.
 */
public class Service implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"获得" + Main.itl.get());

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
        }).start();
        Main.itl.set(Thread.currentThread().getName() + "设置新的值");

    }
}
