package banktest.demo.service.impl;

import banktest.demo.model.Client;
import banktest.demo.repository.ClientRepository;
import banktest.demo.service.ClientService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client get(Long id) {
        return clientRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Could not find user with id " + id + " in DB"));
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(clientRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Could not find user with id " + id + " in DB")));
    }

    @Override
    public void update(Client client) {
        clientRepository.save(client);
    }
}
