package confeti.confetiratelimiter.global.interceptor;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicTokenGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AppleMusicTokenRefreshInterceptor implements ReactiveHttpRequestInterceptor {

    private final AppleMusicTokenGenerator tokenGenerator;

    @Override
    public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest request) {
        request.headers().put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + tokenGenerator.getAccessToken()));

        return Mono.just(request);
    }
}
