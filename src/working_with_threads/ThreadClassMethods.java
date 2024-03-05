package working_with_threads;

public class ThreadClassMethods{
    public static void main(String[] args) throws InterruptedException{
        Runnable runnable = ()->{
            for (int i = 1; i <= 15; i++){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"-> "+i);
            }
        };
        var thread1 = new Thread(runnable,"Thread One");  //statud of thread ---> New
        var thread2 = new Thread(runnable, "Thread Two");
        thread1.start();  // status of thread ---> Runnable
        thread1.join(1000); // after completing first take second thread works
        thread2.start();     // status of thread ---> Waiting
        System.out.println("Say Hello");
    }

}
