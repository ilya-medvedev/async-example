package medvedev.ilya.example.async.echo;

import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;

import java.util.concurrent.CompletableFuture;

public interface EchoService {
    CompletableFuture<Response> echo(final Request request);
}
