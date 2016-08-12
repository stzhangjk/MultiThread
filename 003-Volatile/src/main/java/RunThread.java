/**
 * Created by Grady on 2016.7.26.
 */
public class RunThread  extends Thread{

    /**volatile,强制访问公共堆栈中的值*/
    private volatile boolean isRunnning = true;

    public boolean isRunnning() {
        return isRunnning;
    }

    public void setRunnning(boolean runnning) {
        isRunnning = runnning;
    }

    @Override
    public void run() {
        System.out.println("进入循环");
        while(isRunnning){

        }
        System.out.println("退出循环");
    }
}
