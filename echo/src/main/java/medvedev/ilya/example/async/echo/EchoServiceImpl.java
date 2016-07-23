package medvedev.ilya.example.async.echo;

import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public class EchoServiceImpl implements EchoService {
    private static final long TIMEOUT = 5000;

    private final Executor executor;

    public EchoServiceImpl(final Executor executor) {
        this.executor = executor;
    }

    @Override
    public CompletableFuture<Response> echo(final Request request) {
        final Supplier<Response> supplier = () -> {
            final String stringRequest = request.getRequest();

            try {
                Thread.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return new Response(stringRequest);
        };

        return CompletableFuture.supplyAsync(supplier, executor);
    }
}
