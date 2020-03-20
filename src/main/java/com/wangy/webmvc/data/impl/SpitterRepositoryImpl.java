package com.wangy.webmvc.data.impl;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {

        String sql = "SELECT * FROM SPITTER WHERE USERNAME = ?";

        return jdbcOperations.queryForObject(sql, this::mapRow, username);


        /**  jdbc 样板式代码*********************************/
        /*Spitter spitter;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = datasource.getConnection();
            String sql = "SELECT * FROM SPITTER WHERE USERNAME = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                spitter = new Spitter();
                spitter.setId(resultSet.getInt("id"));
                spitter.setFirstName(resultSet.getString("firstName"));
                spitter.setLastName(resultSet.getString("lastName"));
                spitter.setUsername(resultSet.getString("username"));
                spitter.setPassword(resultSet.getString("password"));
                return spitter;
            }
        } catch (SQLException sqle) {
            log.error(sqle.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
        */
        /**  end ***********************************/
    }

    private Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Spitter(rs.getInt("id")
            , rs.getString("firstName")
            , rs.getString("lastName")
            , rs.getString("username")
            , rs.getString("password")
        );
    }
}
