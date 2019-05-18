package me.jae57.gureuda_v1.dao;

import me.jae57.gureuda_v1.model.MyUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser(MyUser myUser) {
        String query = "INSERT INTO user(user_name, user_pass) VALUES (?,?)";
        try{
            return jdbcTemplate.update(query,
                    myUser.getUsername(),
                    myUser.getUserPass());
        }catch(Exception e){
            throw e;
        }
    }

    public MyUser getUser(String userName) {
        String query = "SELECT * FROM user WHERE user_name=?";
        try{
            return jdbcTemplate.queryForObject(query, (result, rowNum) -> MyUser
                        .builder()
                        .userName(result.getString("user_name"))
                        .userPass(result.getString("user_pass"))
                        .build()
                    ,userName);
        }catch(Exception e){
            throw e;
        }
    }
}
