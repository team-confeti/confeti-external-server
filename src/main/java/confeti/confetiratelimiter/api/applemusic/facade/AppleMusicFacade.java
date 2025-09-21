package confeti.confetiratelimiter.api.applemusic.facade;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicService;
import confeti.confetiratelimiter.external.client.dto.response.album.AppleMusicAlbumsResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicArtistMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.annotation.Facade;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class AppleMusicFacade {

    private final AppleMusicService appleMusicService;

    public AppleMusicArtistResponse getArtistById(String id) {
        return appleMusicService.getArtistById(id);
    }

    public AppleMusicArtistsResponse getArtistsByIds(String ids) {
        return appleMusicService.getArtistsByIds(ids);
    }

    public AppleMusicArtistsResponse getRelatedArtistsById(String id, String view, String limit) {
        return appleMusicService.getRelatedArtistsById(id, view, limit);
    }

    public AppleMusicArtistMusicsResponse getArtistMusicsById(String id, String limit, String offset) {
        return appleMusicService.getArtistMusicsById(id, limit, offset);
    }

    public AppleMusicAlbumsResponse getAlbumsByIds(String ids) {
        return appleMusicService.getAlbumsByIds(ids);
    }

    public AppleMusicMusicsResponse getSongsByIds(String ids) {
        return appleMusicService.getSongsByIds(ids);
    }

    public AppleMusicSearchResponse searchByKeyword(String term, String types, String limit, String offset, String with) {
        return appleMusicService.searchByKeyword(term, types, limit, offset, with);
    }

    public AppleMusicChartsResponse getCharts(String types, String limit) {
        return appleMusicService.getCharts(types, limit);
    }
}
