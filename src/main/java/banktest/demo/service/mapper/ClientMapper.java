package banktest.demo.service.mapper;

import banktest.demo.model.Client;
import banktest.demo.model.dto.request.ClientRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client fromDto(ClientRequestDto dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        return client;
    }
}
