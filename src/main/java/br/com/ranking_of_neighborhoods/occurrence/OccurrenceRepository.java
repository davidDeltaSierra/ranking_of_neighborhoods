package br.com.ranking_of_neighborhoods.occurrence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface OccurrenceRepository extends ReactiveCrudRepository<Occurrence, Long> {
}
