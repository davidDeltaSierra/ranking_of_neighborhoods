package br.com.ranking_of_neighborhoods.occurrence;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

interface OccurrenceRepository extends ReactiveCrudRepository<Occurrence, Long> {
    @Query("""
            select o.*, c.name as category_name, c.description as category_description
            from occurrence o
            inner join category c on o.id_category=c.id
            where o.id=:id
            """)
    Mono<OccurrenceFullQueryResult> findFullById(Long id);
}
