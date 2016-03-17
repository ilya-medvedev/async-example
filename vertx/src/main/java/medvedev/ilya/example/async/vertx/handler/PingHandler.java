package medvedev.ilya.example.async.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import medvedev.ilya.example.async.ping.Repository;
import medvedev.ilya.example.async.vertx.constant.ContentType;

public class PingHandler implements Handler<RoutingContext> {
    private static final int BAD_GATEWAY = 502;

    private final Repository repository;

    public PingHandler(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(final RoutingContext event) {
        repository.getResponse()
                .thenAccept(response -> {
                    final String responseString = Json.encode(response);

                    event.response()
                            .putHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                            .end(responseString);
                })
                .exceptionally(exception -> {
                    event.response()
                            .setStatusCode(BAD_GATEWAY)
                            .end();

                    throw new RuntimeException(exception);
                });
    }
}
