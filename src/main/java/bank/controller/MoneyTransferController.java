package bank.controller;

import bank.dto.BankResponse;
import bank.dto.MoneyTransferDto;
import bank.service.BankTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MoneyTransferController {

    private final BankTransferService bankTransferService;

    @PostMapping("/transfer-money")
    public BankResponse transferMoney(@RequestBody @Valid MoneyTransferDto request) {
        bankTransferService.transferMoney(request);
        return new BankResponse("OK", null);
    }
}