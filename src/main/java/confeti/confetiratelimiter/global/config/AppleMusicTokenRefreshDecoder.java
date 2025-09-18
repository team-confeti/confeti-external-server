package confeti.confetiratelimiter.global.config;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicTokenGenerator;
import confeti.confetiratelimiter.global.common.response.ErrorCode;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppleMusicTokenRefreshDecoder implements ErrorDecoder {

    private final Long retryAfter = 0L;

    private final AppleMusicTokenGenerator tokenGenerator;
    private final ErrorDecoder decoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == ErrorCode.UNAUTHORIZED.getHttpStatus().value()) {
            tokenGenerator.refreshToken();

            return new RetryableException(
                    response.status(),
                    ErrorCode.EXPIRED_TOKEN.getMessage(),
                    response.request().httpMethod(),
                    retryAfter,
                    response.request()
            );
        }

        return decoder.decode(methodKey, response);
    }
}
