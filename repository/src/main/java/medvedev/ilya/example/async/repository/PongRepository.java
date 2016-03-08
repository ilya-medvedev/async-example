package medvedev.ilya.example.async.repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class PongRepository implements Repository {
    private static final long TIMEOUT = 5000;
    private static final String RESPONSE = "pong";

    private final Executor executor;

    public PongRepository(final Executor executor) {
        this.executor = executor;
    }

    @Override
    public CompletableFuture<String> getResponse() {
        return CompletableFuture.supplyAsync(PongRepository::responseSupplier, executor);
    }

    private static String responseSupplier() {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return RESPONSE;
    }
}
