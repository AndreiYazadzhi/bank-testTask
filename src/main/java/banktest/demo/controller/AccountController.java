package banktest.demo.controller;

import banktest.demo.model.Account;
import banktest.demo.model.dto.request.AccountRequestDto;
import banktest.demo.service.AccountService;
import banktest.demo.service.mapper.AccountMapper;
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
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper mapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Account> create(@RequestBody AccountRequestDto dto) {
        Account account = mapper.fromDto(dto);
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.get(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
