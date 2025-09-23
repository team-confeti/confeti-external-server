package confeti.confetiratelimiter.api.applemusic.facade;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicService;
import confeti.confetiratelimiter.domain.applemusic.common.AppleMusicFetchLimit;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.AppleMusicArtistsResponse;
import confeti.confetiratelimiter.external.client.dto.response.chart.AppleMusicChartsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicArtistSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.song.AppleMusicSongsResponse;
import confeti.confetiratelimiter.external.client.dto.response.search.AppleMusicSearchResponse;
import confeti.confetiratelimiter.global.annotation.Facade;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Facade
@RequiredArgsConstructor
public class AppleMusicFacade {

    private static final String ID_DELIMITER = ",";

    private final AppleMusicService appleMusicService;

    public Mono<AppleMusicArtistResponse> getArtistById(String id) {
        return appleMusicService.getArtistById(id);
    }

    public Mono<AppleMusicArtistsResponse> getArtistsByIds(String ids) {
        List<String> artistIds = Arrays.stream(ids.split(ID_DELIMITER)).toList();

        return Flux.fromIterable(partition(artistIds, AppleMusicFetchLimit.ARTISTS))
                .flatMap(appleMusicService::getArtistsByIds)
                .map(AppleMusicArtistsResponse::data)
                .flatMap(Flux::fromIterable)
                .collectList()
                .map(AppleMusicArtistsResponse::new);
    }

    public Mono<AppleMusicArtistsResponse> getRelatedArtistsById(String id, String limit) {
        return appleMusicService.getRelatedArtistsById(id, limit);
    }

    public Mono<AppleMusicSongsResponse> getArtistTopSongsById(String id, String limit) {
        return appleMusicService.getArtistTopSongsById(id, limit);
    }


    public Mono<AppleMusicArtistSongsResponse> getArtistMusicsById(String id, String limit, String offset) {
        return appleMusicService.getArtistMusicsById(id, limit, offset);
    }

    public Mono<AppleMusicSongsResponse> getSongsByIds(String ids) {
        List<String> songIds = Arrays.stream(ids.split(ID_DELIMITER)).toList();

        return Flux.fromIterable(partition(songIds, AppleMusicFetchLimit.SONGS))
                .flatMap(appleMusicService::getSongsByIds)
                .map(AppleMusicSongsResponse::data)
                .flatMap(Flux::fromIterable)
                .collectList()
                .map(AppleMusicSongsResponse::new);
    }

    public Mono<AppleMusicSearchResponse> searchByKeyword(String term, String types, String limit, String offset, String with) {
        return appleMusicService.searchByKeyword(term, types, limit, offset, with);
    }

    public Mono<AppleMusicChartsResponse> getCharts(String types, String limit) {
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
