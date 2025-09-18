package confeti.confetiratelimiter.external.client.dto.response.music;

public record AppleMusicMusicResponse(
        String id,
        String type,
        AppleMusicMusicAttributesResponse attributes,
        AppleMusicMusicRelationshipsResponse relationships
) {
}
