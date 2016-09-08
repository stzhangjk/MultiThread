/**
 * Created by STZHANGJK on 2016/9/8.
 */
public class Customer extends Thread {
    private MyService service;

    public Customer(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for(int i=0;i<15;i++){
            service.get();
        }
    }
}
