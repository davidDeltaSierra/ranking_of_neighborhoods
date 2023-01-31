package br.com.ranking_of_neighborhoods.occurrence;

import br.com.ranking_of_neighborhoods.category.Category;
import br.com.ranking_of_neighborhoods.category.CategoryService;
import br.com.ranking_of_neighborhoods.client.Client;
import br.com.ranking_of_neighborhoods.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OccurrenceService {
    private final OccurrenceRepository occurrenceRepository;
    private final CategoryService categoryService;
    private final ClientService clientService;

    public Mono<Occurrence> findById(Long id) {
        return occurrenceRepository.findFullById(id)
                .map(OccurrenceFullQueryResult::getOccurrence)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Occurrence not found")));
    }

    public Mono<Occurrence> save(OccurrenceRequest occurrenceRequest) {
        return Mono.zip(
                categoryService.findById(occurrenceRequest.idCategory()),
                clientService.me()
        ).flatMap(zip -> save(occurrenceRequest, zip.getT1(), zip.getT2()));
    }

    private Mono<Occurrence> save(OccurrenceRequest occurrenceRequest, Category category, Client client) {
        return occurrenceRepository.save(
                factoryOccurrence(occurrenceRequest, category, client)
        );
    }

    private Occurrence factoryOccurrence(OccurrenceRequest occurrenceRequest,
                                         Category category,
                                         Client client) {
        return Occurrence.builder()
                //.category(category)
                .idCategory(category.getId())
                .owner(client)
                .idOwner(category.getId())
                .description(occurrenceRequest.description())
                .street(occurrenceRequest.street())
                .streetNumber(occurrenceRequest.streetNumber())
                .neighborhood(occurrenceRequest.neighborhood())
                .city(occurrenceRequest.city())
                .acronym(occurrenceRequest.acronym())
                .latitude(occurrenceRequest.latitude())
                .longitude(occurrenceRequest.longitude())
                .build();
    }
}
