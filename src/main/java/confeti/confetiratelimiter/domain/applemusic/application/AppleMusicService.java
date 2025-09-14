package confeti.confetiratelimiter.domain.applemusic.application;

import confeti.confetiratelimiter.external.client.AppleMusicFeignClient;
import confeti.confetiratelimiter.external.client.dto.response.album.AppleMusicAlbumsResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicArtistMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.common.response.ErrorCode;
import confeti.confetiratelimiter.global.exception.ConfetiException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CircuitBreaker(name = "appleMusicService", fallbackMethod = "circuitBreakerFallback")
public class AppleMusicService {

    private final AppleMusicFeignClient client;

    public AppleMusicArtistResponse getArtistById(String id) {
        AppleMusicArtistsResponse appleMusicArtistsResponse = client.getArtistById(id);

        return appleMusicArtistsResponse.data().stream().findFirst()
                .orElseThrow(
                        () -> new ConfetiException(ErrorCode.NOT_FOUND)
                );
    }

    public AppleMusicArtistsResponse getArtistsByIds(String ids) {
        return client.getArtistsByIds(ids);
    }

    public AppleMusicArtistsResponse getRelatedArtistsById(String id, String view, String limit) {
        return client.getRelatedArtistsById(id, view, limit);
    }

    public AppleMusicArtistMusicsResponse getArtistMusicsById(String id, String limit, String offset) {
        return client.getArtistMusicsById(id, limit, offset);
    }

    public AppleMusicAlbumsResponse getAlbumsByIds(String ids) {
        return client.getAlbumsByIds(ids);
    }

    public AppleMusicMusicsResponse getSongsByIds(String ids) {
        return client.getSongsByIds(ids);
    }

    public AppleMusicSearchResponse searchByKeyword(String term, String types, String limit, String offset, String with) {
        return client.searchByKeyword(term, types, limit, offset, with);
    }

    public AppleMusicChartsResponse getCharts(String types, String limit) {
        return client.getCharts(types, limit);
    }

    public void circuitBreakerFallback() {
        throw new ConfetiException(ErrorCode.BAD_GATEWAY);
    }
}
