package confeti.confetiratelimiter.api.applemusic.controller;

import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicService;
import confeti.confetiratelimiter.domain.applemusic.application.AppleMusicValidateService;
import confeti.confetiratelimiter.domain.applemusic.common.AppleMusicFetchLimit;
import confeti.confetiratelimiter.global.common.response.ApiResponseUtil;
import confeti.confetiratelimiter.global.common.response.BaseResponse;
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

    private final AppleMusicService appleMusicService;
    private final AppleMusicValidateService appleMusicValidateService;

    @GetMapping("/artists/{id}")
    public ResponseEntity<BaseResponse<?>> getArtistById(
            @PathVariable String id
    ) {
        return ApiResponseUtil.success(appleMusicService.getArtistById(id));
    }

    @GetMapping("/artists")
    public ResponseEntity<BaseResponse<?>> getArtists(
            @RequestParam String ids
    ) {
        appleMusicValidateService.validateIds(ids, AppleMusicFetchLimit.ARTISTS);
        return ApiResponseUtil.success(appleMusicService.getArtistsByIds(ids));
    }

    @GetMapping("/artists/{id}/view/{view}")
    public ResponseEntity<BaseResponse<?>> getRelatedArtistsById(
            @PathVariable String id,
            @PathVariable String view,
            @RequestParam String limit
    ) {
        return ApiResponseUtil.success(appleMusicService.getRelatedArtistsById(id, view, limit));
    }

    @GetMapping("/artists/{id}/songs")
    public ResponseEntity<BaseResponse<?>> getArtistMusicsById(
            @PathVariable String id,
            @RequestParam String limit,
            @RequestParam String offset
    ) {
        return ApiResponseUtil.success(appleMusicService.getArtistMusicsById(id, limit, offset));
    }
}
