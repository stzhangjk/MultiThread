import org.junit.Test;
import service.ReadService;
import service.WriteService;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by STZHANGJK on 2016.9.12.
 */
public class Test014{

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 写写互斥
     */
    @Test
    public void testWriteWrite() throws InterruptedException {
        WriteService service = new WriteService(lock);
        Thread a = new Thread(service);
        Thread b = new Thread(service);

        a.start();
        b.start();

        a.join();
        b.join();
    }

    /**
     * 读写互斥
     */
    @Test
    public void testWriteRead() throws InterruptedException {
        WriteService write = new WriteService(lock);
        ReadService read = new ReadService(lock);

        Thread a = new Thread(write);
        Thread b = new Thread(read);

        a.start();
        b.start();

        a.join();
        b.join();
    }

    @Test
    public void testReadRead() throws InterruptedException {
        ReadService service = new ReadService(lock);
        Thread a = new Thread(service);
        Thread b = new Thread(service);

        a.start();
        b.start();

        a.join();
        b.join();
    }


}
