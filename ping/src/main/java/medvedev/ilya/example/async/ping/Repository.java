package medvedev.ilya.example.async.ping;

import medvedev.ilya.example.async.ping.model.Response;

import java.util.concurrent.CompletableFuture;

public interface Repository {
    CompletableFuture<Response> getResponse();
}
