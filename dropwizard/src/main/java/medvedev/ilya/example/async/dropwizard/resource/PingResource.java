package medvedev.ilya.example.async.dropwizard.resource;

import medvedev.ilya.example.async.ping.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("ping")
public class PingResource {
    private final Repository repository;

    public PingResource(final Repository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void ping(@Suspended final AsyncResponse response) {
        repository.getResponse()
                .thenAccept(response::resume)
                .exceptionally(exception -> {
                    response.resume(exception);

                    throw new RuntimeException(exception);
                });
    }
}
