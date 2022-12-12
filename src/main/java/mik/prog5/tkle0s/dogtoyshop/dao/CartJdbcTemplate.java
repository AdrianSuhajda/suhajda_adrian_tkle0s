package mik.prog5.tkle0s.dogtoyshop.dao;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.rowmapper.CartRowMapper;
import mik.prog5.tkle0s.dogtoyshop.entity.Cart;
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
public class CartJdbcTemplate implements CartDao{

    private final JdbcTemplate jdbcTemplate;
    private final CartRowMapper rowMapper;

    @Override
    public Cart create(Cart cart) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory(
                    "INSERT INTO cart (name, image, description, cost, quantity) VALUES (?, ?, ?, ?, ?)"
                    , Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER);
            psFactory.setReturnGeneratedKeys(true);
            PreparedStatementCreator ps = psFactory.newPreparedStatementCreator(
                    List.of(cart.getName(), cart.getImage(), cart.getDescription(), cart.getCost(), cart.getQuantity()));

            return ps.createPreparedStatement(connection);
        }, keyHolder);
        return this.findById(((Integer) keyHolder.getKeys().get("id")).longValue());
    }

    @Override
    public Cart findById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM cart c WHERE c.id = ?", this.rowMapper, id);
    }

    @Override
    public List<Cart> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM cart", this.rowMapper);
    }

    @Override
    public Cart update(Cart cart) {
        this.jdbcTemplate.update("UPDATE cart SET name = ?, image = ?, description = ?, cost = ?, quantity = ?, id = ?"
        , cart.getName(), cart.getImage(), cart.getDescription(), cart.getCost(), cart.getQuantity(), cart.getId());
        return this.findById(cart.getId());
    }

    @Override
    public boolean delete(Long id) {
        return this.jdbcTemplate.update("DELETE FROM cart c WHERE c.id = ?", id) == 1;
    }

    @Override
    public boolean deleteAll() {
        return this.jdbcTemplate.update("DELETE FROM cart") == 1;
    }

    @Override
    public Integer getSum() {
        int sum = 0;
        List<Cart> carts = this.findAll();

        for (Cart cart: carts) {
            sum += cart.getCost() * cart.getQuantity();
        }
        
        return sum;
    }
}
