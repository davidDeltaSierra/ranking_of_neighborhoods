package br.com.ranking_of_neighborhoods.occurrence;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

@Builder
@Jacksonized
public record OccurrenceRequest(
        @NotEmpty
        @Length(max = 255)
        String description,
        @NotEmpty
        @Length(max = 255)
        String street,
        @NotEmpty
        @Length(max = 10)
        String streetNumber,
        @NotEmpty
        @Length(max = 255)
        String neighborhood,
        @NotEmpty
        @Length(max = 255)
        String city,
        @NotEmpty
        @Length(max = 255)
        String acronym,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        @NotNull
        Long idCategory
) {
}
