package confeti.confetiratelimiter.external.client.dto.response.song;

import java.util.List;

public record AppleMusicSongsResponse(
        String next,
        List<AppleMusicSongResponse> data
) {
    public AppleMusicSongsResponse(List<AppleMusicSongResponse> data) {
        this(null, data);
    }
}
