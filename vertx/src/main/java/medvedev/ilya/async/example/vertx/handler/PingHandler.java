package medvedev.ilya.async.example.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import medvedev.ilya.async.example.repository.Repository;

public class PingHandler implements Handler<RoutingContext> {
    private static final int BAD_GATEWAY = 502;

    private final Repository repository;

    public PingHandler(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(final RoutingContext event) {
        repository.getResponse()
                .thenAccept(response -> event.response()
                        .end(response))
                .exceptionally(exception -> {
                    event.response()
                            .setStatusCode(BAD_GATEWAY)
                            .end();

                    throw new RuntimeException(exception);
                });
    }
}
