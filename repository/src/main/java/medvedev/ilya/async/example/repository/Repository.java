package medvedev.ilya.async.example.repository;

import java.util.concurrent.CompletableFuture;

public interface Repository {
    CompletableFuture<String> getResponse();
}
