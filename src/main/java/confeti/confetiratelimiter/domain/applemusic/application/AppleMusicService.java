package confeti.confetiratelimiter.domain.applemusic.application;

import confeti.confetiratelimiter.external.client.AppleMusicFeignClient;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient client;
}
