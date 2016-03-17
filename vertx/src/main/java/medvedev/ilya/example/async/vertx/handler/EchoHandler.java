package medvedev.ilya.example.async.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;
import medvedev.ilya.example.async.vertx.constant.ContentType;

public class EchoHandler implements Handler<RoutingContext> {
    private static final int BAD_REQUEST = 400;

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

        final String requestString = request.getRequest();
        final Response response = new Response(requestString);
        final String responseString = Json.encode(response);

        event.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                .end(responseString);
    }
}
