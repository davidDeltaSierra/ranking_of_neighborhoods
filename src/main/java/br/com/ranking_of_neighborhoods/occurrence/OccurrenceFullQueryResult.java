package br.com.ranking_of_neighborhoods.occurrence;

import br.com.ranking_of_neighborhoods.category.Category;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class OccurrenceFullQueryResult {
    private Long id;
    private String description;
    private String street;
    private String streetNumber;
    private String neighborhood;
    private String city;
    private String acronym;
    private Double latitude;
    private Double longitude;
    private LocalDateTime registrationDate;
    private Long idOwner;
    private Long idCategory;
    private String categoryName;
    private String categoryDescription;

    public Occurrence getOccurrence() {
        return Occurrence.builder()
                .id(id)
                .description(description)
                .street(street)
                .streetNumber(streetNumber)
                .neighborhood(neighborhood)
                .city(city)
                .acronym(acronym)
                .latitude(latitude)
                .longitude(longitude)
                .registrationDate(registrationDate)
                .idOwner(idOwner)
                .idCategory(idCategory)
                .category(
                        Category.builder()
                                .id(idCategory)
                                .name(categoryName)
                                .description(categoryDescription)
                                .build()
                )
                .build();
    }
}
