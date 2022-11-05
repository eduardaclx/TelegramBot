package br.com.feltex.bot.telegram;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DesktopRowMapper implements RowMapper<Desktop> {

    @Override
    public Desktop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Desktop desk = new Desktop();
        desk.setNomeDesktop(rs.getString("nomeDesktop"));
        desk.setNomeProcessador(rs.getString("NomeProcessador"));
        desk.setFabricante(rs.getString("fabricante"));
        desk.setFrequencia(rs.getString("frequencia"));
        desk.setQntDisco(rs.getInt("qntDisco"));
        desk.setMemoriaTotal(rs.getLong("memoriaTotal"));
        return desk;
    }    
}
