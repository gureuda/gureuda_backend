package me.jae57.gureuda_v1.dao;

import me.jae57.gureuda_v1.model.MyUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addAuth(String userName, String authName) {
        String query = "INSERT INTO auth(user_name, authority_name) VALUES (?,?)";
        try{
            return jdbcTemplate.update(query,userName,authName);
        }catch(Exception e){
            throw e;
        }
    }

    public List<String> getAuthsByUserName(String userName) {
        String query = "SELECT authority_name FROM auth WHERE user_name=?";
        try{
            return jdbcTemplate.query(query,
                    (result, rowNum) -> result.getString("authority_name")
                    ,userName);
        }catch(Exception e){
            throw e;
        }
    }
}
