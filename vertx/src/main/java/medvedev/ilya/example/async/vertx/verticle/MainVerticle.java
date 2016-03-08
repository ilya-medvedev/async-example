package medvedev.ilya.example.async.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import medvedev.ilya.example.async.repository.Repository;
import medvedev.ilya.example.async.vertx.handler.PingHandler;

public class MainVerticle extends AbstractVerticle {
    private static final String PATH = "/ping";
    private static final int PORT = 8080;

    private final Repository repository;

    public MainVerticle(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void start() {
        final Router router = Router.router(vertx);

        final Handler<RoutingContext> handler = new PingHandler(repository);

        router.route(HttpMethod.GET, PATH)
                .handler(handler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
    }
}
