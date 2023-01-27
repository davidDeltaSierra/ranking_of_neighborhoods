package br.com.ranking_of_neighborhoods.client;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
}
