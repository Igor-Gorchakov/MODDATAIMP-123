import coordinator.BlockingCoordinator;
import coordinator.QueuedBlockingCoordinator;
import reader.SourceReader;
import reader.SourceReaderImpl;
import client.HttpClient;
import client.HttpClientImpl;

public class Main {
    private static final int QUEUE_CAPACITY = 10;
    private static final SourceReader SOURCE_READER = new SourceReaderImpl();
    private static final HttpClient SENDER = new HttpClientImpl();

    public static void main(String[] args) {
        processFile();
    }

    private static void processFile() {
        BlockingCoordinator coordinator = new QueuedBlockingCoordinator(QUEUE_CAPACITY);
        while (SOURCE_READER.hasNext()) {
            coordinator.lock();
            String record = SOURCE_READER.next();
            System.out.println("Sending record: " + record);
            SENDER.send(record, ar -> {
                if (ar.failed()) {
                    System.out.println("Sending failed for record: " + ar.result());
                } else {
                    System.out.println("Sending succeeded record:" + ar.result());
                    coordinator.unlock();
                }
            });
        }
    }
}
