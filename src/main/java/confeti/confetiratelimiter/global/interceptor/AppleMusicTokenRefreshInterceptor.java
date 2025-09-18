package confeti.confetiratelimiter.global.interceptor;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicTokenGenerator;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
public class AppleMusicTokenRefreshInterceptor implements RequestInterceptor {

    private final AppleMusicTokenGenerator tokenGenerator;

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenGenerator.getAccessToken());
    }
}
