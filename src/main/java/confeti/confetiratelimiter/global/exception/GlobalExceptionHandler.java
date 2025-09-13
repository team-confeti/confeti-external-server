package confeti.confetiratelimiter.global.exception;

import confeti.confetiratelimiter.global.common.response.ApiResponseUtil;
import confeti.confetiratelimiter.global.common.response.BaseResponse;
import confeti.confetiratelimiter.global.common.response.ErrorCode;
import feign.FeignException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<?>> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<?>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.TYPE_MISMATCH);
    }

    @ExceptionHandler(ConfetiException.class)
    public ResponseEntity<BaseResponse<?>> handleConfetiException(ConfetiException e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(e.getErrorCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<?>> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseResponse<?>> handleFeignException(FeignException e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<BaseResponse<?>> handleRequestNotPermittedException(RequestNotPermitted e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.TOO_MANY_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleException(Exception e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        return ApiResponseUtil.failure(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
