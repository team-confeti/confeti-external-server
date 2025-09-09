package confeti.confetiratelimiter.external.client.dto.response.artist.attributes;

import confeti.confetiratelimiter.external.client.dto.response.artist.attributes.artwork.AppleMusicArtistArtworkResponse;
import confeti.confetiratelimiter.external.client.dto.response.artist.attributes.editorialnotes.AppleMusicArtistEditorialNotesResponse;
import java.util.List;

public record AppleMusicArtistAttributesResponse(
        AppleMusicArtistArtworkResponse artwork,
        AppleMusicArtistEditorialNotesResponse editorialNotes,
        List<String> genreNames,
        Boolean isFavorites,
        String name,
        String url
) {
}
