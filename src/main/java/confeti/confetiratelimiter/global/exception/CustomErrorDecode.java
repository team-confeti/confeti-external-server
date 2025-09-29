package confeti.confetiratelimiter.global.exception;

import confeti.confetiratelimiter.global.common.response.ErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomErrorDecode implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String reason = getReason(response);

        log.error(response.toString());
        return switch (response.status()) {
            case 400 -> new ConfetiException(ErrorCode.BAD_REQUEST, reason);
            case 401 -> new ConfetiException(ErrorCode.UNAUTHORIZED, reason);
            case 403 -> new ConfetiException(ErrorCode.FORBIDDEN, reason);
            case 404 -> new ConfetiException(ErrorCode.NOT_FOUND, reason);
            case 409 -> new ConfetiException(ErrorCode.CONFLICT, reason);
            case 429 -> new ConfetiException(ErrorCode.TOO_MANY_REQUEST, reason);
            case 500 -> new ConfetiException(ErrorCode.INTERNAL_SERVER_ERROR, reason);
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }

    private String getReason(Response response) {
        String reason = "";

        try {
            reason = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reason;
    }
}
