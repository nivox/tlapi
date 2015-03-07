package com.github.nivox.tlapi.dao;

import com.github.nivox.tlapi.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUser(rs.getString("user"));
        user.setName(rs.getString("name"));
        user.setDescription(rs.getString("description"));
        user.setLocation(rs.getString("location"));
        return user;
    }
}
