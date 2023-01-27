package br.com.ranking_of_neighborhoods.client;

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
@Table(name = "client")
public class Client {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
