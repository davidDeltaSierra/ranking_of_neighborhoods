package br.com.ranking_of_neighborhoods.client;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

@Builder
@Jacksonized
public record ClientRequest(
        @NotEmpty @Length(max = 255)
        String name,
        @NotEmpty @Length(max = 255)
        String email,
        @NotEmpty @Length(min = 6, max = 255)
        String password
) {
}
