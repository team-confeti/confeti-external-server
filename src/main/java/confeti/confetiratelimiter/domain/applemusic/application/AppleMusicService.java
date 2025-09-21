package confeti.confetiratelimiter.domain.applemusic.application;

import confeti.confetiratelimiter.external.client.AppleMusicFeignClient;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicArtistSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.common.response.ErrorCode;
import confeti.confetiratelimiter.global.exception.ConfetiException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@RateLimiter(name = "appleMusicService")
@CircuitBreaker(name = "appleMusicService")
public class AppleMusicService {

    private final AppleMusicFeignClient client;

    public AppleMusicArtistResponse getArtistById(String id) {
        log.info("AppleMusicService.getArtistById Single artist lookup started. Target artist ID : {}", id);
        AppleMusicArtistsResponse appleMusicArtistsResponse = client.getArtistById(id);

        return appleMusicArtistsResponse.data().stream().findFirst()
                .orElseThrow(
                        () -> new ConfetiException(ErrorCode.NOT_FOUND)
                );
    }

    public AppleMusicArtistsResponse getArtistsByIds(String ids) {
        log.info("AppleMusicService.getArtistsByIds Multiple artists lookup started. Target artist IDs : {}", ids);
        return client.getArtistsByIds(ids);
    }

    public AppleMusicArtistsResponse getRelatedArtistsById(String id, String view, String limit) {
        log.info("AppleMusicService.getRelatedArtistsById Related artists lookup started. Target artist IDs : {}, View : {}, Limit : {}", id, view, limit);
        return client.getRelatedArtistsById(id, view, limit);
    }

    public AppleMusicArtistSongsResponse getArtistMusicsById(String id, String limit, String offset) {
        log.info("AppleMusicService.getArtistMusicsById Artist musics lookup started. Target artist ID : {}, Limit : {}, Offset : {}", id, limit, offset);
        return client.getArtistMusicsById(id, limit, offset);
    }

    public AppleMusicSongsResponse getSongsByIds(String ids) {
        log.info("AppleMusicService.getSongsByIds Songs lookup started. Target song IDs : {}", ids);
        return client.getSongsByIds(ids);
    }

    public AppleMusicSearchResponse searchByKeyword(String term, String types, String limit, String offset, String with) {
        log.info("AppleMusicService.searchByKeyword Search started. Term : {}, Types : {}, Limit : {}, Offset : {}, Width : {}", term, types, limit, offset, with);
        return client.searchByKeyword(term, types, limit, offset, with);
    }

    public AppleMusicChartsResponse getCharts(String types, String limit) {
        log.info("AppleMusicService.getCharts Charts lookup started. Types : {}, Limit : {}", types, limit);
        return client.getCharts(types, limit);
    }
}
