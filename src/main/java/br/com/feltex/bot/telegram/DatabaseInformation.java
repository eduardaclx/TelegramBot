package br.com.feltex.bot.telegram;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInformation {

    private final BasicDataSource dataSourceSql = new BasicDataSource();
    protected JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceSql);

    public DatabaseInformation() {

        dataSourceSql.setDriverClassName(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        dataSourceSql.setUrl(
                "jdbc:sqlserver://nocrash.database.windows.net:1433;database=nocrash;"
                + "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
        dataSourceSql.setUsername("nocrash");
        dataSourceSql.setPassword("#Gfgrupo4");
    }

    public String usoProcessadorDiario(String token) {

        Dados selectUsoProcessador = jdbcTemplate.queryForObject(
                "SELECT AVG(usoProcessador)AS 'usoProcessador' "
                + "FROM Desktop JOIN Hardware ON fkDesktop = ' " + token + "'"
                + "JOIN Dado ON fkHardware = idHardware "
                + "WHERE columnData <= (SELECT CURRENT_TIMESTAMP) "
                + "AND columnData >= DATEADD(day, -1, GETDATE())",
                new DadosRowMapper());

        return selectUsoProcessador.toString();
    }

    public String usoProcessadorSemanal(String token) {

        Dados selectUsoProcessador = jdbcTemplate.queryForObject(
                "SELECT AVG(usoProcessador)AS 'usoProcessador' "
                + "FROM Desktop JOIN Hardware ON fkDesktop = ' " + token + "'"
                + "JOIN Dado ON fkHardware = idHardware "
                + "WHERE columnData <= (SELECT CURRENT_TIMESTAMP) "
                + "AND columnData >= DATEADD(day, -7, GETDATE())",
                new DadosRowMapper());

        return selectUsoProcessador.toString();
    }

    public String desktopInformation(String token) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM Desktop JOIN Hardware ");
        sb.append("ON fkDesktop = idDesktop WHERE fkDesktop = ' ");
        sb.append(token).append("';");
        
        System.out.println("toaqui" + sb.toString());

        Desktop desktopInfo = jdbcTemplate.queryForObject(sb.toString(),
                new DesktopRowMapper());

        return desktopInfo.toString();
    }
}
