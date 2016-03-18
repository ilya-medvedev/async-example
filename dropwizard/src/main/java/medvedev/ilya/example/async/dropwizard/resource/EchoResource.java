package medvedev.ilya.example.async.dropwizard.resource;

import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("echo")
public class EchoResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(final Request request) {
        final String response = request.getRequest();

        return new Response(response);
    }
}
