package medvedev.ilya.example.async.spring.config;

import medvedev.ilya.example.async.ping.PongRepository;
import medvedev.ilya.example.async.ping.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableWebMvc
@ComponentScan("medvedev.ilya.example.async.spring.controller")
public class ApplicationConfiguration {
    private static final int THREAD_POOL_SIZE = 2;

    @Bean
    public Repository repository() {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        return new PongRepository(executor);
    }
}
