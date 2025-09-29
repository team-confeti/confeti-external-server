package confeti.confetiratelimiter.external.client.dto.response.song;

public record AppleMusicSongResponse(
        String id,
        String type,
        AppleMusicSongAttributesResponse attributes,
        AppleMusicSongRelationshipsResponse relationships
) {
}
