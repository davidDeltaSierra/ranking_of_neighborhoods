package br.com.ranking_of_neighborhoods.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Value
@Builder
@Jacksonized
public class PageQuery {

    public PageQuery(Integer page, Integer size, String direction, List<String> orderBy) {
        this.page = nonNull(page) ? page : 1;
        this.size = nonNull(size) ? size : 50;
        this.direction = direction;
        this.orderBy = orderBy;
    }

    @Min(1)
    Integer page;
    @Min(1)
    @Max(50)
    Integer size;
    String direction;
    List<String> orderBy;

    public PageRequest toPageRequest(String... allowedSortProperties) {
        if (isNull(orderBy) || isNull(allowedSortProperties)) {
            return PageRequest.of(this.page - 1, this.size);
        }
        var props = orderBy.stream()
                .filter(it -> Arrays.asList(allowedSortProperties).contains(it))
                .toArray(String[]::new);
        if (props.length == 0) {
            return PageRequest.of(this.page - 1, this.size);
        }
        return PageRequest.of(
                this.page - 1,
                this.size,
                Sort.by(factoryDirection(this.direction), props)
        );
    }

    private Sort.Direction factoryDirection(String direction) {
        if (isNull(direction)) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.fromOptionalString(direction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Direction must be ASC or DESC"));
    }
}
