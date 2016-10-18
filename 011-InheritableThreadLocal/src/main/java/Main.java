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
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
        }).start();
        Main.itl.set(Thread.currentThread().getName() + "设置新的值");
        System.out.println(Thread.currentThread().getName() +"获得"+ Main.itl.get());
    }
}
