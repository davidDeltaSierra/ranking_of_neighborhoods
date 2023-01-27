package br.com.ranking_of_neighborhoods.category;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/categories")
@Timed("http_server_requests")
class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "List all categories")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Flux<Category>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }
}
