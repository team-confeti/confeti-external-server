package confeti.confetiratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableReactiveFeignClients(basePackages = "confeti.confetiratelimiter.external.client")
public class ConfetiRateLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfetiRateLimiterApplication.class, args);
    }

}
