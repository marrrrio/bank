package bank.service;


import bank.dto.MoneyTransferDto;
import bank.repository.MoneyTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankTransferService {

    private final MoneyTransferRepository moneyTransferRepository;

    public void transferMoney(MoneyTransferDto request) {
        moneyTransferRepository.transferMoney(request);
    }
}