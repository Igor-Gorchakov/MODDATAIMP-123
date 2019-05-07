package coordinator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueuedBlockingCoordinator implements BlockingCoordinator {

    private static final Object STUB_ITEM = new Object();
    private BlockingQueue<Object> blockingQueue = null;

    public QueuedBlockingCoordinator(int capacity) {
        this.blockingQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void acceptLock() {
        try {
            this.blockingQueue.put(STUB_ITEM);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void acceptUnlock() {
        try {
            this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
