package confeti.confetiratelimiter.global.common.response;

import org.springframework.http.ResponseEntity;

public interface ApiResponseUtil {

    static ResponseEntity<BaseResponse<?>> success() {
        return ResponseEntity.status(SuccessCode.SUCCESS.getHttpStatus())
                .body(BaseResponse.of(SuccessCode.SUCCESS));
    }

    static <T> ResponseEntity<BaseResponse<?>> success(T data) {
        return ResponseEntity.status(SuccessCode.SUCCESS.getHttpStatus())
                .body(BaseResponse.of(SuccessCode.SUCCESS, data));
    }

    static ResponseEntity<BaseResponse<?>> success(SuccessCode successCode) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(BaseResponse.of(successCode));
    }

    static <T> ResponseEntity<BaseResponse<?>> success(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(BaseResponse.of(successCode, data));
    }

    static ResponseEntity<BaseResponse<?>> failure(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(BaseResponse.of(errorCode));
    }
}
