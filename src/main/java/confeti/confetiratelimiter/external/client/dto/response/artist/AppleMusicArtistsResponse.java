package confeti.confetiratelimiter.external.client.dto.response.artist;

import java.util.List;

public record AppleMusicArtistsResponse(
        String next,
        List<AppleMusicArtistResponse> data
) {
    public AppleMusicArtistsResponse(List<AppleMusicArtistResponse> data){
        this(null, data);
    }
}
