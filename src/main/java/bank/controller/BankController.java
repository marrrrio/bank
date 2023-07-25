package bank.controller;

import bank.dto.AddClientDto;
import bank.dto.BankDto;
import bank.dto.BankResponse;
import bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/get-all-clients")
    public List<BankDto> getAllClients() {
        return bankService.getAllClients();
    }

    @PostMapping("/add-client")
    public BankResponse addClient(@RequestBody @Valid AddClientDto request) {
        bankService.addClient(request);
        return new BankResponse("OK", null);
    }
}