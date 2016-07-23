package medvedev.ilya.example.async.dropwizard.controller;

import medvedev.ilya.example.async.echo.EchoService;
import medvedev.ilya.example.async.echo.model.Request;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("echo")
public class EchoResource {
    private final EchoService echoService;

    public EchoResource(final EchoService echoService) {
        this.echoService = echoService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void echo(final Request request, @Suspended final AsyncResponse response) {
        echoService.echo(request)
                .thenAccept(response::resume)
                .exceptionally(exception -> {
                    response.resume(exception);

                    throw new RuntimeException(exception);
                });
    }
}
