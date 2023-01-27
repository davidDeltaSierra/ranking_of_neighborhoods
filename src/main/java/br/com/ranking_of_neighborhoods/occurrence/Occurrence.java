package br.com.ranking_of_neighborhoods.occurrence;

import br.com.ranking_of_neighborhoods.category.Category;
import br.com.ranking_of_neighborhoods.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "occurrence")
public class Occurrence {
    @Id
    private Long id;
    private String description;
    private String street;
    private String streetNumber;
    private String neighborhood;
    private String city;
    private String acronym;
    private Double latitude;
    private Double longitude;
    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now();
    private Long idOwner;
    private Long idCategory;
    @Transient
    private Category category;
    @Transient
    private Client owner;
}
