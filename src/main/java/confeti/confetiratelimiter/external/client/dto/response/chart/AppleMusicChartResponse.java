package confeti.confetiratelimiter.external.client.dto.response.chart;

import java.util.List;

public record AppleMusicChartResponse(
        List<AppleMusicChartSongResponse> songs
) {
}
