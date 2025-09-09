package confeti.confetiratelimiter.external.client.dto.response.artist;

public record AppleMusicArtistResponse(
        String id,
        String type,
        AppleMusicArtistAttributesResponse attributes,
        AppleMusicArtistRelationshipsResponse relationships
) {
}
