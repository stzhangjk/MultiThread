/**
 * Created by Grady on 2016.8.13.
 */
public class Main {
    public static void main(String[] args){
        final DBTools tools = new DBTools();
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tools.backupA();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    tools.backupB();
                }
            }).start();
        }
    }
}
