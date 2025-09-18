package confeti.confetiratelimiter.external.client.dto.response.chart;

import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicResponse;
import java.util.List;

public record AppleMusicChartSongResponse(
        List<AppleMusicMusicResponse> data
) {
}
