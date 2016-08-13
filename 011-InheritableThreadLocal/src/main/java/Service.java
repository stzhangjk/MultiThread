/**
 * Created by Grady on 2016.8.13.
 */
public class Service implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + Main.itl.get());
    }
}
