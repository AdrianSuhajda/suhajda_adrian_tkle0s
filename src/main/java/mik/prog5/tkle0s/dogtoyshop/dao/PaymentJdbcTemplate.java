package mik.prog5.tkle0s.dogtoyshop.dao;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.rowmapper.PaymentRowMapper;
import mik.prog5.tkle0s.dogtoyshop.entity.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PaymentJdbcTemplate implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;
    private final PaymentRowMapper rowMapper;

    @Override
    public Payment create(Payment payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory(
                    "INSERT INTO payment (name, cardNumber, expireDate, cvc) VALUES (?, ?, ?, ?)"
                    , Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER);
            psFactory.setReturnGeneratedKeys(true);
            PreparedStatementCreator ps = psFactory.newPreparedStatementCreator(
                    List.of(payment.getName(), payment.getCardNumber(), payment.getExpireDate(), payment.getCvc()));

            return ps.createPreparedStatement(connection);
        }, keyHolder);
        return this.findById(((Integer) keyHolder.getKeys().get("id")).longValue());
    }

    @Override
    public Payment findById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM payment p WHERE p.id = ?", this.rowMapper, id);
    }

    @Override
    public List<Payment> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM payment", this.rowMapper);
    }

    @Override
    public Payment update(Payment payment) {
        this.jdbcTemplate.update("UPDATE payment SET name = ?, cardNumber = ?, expireDate = ?, cvc = ?, id = ?"
                , payment.getName(), payment.getCardNumber(), payment.getExpireDate(), payment.getCvc(), payment.getId());
        return this.findById(payment.getId());
    }

    @Override
    public boolean delete(Long id) {
        return this.jdbcTemplate.update("DELETE FROM payment p WHERE p.id = ?", id) == 1;
    }
}
