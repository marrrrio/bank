package bank.repository;

import bank.dto.BankDto;
import bank.dto.MoneyTransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MoneyTransferRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    public void transferMoney(MoneyTransferDto request) {

        BankDto fromClient = namedParameterJdbcTemplate.queryForObject("select * from bank where client_id =:id",
                new MapSqlParameterSource()
                    .addValue("id", request.getFromId()),
                new BeanPropertyRowMapper<>(BankDto.class));

        if (fromClient.getClientMoney().compareTo(request.getSumOfMoney()) < 0) {
            throw new RuntimeException("The client lacks money to transfer");
        }

        BankDto toClient = namedParameterJdbcTemplate.queryForObject("select * from bank where client_id =:id",
                new MapSqlParameterSource()
                        .addValue("id", request.getToId()),
                new BeanPropertyRowMapper<>(BankDto.class));

        namedParameterJdbcTemplate.update("update bank set client_money =:money where client_id =:id",
                new MapSqlParameterSource()
                .addValue("money", fromClient.getClientMoney().subtract(request.getSumOfMoney()))
                .addValue("id", fromClient.getClientId()));

        namedParameterJdbcTemplate.update("update bank set client_money =:money where client_id =:id",
                new MapSqlParameterSource()
                .addValue("money", toClient.getClientMoney().add(request.getSumOfMoney()))
                .addValue("id", toClient.getClientId()));
    }
}