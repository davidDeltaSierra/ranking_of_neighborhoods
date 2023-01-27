package br.com.ranking_of_neighborhoods.category;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

interface CategoryRepository extends ReactiveCrudRepository<Category, Long>, ReactiveSortingRepository<Category, Long> {
}
