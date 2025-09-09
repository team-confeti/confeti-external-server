package confeti.confetiratelimiter.domain.applemusic.application;

import confeti.confetiratelimiter.global.common.response.ErrorCode;
import confeti.confetiratelimiter.global.exception.ConfetiException;
import org.springframework.stereotype.Service;

@Service
public class AppleMusicValidateService {

    private static final String IDS_DELIMITER = ",";

    public void validateIds(String ids, int fetchLimit) {
        String[] idList = ids.split(IDS_DELIMITER);

        if (idList.length > fetchLimit) {
            throw new ConfetiException(ErrorCode.BAD_REQUEST);
        }
    }
}
