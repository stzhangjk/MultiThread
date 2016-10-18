/**
 * Created by Grady on 2016.8.13.
 */
public class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                //Main.tl.set("第" + i + "个值");
                System.out.println("A:" + Main.tl.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
