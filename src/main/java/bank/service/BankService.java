package bank.service;

import bank.dto.AddClientDto;
import bank.dto.BankDto;
import bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    
    private final BankRepository bankRepository;

    public List<BankDto> getAllClients() {
        return bankRepository.getAllClients();
    }

    public void addClient(AddClientDto request) {
        bankRepository.addClient(request);
    }
}