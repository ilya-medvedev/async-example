package medvedev.ilya.example.async.ping.model;

public class Response {
    private final String response;

    public Response(final String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
