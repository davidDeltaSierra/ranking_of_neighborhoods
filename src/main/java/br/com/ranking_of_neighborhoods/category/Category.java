package br.com.ranking_of_neighborhoods.category;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    private String name;
    private String description;
}
