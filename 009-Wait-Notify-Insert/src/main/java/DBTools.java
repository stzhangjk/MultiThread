/**
 * Created by Grady on 2016.8.13.
 */
public class DBTools {
    private volatile boolean prevIsA = false;

    public synchronized void backupA(){
        try{
            while(prevIsA){
                wait();
            }
            for(int i=0;i< 5;i++){
                System.out.println("backup A");
            }
            prevIsA = true;
            notifyAll();
        }catch (InterruptedException e){

        }
    }

    public synchronized void backupB(){
        try{
            while(!prevIsA){
                wait();
            }
            for(int i=0;i< 5;i++){
                System.out.println("backup  B");
            }
            prevIsA = false;
            notifyAll();
        }catch (InterruptedException e){

        }
    }
}
