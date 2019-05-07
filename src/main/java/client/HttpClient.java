package client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface HttpClient {

    void send(Object request, Handler<AsyncResult> handler);
}
