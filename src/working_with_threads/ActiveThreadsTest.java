package working_with_threads;

public class ActiveThreadsTest {
    public static void main(String[] args) {
        var thr = new Thread(()->{
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }, "Thread for test");
        thr.start();
        int x = Thread.activeCount();
        Thread[] threads = new Thread[x];
        Thread.enumerate(threads);
        System.out.println(x);
        for (Thread thread : threads) {
            System.out.println(thread);
        }
    }
}
