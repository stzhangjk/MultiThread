import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Grady on 2016.7.26.
 */
public class AddCountThread extends Thread {
    /**原子类*/
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            System.out.println(count.incrementAndGet());
        }
    }
}
