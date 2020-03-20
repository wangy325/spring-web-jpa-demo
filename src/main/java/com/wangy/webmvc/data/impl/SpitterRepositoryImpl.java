package com.wangy.webmvc.data.impl;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 14:22
 */
@Repository
@Slf4j
public class SpitterRepositoryImpl implements SpitterRepository {

    private final JdbcOperations jdbcOperations;

    public SpitterRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Spitter register(Spitter spitter) {
        // TODO h2无法主键自增
        String sql = "INSERT INTO SPITTER VALUES(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcOperations.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, spitter.getFirstName());
            ps.setString(2, spitter.getLastName());
            ps.setString(3, spitter.getUsername());
            ps.setString(4, spitter.getPassword());
            return ps;
        }, keyHolder);
        int key = (int) keyHolder.getKey();
        spitter.setId(key);
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {

        String sql = "SELECT * FROM SPITTER WHERE USERNAME = ?";
        // java 8 方法引用
        return jdbcOperations.queryForObject(sql, this::mapRow, username);
    }

    private Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spitter spitter = new Spitter(rs.getInt("id")
            , rs.getString("firstName")
            , rs.getString("lastName")
            , rs.getString("username")
            , rs.getString("password")
        );
        return spitter;
    }

}
