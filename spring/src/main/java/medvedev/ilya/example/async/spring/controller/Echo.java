package medvedev.ilya.example.async.spring.controller;

import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("echo")
public class Echo {
    @RequestMapping(method = RequestMethod.POST)
    public Response ping(@RequestBody final Request request) {
        final String stringRequest = request.getRequest();

        return new Response(stringRequest);
    }
}
