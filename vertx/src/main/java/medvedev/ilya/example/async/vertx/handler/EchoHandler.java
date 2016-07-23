package medvedev.ilya.example.async.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import medvedev.ilya.example.async.echo.EchoService;
import medvedev.ilya.example.async.echo.model.Request;

public class EchoHandler implements Handler<RoutingContext> {
    private static final String APPLICATION_JSON = "application/json";

    private static final int BAD_REQUEST = 400;
    private static final int BAD_GATEWAY = 502;

    private final EchoService echoService;

    public EchoHandler(final EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public void handle(final RoutingContext event) {
        final String body = event.getBodyAsString();
        final Request request;
        try {
            request = Json.decodeValue(body, Request.class);
        } catch (DecodeException e) {
            event.response()
                    .setStatusCode(BAD_REQUEST)
                    .end();

            return;
        }

        echoService.echo(request)
                .thenAccept(response -> {
                    final String responseString = Json.encode(response);

                    event.response()
                            .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
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
