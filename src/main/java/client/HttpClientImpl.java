package client;


import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpClientImpl implements HttpClient {

    private static final int THREAD_POOL_SIZE = 20;       // threads number in event loop
    private static final int TASK_EXECUTION_DURATION = 5; // in seconds
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Override
    public void send(Object request, Handler<AsyncResult> handler) {
        this.executor.submit(new Task(handler));
    }

    private class Task implements Runnable {
        private Handler<AsyncResult> handler;

        public Task(Handler<AsyncResult> handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(TASK_EXECUTION_DURATION);
                this.handler.handle(Future.succeededFuture());
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.handler.handle(Future.failedFuture(e));
            }
        }
    }
}
