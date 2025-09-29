package confeti.confetiratelimiter.external.client.dto.response.song;

import java.util.List;

public record AppleMusicSongAttributesResponse(
        String name,
        String artistName,
        AppleMusicSongArtworkResponse artwork,
        List<AppleMusicSongPreviewResponse> previews
) {
}
