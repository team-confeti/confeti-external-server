package confeti.confetiratelimiter.global.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicTokenGenerator;
import confeti.confetiratelimiter.global.interceptor.AppleMusicTokenRefreshInterceptor;
import feign.Logger;
import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.optionals.OptionalDecoder;
import lombok.RequiredArgsConstructor;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("confeti.confetiratelimiter.external.client")
@RequiredArgsConstructor
public class OpenFeignConfig {

    private final AppleMusicTokenGenerator tokenGenerator;

    @Bean
    public ErrorDecoder errorDecoder() {
        return new AppleMusicTokenRefreshDecoder(tokenGenerator);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new AppleMusicTokenRefreshInterceptor(tokenGenerator);
    }

    @Bean
    public Decoder feignDecoder() {
        ObjectMapper objectMapper =
                new ObjectMapper()
                        // 기본 타입에 null이 할당될 경우 에러 무시
                        .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                        // enum 값이 없는 경우 null 처리
                        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                        // property가 없을 때 에러 발생 무시
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new OptionalDecoder(new JacksonDecoder(objectMapper));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Level.BASIC;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient(
                new okhttp3.OkHttpClient.Builder()
                        .build()
        );
    }
}
