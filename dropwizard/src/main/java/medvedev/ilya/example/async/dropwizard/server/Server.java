package medvedev.ilya.example.async.dropwizard.server;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import medvedev.ilya.example.async.dropwizard.controller.EchoResource;
import medvedev.ilya.example.async.echo.EchoService;

public class Server extends Application<Configuration> {
    private final EchoService echoService;

    public Server(final EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        final EchoResource echoResource = new EchoResource(echoService);

        environment.jersey()
                .register(echoResource);
    }
}
