package confeti.confetiratelimiter.external.client.dto.response.artist.attributes.editorialnotes;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppleMusicArtistEditorialNotesResponse(
        @JsonProperty(value = "short")
        String shorts,
        String standard,
        String name,
        String tagline
) {
}
