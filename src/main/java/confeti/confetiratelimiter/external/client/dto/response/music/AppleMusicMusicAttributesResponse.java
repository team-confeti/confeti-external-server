package confeti.confetiratelimiter.external.client.dto.response.music;

import java.util.List;

public record AppleMusicMusicAttributesResponse(
        String name,
        String artistName,
        AppleMusicMusicArtworkResponse artwork,
        List<AppleMusicMusicPreviewResponse> previews
) {
}
