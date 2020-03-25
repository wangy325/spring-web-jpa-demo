package com.wangy.webmvc.data.jdbc;

import com.wangy.webmvc.config.JdbcConfig;
import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 14:22
 */
@Repository
@Slf4j
public class SpitterRepositoryImpl implements SpitterRepository {

    /**
     * JdbcOperations是{@link JdbcTemplate}的超类（接口）<br>
     * 此处注入JdbcOperation是可行的，也保证了此类和{@link JdbcConfig}的松耦合<br>
     * 此处也可直接注入{@link JdbcTemplate}
     *
     * @see JdbcOperations
     */
    private final JdbcOperations jdbcOperations;

    public SpitterRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private final String SPITTER_INSERT = "INSERT INTO spitter (firstname, lastname, username, password) VALUES(?,?,?,?)";
    private final String SPITTER_SELECT = "SELECT id,firstname,lastname,username,password FROM spitter ";

    @Override
    public Spitter save(Spitter spitter) {
        Integer id = spitter.getId();
        if (null == id) {
            return insertAndGetPrimaryKey(spitter);
//            return insertAndGetPrimaryKey_2(spitter);
        } else {
            // update by id
            jdbcOperations.update("UPDATE spitter SET firstName=?,lastName=?,username=?,password=? WHERE id = ?"
                , spitter.getFirstName()
                , spitter.getLastName()
                , spitter.getUsername()
                , spitter.getPassword()
                , id);
        }
        return spitter;
    }

    /**
     * 通过JdbcTemplate插入spitter并获取主键，主键会放入{@link KeyHolder}中
     *
     * @param spitter spitter will insert into db
     * @return spitter with primary key
     */
    private Spitter insertAndGetPrimaryKey(Spitter spitter) {
        //获取自增主键，jdbc只有此法
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(con -> {
            PreparedStatement ps = con.prepareStatement(SPITTER_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, spitter.getFirstName());
            ps.setString(2, spitter.getLastName());
            ps.setString(3, spitter.getUsername());
            ps.setString(4, spitter.getPassword());
            return ps;
        }, keyHolder);
        Integer key = Objects.requireNonNull(keyHolder.getKey()).intValue();
        spitter.setId(key);
        return spitter;
    }

    /**
     * Inserts a spitter using SimpleJdbcInsert.
     * Involves no direct SQL and is able to return the ID of the newly created Spitter.
     *
     * @param spitter a Spitter to insert into the database
     * @return the ID of the newly inserted Spitter
     */
    private Spitter insertAndGetPrimaryKey_2(Spitter spitter) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcOperations).withTableName("spitter");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>(4, 1.5f);
        args.put("firstname", spitter.getFirstName());
        args.put("lastname", spitter.getLastName());
        args.put("username", spitter.getUsername());
        args.put("password", spitter.getPassword());
        //获取自增主键
        int key = jdbcInsert.executeAndReturnKey(args).intValue();
        spitter.setId(key);
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        //此处可使用内部类或lambda表达式
        // java 8 方法引用
        return jdbcOperations.queryForObject(SPITTER_SELECT + "WHERE username = ?", this::mapRow, username);
    }

    @Override
    public int count() {
        Integer count = jdbcOperations.queryForObject("SELECT count(*) FROM spitter", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Spitter findOne(int id) {
        return jdbcOperations.queryForObject(SPITTER_SELECT + "WHERE id=?", this::mapRow, id);
    }

    @Override
    public List<Spitter> findAll() {
        return jdbcOperations.query(SPITTER_SELECT, this::mapRow);
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
