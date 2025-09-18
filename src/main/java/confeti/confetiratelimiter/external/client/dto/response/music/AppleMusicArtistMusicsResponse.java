package confeti.confetiratelimiter.external.client.dto.response.music;

import java.util.List;

public record AppleMusicArtistMusicsResponse(
        String next,
        List<AppleMusicMusicResponse> data
) {
}
