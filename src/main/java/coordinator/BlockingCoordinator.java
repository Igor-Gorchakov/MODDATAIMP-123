package coordinator;

public interface BlockingCoordinator {

    void lock();

    void unlock();
}
