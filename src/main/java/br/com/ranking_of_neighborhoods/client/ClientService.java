package br.com.ranking_of_neighborhoods.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Mono<Client> me() {
        return clientRepository.findById(1L);
    }

    public Mono<Client> save(ClientRequest clientRequest) {
        return clientRepository.existsByEmail(clientRequest.email())
                .filter(it -> !it)
                .flatMap(it -> clientRepository.save(mapper(clientRequest)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists")));
    }

    private Client mapper(ClientRequest clientRequest) {
        return Client.builder()
                .email(clientRequest.email())
                .name(clientRequest.name())
                .password(bCryptPasswordEncoder.encode(clientRequest.password()))
                .build();
    }
}
