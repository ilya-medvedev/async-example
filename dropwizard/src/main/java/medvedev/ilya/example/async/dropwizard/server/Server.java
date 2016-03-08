package medvedev.ilya.example.async.dropwizard.server;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import medvedev.ilya.example.async.dropwizard.resource.PingResource;
import medvedev.ilya.example.async.repository.Repository;

public class Server extends Application<Configuration> {
    private final Repository repository;

    public Server(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        final PingResource pingResource = new PingResource(repository);

        environment.jersey()
                .register(pingResource);
    }
}
