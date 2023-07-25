package bank.repository;

import bank.dto.AddClientDto;
import bank.dto.BankDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.http.HttpHeaders;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.extractProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private BankRepository bankRepository;

    @Captor
    ArgumentCaptor<MapSqlParameterSource> mapSqlParameterSourceArgumentCaptor;

    @Test
    void getAllClients() {
        List<BankDto> list3 = List.of(new BankDto(1L, 23, new BigDecimal(1000000), "Mariia"));
        when(namedParameterJdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(list3);
        List<BankDto> getAllCl = bankRepository.getAllClients();
        assertThat(getAllCl)
                .isEqualTo(list3);
        verify(namedParameterJdbcTemplate).query(eq("select * from bank"), any(BeanPropertyRowMapper.class));
    }

    @Test
    void addClient() {

        AddClientDto request = new AddClientDto(18, new BigDecimal(5000), "Dima");

        bankRepository.addClient(request);

        verify(namedParameterJdbcTemplate).update(eq("insert into bank(client_name, client_money, client_age) values (:clientName, :clientMoney, :clientAge)"),
                mapSqlParameterSourceArgumentCaptor.capture());

        MapSqlParameterSource mapSqlParameterSource = mapSqlParameterSourceArgumentCaptor.getValue();

        assertThat(mapSqlParameterSource)
                .extracting(e -> e.getValue("clientName"),
                        e -> e.getValue("clientMoney"),
                        e -> e.getValue("clientAge")
                )
                .contains(request.getClientName(), request.getClientMoney(), request.getClientAge());

    }
}