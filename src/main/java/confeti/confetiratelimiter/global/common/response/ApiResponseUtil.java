package confeti.confetiratelimiter.global.common.response;

import org.springframework.http.ResponseEntity;

public interface ApiResponseUtil {

    static ResponseEntity<?> success() {
        return ResponseEntity.status(SuccessCode.SUCCESS.getHttpStatus())
                .body(SuccessCode.SUCCESS.getMessage());
    }

    static ResponseEntity<?> success(SuccessCode successCode) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(successCode.getMessage());
    }

    static <T> ResponseEntity<?> success(T data) {
        return ResponseEntity.status(SuccessCode.SUCCESS.getHttpStatus())
                .body(data);
    }

    static ResponseEntity<?> failure(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errorCode.getMessage());
    }
}
