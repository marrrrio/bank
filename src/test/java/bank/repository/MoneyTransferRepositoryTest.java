package bank.repository;

import bank.dto.BankDto;
import bank.dto.MoneyTransferDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoneyTransferRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private MoneyTransferRepository moneyTransferRepository;

    @Test
    void transferMoneyWithException() {

        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class)))
                .thenReturn(new BankDto(1L, 18, new BigDecimal(1000), "Dima"));

        MoneyTransferDto moneyTransferDto = new MoneyTransferDto(1L, 2L, new BigDecimal(1500));

        RuntimeException runtimeException =
                assertThrows(RuntimeException.class, () -> moneyTransferRepository.transferMoney(moneyTransferDto));

        assertThat(runtimeException.getMessage())
                .isNotNull()
                .isEqualTo("The client lacks money to transfer");

        verify(namedParameterJdbcTemplate).queryForObject(anyString(), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class));
    }
}