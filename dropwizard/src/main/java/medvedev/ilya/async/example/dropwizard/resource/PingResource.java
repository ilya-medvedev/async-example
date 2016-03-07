package medvedev.ilya.async.example.dropwizard.resource;

import medvedev.ilya.async.example.repository.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/ping")
public class PingResource {
    private final Repository repository;

    public PingResource(final Repository repository) {
        this.repository = repository;
    }

    @GET
    public void ping(@Suspended final AsyncResponse response) {
        repository.getResponse()
                .thenAccept(response::resume)
                .exceptionally(exception -> {
                    response.resume(exception);

                    throw new RuntimeException(exception);
                });
    }
}
