package confeti.confetiratelimiter.api.applemusic.controller;

import confeti.confetiratelimiter.api.applemusic.facade.AppleMusicFacade;
import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicService;
import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicValidateService;
import confeti.confetiratelimiter.domain.applemusic.common.AppleMusicFetchLimit;
import confeti.confetiratelimiter.external.client.dto.response.music.AppleMusicMusicsResponse;
import confeti.confetiratelimiter.global.common.response.ApiResponseUtil;
import confeti.confetiratelimiter.global.common.response.BaseResponse;
import confeti.confetiratelimiter.global.common.response.ErrorCode;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apple-music-api")
public class AppleMusicController {

    private final AppleMusicFacade appleMusicFacade;
    private final AppleMusicValidateService appleMusicValidateService;

    @GetMapping("/artists/{id}")
    public ResponseEntity<BaseResponse<?>> getArtistById(
            @PathVariable String id
    ) {
        return ApiResponseUtil.success(appleMusicFacade.getArtistById(id));
    }

    @GetMapping("/artists")
    public ResponseEntity<BaseResponse<?>> getArtists(
            @RequestParam String ids
    ) {
        return ApiResponseUtil.success(appleMusicFacade.getArtistsByIds(ids));
    }

    @GetMapping("/artists/{id}/view/{view}")
    public ResponseEntity<BaseResponse<?>> getRelatedArtistsById(
            @PathVariable String id,
            @PathVariable String view,
            @RequestParam String limit
    ) {
        return ApiResponseUtil.success(appleMusicFacade.getRelatedArtistsById(id, view, limit));
    }

    @GetMapping("/artists/{id}/songs")
    public ResponseEntity<BaseResponse<?>> getArtistMusicsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam String offset
    ) {
        return ApiResponseUtil.success(appleMusicFacade.getArtistMusicsById(id, limit, offset));
    }

    @GetMapping("/songs")
    public ResponseEntity<BaseResponse<?>> getSongsByIds(
            @RequestParam String ids
    ) {
        return ApiResponseUtil.success(appleMusicFacade.getSongsByIds(ids));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<?>> searchByKeyword(
            @RequestParam String term,
            @RequestParam String types,
            @RequestParam String limit,
            @RequestParam(required = false) String offset,
            @RequestParam(required = false) String with
    ) {
        appleMusicValidateService.validateLimit(limit, AppleMusicFetchLimit.SEARCH_MIN, AppleMusicFetchLimit.SEARCH_MAX);
        return ApiResponseUtil.success(appleMusicFacade.searchByKeyword(term, types, limit, offset, with));
    }

    @GetMapping("/charts")
    public ResponseEntity<BaseResponse<?>> getCharts(
            @RequestParam String types,
            @RequestParam String limit
    ) {
        appleMusicValidateService.validateLimit(limit, AppleMusicFetchLimit.CHARTS_MIN, AppleMusicFetchLimit.CHARTS_MAX);
        return ApiResponseUtil.success(appleMusicFacade.getCharts(types, limit));
    }
}
