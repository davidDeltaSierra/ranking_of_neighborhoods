package br.com.ranking_of_neighborhoods.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Mono<Client> me() {
        return clientRepository.findById(1L);
    }
}
