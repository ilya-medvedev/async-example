package medvedev.ilya.example.async.dropwizard;

import medvedev.ilya.example.async.dropwizard.server.Server;
import medvedev.ilya.example.async.ping.PongRepository;
import medvedev.ilya.example.async.ping.Repository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(final String[] args) throws Exception {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        final Repository repository = new PongRepository(executor);

        new Server(repository)
                .run(args);
    }
}
