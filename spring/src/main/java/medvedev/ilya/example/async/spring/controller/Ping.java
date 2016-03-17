package medvedev.ilya.example.async.spring.controller;

import medvedev.ilya.example.async.ping.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ping")
public class Ping {
    @Autowired
    private Repository pongRepository;

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture ping() {
        return pongRepository.getResponse();
    }
}
