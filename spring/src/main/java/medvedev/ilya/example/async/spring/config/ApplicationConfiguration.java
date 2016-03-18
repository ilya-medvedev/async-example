package medvedev.ilya.example.async.spring.config;

import medvedev.ilya.example.async.ping.PongRepository;
import medvedev.ilya.example.async.ping.Repository;
import medvedev.ilya.example.async.spring.controller.Echo;
import medvedev.ilya.example.async.spring.controller.Ping;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableWebMvc
public class ApplicationConfiguration {
    private static final int THREAD_POOL_SIZE = 2;

    @Bean
    public Ping ping() {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        final Repository repository = new PongRepository(executor);

        return new Ping(repository);
    }

    @Bean
    public Echo echo() {
        return new Echo();
    }
}
