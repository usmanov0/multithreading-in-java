package working_with_threads;

public class ThreadInterruptException {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++){
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        };
        Thread thread = new Thread(runnable, "Thread 1");
        thread.start();
        System.out.println("Say Hello");
        thread.interrupt();
    }
}
