package br.com.ranking_of_neighborhoods.occurrence;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/occurrences")
@Timed("http_server_requests")
class OccurrenceController {
    private final OccurrenceService occurrenceService;

    @Operation(summary = "Find occurrence by id")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Mono<Occurrence>> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(occurrenceService.findById(id));
    }

    @Operation(summary = "Save new occurrence")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Mono<Occurrence>> save(@Valid @RequestBody OccurrenceRequest body) {
        return ResponseEntity.ok()
                .body(occurrenceService.save(body));
    }
}
