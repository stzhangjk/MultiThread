import java.io.IOException;
import java.io.PipedInputStream;
import java.util.Arrays;

/**
 * 读线程
 */
public class ThreadRead extends Thread{
    private PipedInputStream in;

    public ThreadRead(PipedInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[20];
            int len = in.read(buffer);
            while(len != -1){
                System.out.println(new String(buffer,0,len));
                Arrays.fill(buffer, (byte) 0);
                len = in.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
