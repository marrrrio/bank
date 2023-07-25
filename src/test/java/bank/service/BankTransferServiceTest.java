package bank.service;

import bank.dto.MoneyTransferDto;
import bank.repository.MoneyTransferRepository;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BankTransferServiceTest {

    @Mock
    private MoneyTransferRepository moneyTransferRepository;

    @InjectMocks
    private BankTransferService bankTransferService;

    @Test
    void transferMoney() {
        MoneyTransferDto mTrDto = new MoneyTransferDto(2L, 3L, new BigDecimal(100000));
        bankTransferService.transferMoney(mTrDto);
        verify(moneyTransferRepository).transferMoney(mTrDto);
    }
}