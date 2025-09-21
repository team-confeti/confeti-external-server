package confeti.confetiratelimiter.external.client.dto.response.chart;

import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicSongResponse;
import java.util.List;

public record AppleMusicChartSongResponse(
        List<AppleMusicSongResponse> data
) {
}
