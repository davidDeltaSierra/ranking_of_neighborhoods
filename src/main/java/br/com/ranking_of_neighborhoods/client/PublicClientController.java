package br.com.ranking_of_neighborhoods.client;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("public/v1/clients")
@Timed("http_server_requests")
class PublicClientController {
    private final ClientService clientService;

    @Operation(summary = "Save new client")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Mono<Void>> save(@Valid @RequestBody ClientRequest body) {
        return new ResponseEntity<>(
                clientService.save(body).flatMap(it -> Mono.empty()),
                HttpStatus.NO_CONTENT
        );
    }
}
