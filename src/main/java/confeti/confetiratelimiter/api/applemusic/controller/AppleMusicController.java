package confeti.confetiratelimiter.api.applemusic.controller;

import confeti.confetiratelimiter.api.applemusic.facade.AppleMusicFacade;
import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicValidateService;
import confeti.confetiratelimiter.domain.applemusic.common.AppleMusicFetchLimit;
import confeti.confetiratelimiter.global.common.response.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apple-music-api")
public class AppleMusicController {

    private final AppleMusicFacade appleMusicFacade;
    private final AppleMusicValidateService appleMusicValidateService;

    @GetMapping("/artists/{id}")
    public Mono<ResponseEntity<?>> getArtistById(
            @PathVariable String id
    ) {
        return appleMusicFacade.getArtistById(id)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/artists")
    public Mono<ResponseEntity<?>> getArtists(
            @RequestParam String ids
    ) {
        return appleMusicFacade.getArtistsByIds(ids)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/artists/{id}/view/similar-artists")
    public Mono<ResponseEntity<?>> getRelatedArtistsById(
            @PathVariable String id,
            @RequestParam String limit
    ) {
        return appleMusicFacade.getRelatedArtistsById(id, limit)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/artists/{id}/view/top-songs")
    public Mono<ResponseEntity<?>> getArtistTopSongsById(
            @PathVariable String id,
            @RequestParam String limit
    ) {
        return appleMusicFacade.getArtistTopSongsById(id, limit)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/artists/{id}/songs")
    public Mono<ResponseEntity<?>> getArtistMusicsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam String offset
    ) {
        return appleMusicFacade.getArtistMusicsById(id, limit, offset)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/songs")
    public Mono<ResponseEntity<?>> getSongsByIds(
            @RequestParam String ids
    ) {
        return appleMusicFacade.getSongsByIds(ids)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<?>> searchByKeyword(
            @RequestParam String term,
            @RequestParam String types,
            @RequestParam String limit,
            @RequestParam(required = false) String offset,
            @RequestParam(required = false) String with
    ) {
        appleMusicValidateService.validateLimit(limit, AppleMusicFetchLimit.SEARCH_MAX);

        return appleMusicFacade.searchByKeyword(term, types, limit, offset, with)
                .map(ApiResponseUtil::success);
    }

    @GetMapping("/charts")
    public Mono<ResponseEntity<?>> getCharts(
            @RequestParam String types,
            @RequestParam String limit
    ) {
        appleMusicValidateService.validateLimit(limit, AppleMusicFetchLimit.CHARTS_MIN, AppleMusicFetchLimit.CHARTS_MAX);

        return appleMusicFacade.getCharts(types, limit)
                .map(ApiResponseUtil::success);
    }
}
