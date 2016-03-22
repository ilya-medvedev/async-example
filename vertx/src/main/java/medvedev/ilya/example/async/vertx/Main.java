package medvedev.ilya.example.async.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.impl.FileResolver;
import medvedev.ilya.example.async.ping.Repository;
import medvedev.ilya.example.async.ping.PongRepository;
import medvedev.ilya.example.async.vertx.verticle.MainVerticle;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final String VERTX_CACHE_DIR = "vertx-cache";
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(final String[] args) {
        final String cacheDir;
        try {
            cacheDir = Files.createTempDirectory(VERTX_CACHE_DIR)
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setProperty(FileResolver.CACHE_DIR_BASE_PROP_NAME, cacheDir);

        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        final Repository repository = new PongRepository(executor);
        final Verticle mainVerticle = new MainVerticle(repository);

        Vertx.vertx()
                .deployVerticle(mainVerticle);
    }
}
