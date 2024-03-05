package working_with_threads;

public class Threading {
    public static void main(String[] args) {
        // Example 1
//        MyTask myTask = new MyTask();
//        myTask.start();
//        MyRunnable myRunnable = new MyRunnable();
//        Thread thread = new Thread(myRunnable);
//        thread.start();
//        myRunnable.run();


        // Example 2
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
        // F.E -> 1
//        Thread thread1 = new Thread(runnable);
//        thread1.start();
        //F.E -> 2
//        new Thread(runnable).start(); //anonym object
//        System.out.println("Say Hello");


        new Thread(()->{
            for (int i = 1; i <= 15; i++){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"-> "+i);
            }
        },"MyThread").start();
        System.out.println("Say Hello");
    }
}

class MyTask extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 15; i++){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"-> "+i);
        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 15; i++){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"-> "+i);
        }
    }
}