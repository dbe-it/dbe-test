package spittr.data.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spittr.data.Spitter;


@Repository
public class SpitterRepositoryJDBC implements SpitterRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findFirstnameByUsername(String username) {
        String sql = "select firstname from spitters where username = '" + username + "'";
        return jdbcTemplate.queryForObject(sql, String.class);
//        String name = jdbcTemplate.queryForObject(sql,new Object[]{username},String.class);
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }

    @Override
    public Spitter findById(Long id) {
        return null;
    }

    @Override
    public Spitter saveSpitter(Spitter spitter) {
        return null;
    }
}
