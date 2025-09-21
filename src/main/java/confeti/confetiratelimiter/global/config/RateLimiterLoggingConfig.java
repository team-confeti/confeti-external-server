package confeti.confetiratelimiter.global.config;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateLimiterLoggingConfig {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterLoggingConfig.class);
    private final RateLimiterRegistry registry;

    @PostConstruct
    public void addLoggingEvent() {
        registry.getAllRateLimiters().forEach(rateLimiter ->
                rateLimiter.getEventPublisher()
                        .onFailure(event -> log.info("Rate Limiter [FAIL-{}] acquired : {}", event.getEventType(), event.getRateLimiterName()))
        );

        registry.getEventPublisher()
                .onEntryAdded(addedEvent -> {
                    addedEvent.getAddedEntry().getEventPublisher()
                            .onFailure(event -> log.info("Rate Limiter [FAIL-{}] acquired : {}", event.getEventType(), event.getRateLimiterName()));
                });
    }
}
