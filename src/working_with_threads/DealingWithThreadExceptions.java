package working_with_threads;

public class DealingWithThreadExceptions {
    public static void main(String[] args) {
        MyThread threadOne = new MyThread("Thread One");
        MyThread threadTwo = new MyThread("Thread Two");
        MyThread threadThree = new MyThread("Thread Three");
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}

class MyThread extends Thread{
    private final String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++){
            if (name.equalsIgnoreCase("thread one") && i == 2) throw new RuntimeException("Exception for testing");
            else System.out.println(this);
        }
    }


    @Override
    public String toString(){
        return name + "->";
    }
}
