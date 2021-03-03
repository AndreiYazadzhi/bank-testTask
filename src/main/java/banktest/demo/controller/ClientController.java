package banktest.demo.controller;

import banktest.demo.model.Client;
import banktest.demo.model.dto.request.ClientRequestDto;
import banktest.demo.service.ClientService;
import banktest.demo.service.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper mapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Client> create(@RequestBody ClientRequestDto dto) {
        Client client = mapper.fromDto(dto);
        clientService.save(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Client> get(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.get(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
