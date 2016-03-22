package medvedev.ilya.example.async.spring.controller;

import medvedev.ilya.example.async.ping.Repository;
import medvedev.ilya.example.async.ping.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("ping")
public class Ping {
    private final Repository repository;

    @Autowired
    public Ping(final Repository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<Response> ping() {
        return repository.getResponse();
    }
}
