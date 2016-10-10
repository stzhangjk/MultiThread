import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 写线程
 */
public class ThreadWrite extends Thread {

    private PipedOutputStream out;

    public ThreadWrite(PipedOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 300; i++) {
                out.write(new String("写:" + i).getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
