package fieldvisibility;

public class WithVolatile {
    public static void main(String[] args) throws InterruptedException {
        var myRunnable = new MyRunnable();
        var th1 = new Thread(myRunnable);
        var th2 = new Thread(myRunnable);
        th1.start();
        th2.start();
        Thread.sleep(2000);
        myRunnable.stop();
    }
}

class MyRunnable implements Runnable{
    private volatile boolean active;
    @Override
    public void run() {
        active = true;
        while (active){
        }
        System.out.println(Thread.currentThread()+"::::::::::::::Finished !!!");
    }

    public void stop(){
        active = false;
    }
}
