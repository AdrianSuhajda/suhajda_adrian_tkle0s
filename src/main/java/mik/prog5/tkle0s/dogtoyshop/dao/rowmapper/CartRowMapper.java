package mik.prog5.tkle0s.dogtoyshop.dao.rowmapper;

import mik.prog5.tkle0s.dogtoyshop.entity.Cart;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CartRowMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong("id"));
        cart.setName(rs.getString("name"));
        cart.setImage(rs.getString("image"));
        cart.setDescription(rs.getString("description"));
        cart.setCost(rs.getInt("cost"));
        cart.setQuantity(rs.getInt("quantity"));
        return cart;
    }
}
