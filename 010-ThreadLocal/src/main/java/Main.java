/**
 * Created by Grady on 2016.8.13.
 */
public class Main {
    public static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new ThreadA().start();
        new ThreadB().start();

        for(int i=0;i<100;i++){
            Main.tl.set("第" + i + "个值");
            System.out.println("Main:" + Main.tl.get());
            Thread.sleep(100);
        }
    }
}
