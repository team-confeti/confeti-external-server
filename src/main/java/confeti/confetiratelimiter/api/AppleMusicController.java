package confeti.confetiratelimiter.api;

import confeti.confetiratelimiter.domain.applemusic.infra.client.AppleMusicExternalClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppleMusicController {

    private final AppleMusicExternalClient appleMusicExternalClient;
}
