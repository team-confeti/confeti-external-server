package confeti.confetiratelimiter.global.exception;

import confeti.confetiratelimiter.global.common.response.ErrorCode;
import lombok.Getter;

@Getter
public class ConfetiException extends RuntimeException {

    private final ErrorCode errorCode;

    public ConfetiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ConfetiException(ErrorCode errorCode, String reason) {
        super(errorCode.getMessage() + ", " + reason);
        this.errorCode = errorCode;
    }
}
