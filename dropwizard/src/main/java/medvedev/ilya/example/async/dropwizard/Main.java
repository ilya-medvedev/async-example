package medvedev.ilya.example.async.dropwizard;

import medvedev.ilya.example.async.dropwizard.server.Server;
import medvedev.ilya.example.async.echo.EchoService;
import medvedev.ilya.example.async.echo.EchoServiceImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(final String[] args) throws Exception {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        final EchoService echoService = new EchoServiceImpl(executor);

        new Server(echoService)
                .run(args);
    }
}
