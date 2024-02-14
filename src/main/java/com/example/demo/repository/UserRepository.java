package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.utilis.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String query = "SELECT * FROM userTable";

        RowMapper<User> rowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(query, rowMapper);
    }

    public User save(User user) {
        String saveQuery = "INSERT INTO userTable VALUES (Null, ?, ?)";
        jdbc.update(saveQuery, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String deleteQuery = "DELETE FROM userTable WHERE id = ?";
        jdbc.update(deleteQuery, id);
    }

    public void updateUser(User user) {
        String updateQuery = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(updateQuery, user.getFirstName(), user.getLastName(), user.getId());
    }

    //ToDo понять в чем ошибка и исправить
    public User getOne(Integer id) {
        String getQuery = "SELECT * FROM userTable WHERE id = ?";

        return jdbc.query(getQuery, new Object[]{id}, new UserMapper())
                .stream().findFirst().orElse(null);
    }

}
