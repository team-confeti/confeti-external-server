package confeti.confetiratelimiter.external.client.dto.response.artist;

import confeti.confetiratelimiter.external.client.dto.response.artist.attributes.AppleMusicArtistAttributesResponse;

public record AppleMusicArtistResponse(
        String id,
        String type,
        String href,
        AppleMusicArtistAttributesResponse attributes
        // ignore relationships
        // ignore views
) {
}
