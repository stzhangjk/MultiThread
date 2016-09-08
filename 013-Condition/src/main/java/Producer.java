/**
 * Created by STZHANGJK on 2016/9/8.
 */
public class Producer extends Thread {
    private MyService service;

    public Producer(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for(int i=0;i<15;i++){
            service.set();
        }
    }
}
