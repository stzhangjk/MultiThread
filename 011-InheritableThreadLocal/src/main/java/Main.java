/**
 * Created by Grady on 2016.8.13.
 */
public class Main {
    public static InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "父线程设置的初始值";
        }

        @Override
        protected String childValue(String parentValue) {
            return parentValue + "  父线程修饰的值";
        }
    };

    public static void main(String[] args){

        Runnable service = new Service();

        service.run();
        new Thread(service).start();
        new Thread(service).start();

    }
}
