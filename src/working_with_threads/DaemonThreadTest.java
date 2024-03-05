package working_with_threads;

public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            try {
                for (int i = 1; i <= 40; i++){
                    System.out.println("Active threads count: "+Thread.activeCount()+", value "+i);
                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        };
        var thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        System.out.println("Say Hello");
        Thread.sleep(210);
    }
}
