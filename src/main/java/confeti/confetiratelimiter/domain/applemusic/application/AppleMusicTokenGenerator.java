package confeti.confetiratelimiter.domain.applemusic.application;

import confeti.confetiratelimiter.global.common.response.ErrorCode;
import confeti.confetiratelimiter.global.exception.ConfetiException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppleMusicTokenGenerator {

    private final String PRIVATE_KEY_ALGORITHM = "EC";

    @Value("${apple-music.credentials.key-id}")
    private String keyId;

    @Value("${apple-music.credentials.team-id}")
    private String teamId;

    @Value("${apple-music.credentials.private-key}")
    private String privateKey;

    @Value("${apple-music.credentials.expiration}")
    private Long expiration;

    private final AtomicReference<String> accessToken = new AtomicReference<>();
    private final AtomicLong generatedTime = new AtomicLong(0);

    @PostConstruct
    private void init() {
        generateToken();
    }

    public String getAccessToken() {
        if (isTokenExpired()) {
            refreshToken();
        }

        return accessToken.get();
    }

    private boolean isTokenExpired() {
        return System.currentTimeMillis() - generatedTime.get() > (expiration - 30_000); // 30초의 여유를 두고 만료 여부 판단
    }

    public void refreshToken() {
        synchronized (this) {
            if (isTokenExpired()) {
                generateToken();
            }
        }
    }

    private void generateToken() {
        long nowTime = System.currentTimeMillis();
        log.info("AppleMusicTokenGenerator.generateToken Token Expired. Try to generate token... Current Time : {}", nowTime);
        Date now = new Date();

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", teamId);
        claims.put("iat", now);
        claims.put("exp", new Date(now.getTime() + expiration));

        String generatedAccessToken = Jwts.builder()
                .header().keyId(keyId).add("alg", "ES256").and()
                .claims(claims)
                .signWith(getPrivateKey())
                .compact();

        accessToken.set(generatedAccessToken);
        generatedTime.set(nowTime);
    }

    private PrivateKey getPrivateKey() {
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(PRIVATE_KEY_ALGORITHM);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            log.error("AppleMusicTokenGenerator.getPrivateKey Failed to generate key spec");
            throw new ConfetiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
