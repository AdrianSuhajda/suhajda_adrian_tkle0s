package mik.prog5.tkle0s.dogtoyshop.dao.rowmapper;

import mik.prog5.tkle0s.dogtoyshop.entity.Toy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ToyRowMapper implements RowMapper<Toy> {
    @Override
    public Toy mapRow(ResultSet rs, int rowNum) throws SQLException {
        Toy toy = new Toy();
        toy.setId(rs.getLong("id"));
        toy.setName(rs.getString("name"));
        toy.setImage(rs.getString("image"));
        toy.setDescription(rs.getString("description"));
        toy.setCost(rs.getInt("cost"));
        return toy;
    }
}
