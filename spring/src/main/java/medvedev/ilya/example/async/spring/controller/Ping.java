package medvedev.ilya.example.async.spring.controller;

import medvedev.ilya.example.async.ping.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("ping")
public class Ping {
    private final Repository pongRepository;

    public Ping(final Repository pongRepository){
        this.pongRepository = pongRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture ping() {
        return pongRepository.getResponse();
    }
}
