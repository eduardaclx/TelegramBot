package br.com.feltex.bot.telegram;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DadosRowMapper implements RowMapper<Dados> {

    @Override
    public Dados mapRow(ResultSet rs, int rowNum) throws SQLException { 
        Dados dado = new Dados();
        dado.setUsoProcessador(rs.getDouble("usoProcessador"));
        return dado;
    }
}
