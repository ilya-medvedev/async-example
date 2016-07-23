package medvedev.ilya.example.async.spring.controller;

import medvedev.ilya.example.async.echo.EchoService;
import medvedev.ilya.example.async.echo.model.Request;
import medvedev.ilya.example.async.echo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("echo")
public class Echo {
    private final EchoService echoService;

    @Autowired
    public Echo(final EchoService echoService) {
        this.echoService = echoService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<Response> echo(@RequestBody final Request request) {
        return echoService.echo(request);
    }
}
