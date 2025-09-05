package confeti.confetiratelimiter.external.client;

import confeti.confetiratelimiter.domain.applemusic.infra.client.AppleMusicExternalClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "AppleMusicFeignClient",
        url = "https://api.music.apple.com"
)
public interface AppleMusicFeignClient extends AppleMusicExternalClient {
}
