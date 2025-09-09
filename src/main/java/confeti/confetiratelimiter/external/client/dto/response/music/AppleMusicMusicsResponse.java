package confeti.confetiratelimiter.external.client.dto.response.music;

import java.util.List;

public record AppleMusicMusicsResponse(
        String next,
        List<AppleMusicMusicResponse> data
) {
}
