package condition_objects;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                System.out.println("Buffer is full. Producer is waiting...");
                notFull.await();
            }

            buffer.offer(item);
            System.out.println("Produced: " + item);
            notEmpty.signal(); // Signal the consumer that the buffer is not empty
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty. Consumer is waiting...");
                notEmpty.await();
            }

            int item = buffer.poll();
            System.out.println("Consumed: " + item);
            notFull.signal(); // Signal the producer that the buffer is not full
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(100); // Simulate some processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.consume();
                Thread.sleep(200); // Simulate some processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

