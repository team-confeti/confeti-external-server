package confeti.confetiratelimiter.domain.applemusic.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleMusicFetchLimit {

    public static final int ARTISTS = 25;
    public static final int ALBUMS = 100;
    public static final int SONGS = 300;
    public static final int SEARCH_MIN = 5;
    public static final int SEARCH_MAX = 25;
}
