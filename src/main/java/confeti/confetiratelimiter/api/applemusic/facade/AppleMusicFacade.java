package confeti.confetiratelimiter.api.applemusic.facade;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicService;
import confeti.confetiratelimiter.domain.applemusic.common.AppleMusicFetchLimit;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicArtistMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.annotation.Facade;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class AppleMusicFacade {

    private static final String ID_DELIMITER = ",";

    private final AppleMusicService appleMusicService;

    public AppleMusicArtistResponse getArtistById(String id) {
        return appleMusicService.getArtistById(id);
    }

    public AppleMusicArtistsResponse getArtistsByIds(String ids) {
        List<String> artistIds = Arrays.stream(ids.split(ID_DELIMITER)).toList();

        return new AppleMusicArtistsResponse(
                partition(artistIds, AppleMusicFetchLimit.ARTISTS)
                .parallelStream()
                .map(appleMusicService::getArtistsByIds)
                .map(AppleMusicArtistsResponse::data)
                .flatMap(List::stream)
                .toList()
        );
    }

    public AppleMusicArtistsResponse getRelatedArtistsById(String id, String view, String limit) {
        return appleMusicService.getRelatedArtistsById(id, view, limit);
    }

    public AppleMusicArtistMusicsResponse getArtistMusicsById(String id, String limit, String offset) {
        return appleMusicService.getArtistMusicsById(id, limit, offset);
    }

    public AppleMusicMusicsResponse getSongsByIds(String ids) {
        List<String> songIds = Arrays.stream(ids.split(ID_DELIMITER)).toList();

        return new AppleMusicMusicsResponse(
                null,
                partition(songIds, AppleMusicFetchLimit.SONGS)
                        .parallelStream()
                        .map(appleMusicService::getSongsByIds)
                        .map(AppleMusicMusicsResponse::data)
                        .flatMap(List::stream)
                        .toList()
        );
    }

    public AppleMusicSearchResponse searchByKeyword(String term, String types, String limit, String offset, String with) {
        return appleMusicService.searchByKeyword(term, types, limit, offset, with);
    }

    public AppleMusicChartsResponse getCharts(String types, String limit) {
        return appleMusicService.getCharts(types, limit);
    }

    private List<String> partition(List<String> targets, int partitionSize) {
        return IntStream.range(0, targets.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / partitionSize))
                .values()
                .stream()
                .map(indices -> indices.stream()
                        .map(targets::get)
                        .collect(Collectors.joining(ID_DELIMITER)))
                .toList();
    }
}
