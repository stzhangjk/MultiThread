public class Main {
    public static InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName() + "设置的初始值";
        }

        @Override
        protected String childValue(String parentValue) {
            System.out.println(Thread.currentThread().getName() + "执行了childValue");
            return parentValue + Thread.currentThread().getName() + "修饰的值";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
        new Thread(new Service()).start();
    }
}
