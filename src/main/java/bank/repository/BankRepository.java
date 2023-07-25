package bank.repository;

import bank.dto.AddClientDto;
import bank.dto.BankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BankRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<BankDto> getAllClients() {
        return namedParameterJdbcTemplate.query("select * from bank", new BeanPropertyRowMapper<>(BankDto.class));
    }

    @Transactional
    public void addClient(AddClientDto request) {
        namedParameterJdbcTemplate.update("insert into bank(client_name, client_money, client_age) values (:clientName, :clientMoney, :clientAge)",
                new MapSqlParameterSource()
                .addValue("clientName", request.getClientName())
                .addValue("clientMoney", request.getClientMoney())
                .addValue("clientAge", request.getClientAge()));
    }
}