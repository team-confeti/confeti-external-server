package confeti.confetiratelimiter.external.client;

import confeti.confetiratelimiter.domain.applemusic.infra.client.AppleMusicExternalClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "AppleMusicFeignClient",
        url = "${apple-music.api.host}",
        path = "${apple-music.api.path}"
)
public interface AppleMusicFeignClient extends AppleMusicExternalClient {
}
