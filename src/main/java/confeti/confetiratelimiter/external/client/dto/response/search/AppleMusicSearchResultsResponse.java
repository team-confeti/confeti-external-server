package confeti.confetiratelimiter.external.client.dto.response.search;

import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicSongsResponse;

public record AppleMusicSearchResultsResponse(
        AppleMusicArtistsResponse artists,
        AppleMusicSongsResponse songs
) {
}
