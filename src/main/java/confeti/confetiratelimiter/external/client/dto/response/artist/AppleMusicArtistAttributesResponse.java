package confeti.confetiratelimiter.external.client.dto.response.artist;

import java.util.List;

public record AppleMusicArtistAttributesResponse(
        List<String> genreNames,
        String name,
        AppleMusicArtistArtworkResponse artwork
) {
}
