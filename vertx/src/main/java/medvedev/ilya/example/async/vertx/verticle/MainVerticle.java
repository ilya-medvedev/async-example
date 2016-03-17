package medvedev.ilya.example.async.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import medvedev.ilya.example.async.ping.Repository;
import medvedev.ilya.example.async.vertx.handler.EchoHandler;
import medvedev.ilya.example.async.vertx.handler.PingHandler;

import java.io.IOException;
import java.nio.file.Files;

public class MainVerticle extends AbstractVerticle {
    private static final String VERTX_UPLOAD_DIR = "vertx-uploads";

    private static final String PING = "/ping";
    private static final String ECHO = "/echo";

    private static final int PORT = 8080;

    private final Repository repository;

    public MainVerticle(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void start() {
        final Router router = Router.router(vertx);
        final Handler<RoutingContext> ping = new PingHandler(repository);

        router.route(HttpMethod.GET, PING)
                .handler(ping);

        final String fileUploadDir;
        try {
            fileUploadDir = Files.createTempDirectory(VERTX_UPLOAD_DIR)
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final Handler<RoutingContext> body = BodyHandler.create(fileUploadDir);
        final Handler<RoutingContext> echo = new EchoHandler();

        router.route(HttpMethod.POST, ECHO)
                .handler(body);

        router.route(HttpMethod.POST, ECHO)
                .handler(echo);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
    }
}
