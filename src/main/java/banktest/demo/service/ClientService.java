package banktest.demo.service;

import banktest.demo.model.Client;

public interface ClientService {
    void save(Client client);

    Client get(Long id);

    void delete(Long id);

    void update(Client client);
}
