package coordinator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueuedBlockingCoordinator implements BlockingCoordinator {

    private static final Object QUEUE_STUB = new Object();
    private BlockingQueue<Object> blockingQueue = null;

    public QueuedBlockingCoordinator(int capacity) {
        this.blockingQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void countUp() {
        try {
            this.blockingQueue.put(QUEUE_STUB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void countDown() {
        try {
            this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}