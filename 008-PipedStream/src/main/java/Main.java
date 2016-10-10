import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Grady on 2016.8.13.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        /*创建管道输入端*/
        PipedInputStream in = new PipedInputStream();
        /*创建管道输出端*/
        PipedOutputStream out = new PipedOutputStream();
        /*绑定输入输出*/
        out.connect(in);

        ThreadWrite write = new ThreadWrite(out);
        ThreadRead read = new ThreadRead(in);

        /*启动线程*/
        write.start();
        read.start();
    }
}
