/**
 * Created by Grady on 2016.7.26.
 */
public class Main {
    public static void main(String[] args){
        try{
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(5000);
            thread.setRunnning(false);
            System.out.println("已设置为false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
