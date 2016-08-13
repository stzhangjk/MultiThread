import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Grady on 2016.8.13.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        ThreadWrite write = new ThreadWrite(out);
        ThreadRead read = new ThreadRead(in);
        out.connect(in);

        write.start();
        read.start();


    }
}
