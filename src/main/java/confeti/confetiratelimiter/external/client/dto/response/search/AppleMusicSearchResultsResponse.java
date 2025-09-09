package confeti.confetiratelimiter.external.client.dto.response.search;

import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;

public record AppleMusicSearchResultsResponse(
        AppleMusicArtistsResponse artists,
        AppleMusicMusicsResponse songs
) {
}
