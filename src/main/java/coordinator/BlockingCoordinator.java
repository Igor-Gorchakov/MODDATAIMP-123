package coordinator;

public interface BlockingCoordinator {

    void acceptLock();

    void acceptUnlock();
}
