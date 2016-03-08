package medvedev.ilya.example.async.repository;

import java.util.concurrent.CompletableFuture;

public interface Repository {
    CompletableFuture<String> getResponse();
}
