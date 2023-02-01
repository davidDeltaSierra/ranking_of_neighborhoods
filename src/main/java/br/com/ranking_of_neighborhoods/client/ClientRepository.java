package br.com.ranking_of_neighborhoods.client;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
    Mono<Boolean> existsByEmail(String email);
}
