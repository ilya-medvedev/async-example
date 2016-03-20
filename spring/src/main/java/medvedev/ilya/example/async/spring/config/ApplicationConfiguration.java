package medvedev.ilya.example.async.spring.config;

import medvedev.ilya.example.async.ping.PongRepository;
import medvedev.ilya.example.async.ping.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {
    private static final int THREAD_POOL_SIZE = 2;

    @Bean
    public Repository repository() {
        final Executor executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        return new PongRepository(executor);
    }
}
