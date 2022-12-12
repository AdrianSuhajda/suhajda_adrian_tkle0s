package mik.prog5.tkle0s.dogtoyshop.dao;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.rowmapper.ToyRowMapper;
import mik.prog5.tkle0s.dogtoyshop.entity.Toy;
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
public class ToyJdbcTemplate implements ToyDao{

    private final JdbcTemplate jdbcTemplate;
    private final ToyRowMapper rowMapper;

    @Override
    public Toy create(Toy toy) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory psFactory = new PreparedStatementCreatorFactory(
                    "INSERT INTO toy (name, image, description, cost) VALUES (?, ?, ?, ?)"
                    , Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER);
            psFactory.setReturnGeneratedKeys(true);
            PreparedStatementCreator ps = psFactory.newPreparedStatementCreator(
                    List.of(toy.getName(), toy.getImage(), toy.getDescription(), toy.getCost()));

            return ps.createPreparedStatement(connection);
        }, keyHolder);
        return this.findById(((Integer) keyHolder.getKeys().get("id")).longValue());
    }

    @Override
    public Toy findById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM toy t WHERE t.id = ?", this.rowMapper, id);
    }

    @Override
    public List<Toy> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM toy", this.rowMapper);
    }

    @Override
    public Toy update(Toy toy) {
        this.jdbcTemplate.update("UPDATE toy SET name = ?, image = ?, description = ?, cost = ?, id = ?"
                , toy.getName(), toy.getImage(), toy.getDescription(), toy.getCost(), toy.getId());
        return this.findById(toy.getId());
    }

    @Override
    public boolean delete(Long id) {
        return this.jdbcTemplate.update("DELETE FROM toy t WHERE t.id = ?", id) == 1;
    }
}
