package br.com.ranking_of_neighborhoods.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    private String name;
    private String description;
}
