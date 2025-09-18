package confeti.confetiratelimiter.external.client.dto.response.album;

public record AppleMusicAlbumResponse(
        String id,
        String type,
        String href,
        AppleMusicAlbumAttributesResponse attributes
        // ignore relationships
        // ignore views
) {
}
