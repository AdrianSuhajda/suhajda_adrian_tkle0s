package mik.prog5.tkle0s.dogtoyshop.dao.rowmapper;

import mik.prog5.tkle0s.dogtoyshop.entity.Payment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getLong("id"));
        payment.setName(rs.getString("name"));
        payment.setCardNumber(rs.getInt("cardNumber"));
        payment.setExpireDate(rs.getInt("expireDate"));
        payment.setCvc(rs.getInt("cvc"));

        return payment;
    }
}
