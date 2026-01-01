package confeti.confetiratelimiter.external.client;

import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicArtistSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.config.AppleMusicFeignConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@ReactiveFeignClient(
        name = "AppleMusicFeignClient",
        url = "${apple-music.api.host}",
        path = "${apple-music.api.path}",
        configuration = AppleMusicFeignConfig.class
)
public interface AppleMusicFeignClient {

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-a-catalog-artist
     */
    @GetMapping("/artists/{id}")
    Mono<AppleMusicArtistsResponse> getArtistById(
            @PathVariable String id
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-multiple-catalog-artists
     * fetch limit: 25
     */
    @GetMapping("/artists")
    Mono<AppleMusicArtistsResponse> getArtistsByIds(
            @RequestParam String ids
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/fetch-a-view-on-this-resource-by-name-4kow5
     * limit: no specified
     */
    @GetMapping("/artists/{id}/view/top-songs")
    Mono<AppleMusicSongsResponse> getArtistTopSongsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam(required = false) String offset
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/fetch-a-view-on-this-resource-by-name-4kow5
     * limit: no specified
     */
    @GetMapping("/artists/{id}/view/similar-artists")
    Mono<AppleMusicArtistsResponse> getRelatedArtistsById(
        @PathVariable String id,
        @RequestParam String limit,
        @RequestParam(required = false) String offset
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/fetch-a-relationship-on-this-resource-by-name-5akdm
     * limit: no specified
     */
    @GetMapping("/artists/{id}/songs")
    Mono<AppleMusicArtistSongsResponse> getArtistMusicsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam String offset
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-multiple-catalog-songs-by-id
     * fetch limit: 300
     */
    @GetMapping("/songs")
    Mono<AppleMusicSongsResponse> getSongsByIds(
            @RequestParam String ids
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/search-for-catalog-resources-(by-type)
     * limit: 5 ~ 25
     */
    @GetMapping("/search")
    Mono<AppleMusicSearchResponse> searchByKeyword(
            @RequestParam String term,
            @RequestParam String types,
            @RequestParam String limit,
            @RequestParam(required = false) String offset,
            @RequestParam(required = false) String with
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/charts
     * limit: 20 ~ 200
     */
    @GetMapping("/charts")
    Mono<AppleMusicChartsResponse> getCharts(
            @RequestParam String types,
            @RequestParam String limit
    );
}
