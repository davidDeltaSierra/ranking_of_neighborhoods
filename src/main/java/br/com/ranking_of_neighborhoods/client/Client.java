package br.com.ranking_of_neighborhoods.client;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "client")
public class Client {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
