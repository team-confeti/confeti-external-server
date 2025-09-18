package confeti.confetiratelimiter.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BaseResponse<T> {

    private final int status;
    private final String message;
    @JsonInclude(value = Include.NON_NULL)
    private final T data;

    public static BaseResponse<?> of(SuccessCode successCode) {
        return builder()
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .build();
    }

    public static <T> BaseResponse<?> of(SuccessCode successCode, T data) {
        return builder()
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

    public static BaseResponse<?> of(ErrorCode errorCode) {
        return builder()
                .status(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .build();
    }
}
