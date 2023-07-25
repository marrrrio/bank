package bank.service;

import bank.dto.AddClientDto;
import bank.dto.BankDto;
import bank.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    @Test
    void getAllClients() {
        List<BankDto> list1 = List.of(new BankDto(1L, 18, new BigDecimal(100000), "Maria"));
        when(bankRepository.getAllClients())
                .thenReturn(list1);
        List<BankDto> allClients = bankService.getAllClients();
        assertThat(allClients)
                .isEqualTo(list1);
        verify(bankRepository).getAllClients();
    }

    @Test
    void addClient() {
        AddClientDto clientDto = new AddClientDto(18, new BigDecimal(10000), "Nataliia");
        bankService.addClient(clientDto);
        verify(bankRepository).addClient(clientDto);
    }
}