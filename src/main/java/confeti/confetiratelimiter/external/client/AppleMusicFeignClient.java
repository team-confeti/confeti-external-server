package confeti.confetiratelimiter.external.client;

import confeti.confetiratelimiter.external.client.dto.response.album.AppleMusicAlbumsResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicArtistMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.config.AppleMusicFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
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
    AppleMusicArtistsResponse getArtistById(
            @PathVariable String id
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-multiple-catalog-artists
     * fetch limit: 25
     */
    @GetMapping("/artists")
    AppleMusicArtistsResponse getArtistsByIds(
            @RequestParam String ids
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/fetch-a-view-on-this-resource-by-name-4kow5
     * limit: no specified
     */
    @GetMapping("/artists/{id}/view/{view}")
    AppleMusicArtistsResponse getRelatedArtistsById(
            @PathVariable String id,
            @PathVariable String view,
            @RequestParam String limit
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/fetch-a-relationship-on-this-resource-by-name-5akdm
     * limit: no specified
     */
    @GetMapping("/artists/{id}/songs")
    AppleMusicArtistMusicsResponse getArtistMusicsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam String offset
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-multiple-catalog-albums
     * fetch limit: 100
     */
    @GetMapping("/albums")
    AppleMusicAlbumsResponse getAlbumsByIds(
            @RequestParam String ids
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/get-multiple-catalog-songs-by-id
     * fetch limit: 300
     */
    @GetMapping("/songs")
    AppleMusicMusicsResponse getSongsByIds(
            @RequestParam String ids
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/search-for-catalog-resources-(by-type)
     * limit: 5 ~ 25
     */
    @GetMapping("/search")
    AppleMusicSearchResponse getArtistsByKeyword(
            @RequestParam String term,
            @RequestParam String types,
            @RequestParam String limit,
            @RequestParam String offset,
            @RequestParam String with
    );

    /**
     * url: https://developer.apple.com/documentation/applemusicapi/charts
     * limit: 20 ~ 200
     * @param types
     * @param limit
     * @return
     */
    @GetMapping("/charts")
    AppleMusicChartsResponse getCharts(
            @RequestParam String types,
            @RequestParam String limit
    );
}
