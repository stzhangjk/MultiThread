import org.junit.Test;

/**
 * Created by STZHANGJK on 2016/9/8.
 */
public class Test013 {
    @Test
    public void test(){
        MyService service = new MyService();
        Thread a = new Producer(service);
        Thread b = new Customer(service);

        a.start();
        b.start();
    }
}
