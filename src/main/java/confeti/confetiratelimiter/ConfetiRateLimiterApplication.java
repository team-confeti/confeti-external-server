package confeti.confetiratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "confeti.confetiratelimiter.external.client")
@SpringBootApplication
public class ConfetiRateLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfetiRateLimiterApplication.class, args);
    }

}
