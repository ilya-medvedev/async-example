package medvedev.ilya.async.example.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import medvedev.ilya.async.example.repository.Repository;
import medvedev.ilya.async.example.repository.PongRepository;
import medvedev.ilya.async.example.vertx.verticle.MainVerticle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        final Repository repository = new PongRepository(executor);
        final Verticle mainVerticle = new MainVerticle(repository);

        Vertx.vertx()
                .deployVerticle(mainVerticle);
    }
}
