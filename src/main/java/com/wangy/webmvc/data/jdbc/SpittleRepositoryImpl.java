package com.wangy.webmvc.data.jdbc;

import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spitter;
import com.wangy.webmvc.data.bean.Spittle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 21:27
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    private JdbcTemplate jdbcTemplate;

    public SpittleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SPITTLE_SELECT = "SELECT t1.*, t2.firstName, t2.lastName, t2.username FROM spittle t1  LEFT JOIN  spitter  t2 ON t1.spitterId = t2.id ";
    private final String SPITTLE_SELECT_BY_ID = SPITTLE_SELECT + "AND t1.id = ?";
    private final String SPITTLE_SELECT_BY_SPITTER_ID = SPITTLE_SELECT + "AND t2.id = ? ORDER BY t1.time DESC";
    private final String SPITTLE_SELECT_RECENT_SPITTLES = SPITTLE_SELECT + "ORDER BY t1.time DESC LIMIT ?";
    private final String SPITTLE_SELECT_AFTER = SPITTLE_SELECT + "AND t1.id < ? ORDER BY t1.time DESC LIMIT ?";
    private final String SPITTLE_DEL = "DELETE FROM spittle WHERE id = ?";

    private Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
        //column label 大小写不敏感
        long id = rs.getLong("id");
        int spitterid = rs.getInt("spitterId");
        String message = rs.getString("message");
        Time time = rs.getTime("time");
        double latitude = rs.getDouble("latitude");
        double longitude = rs.getDouble("longitude");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String username = rs.getString("username");
        String password = rs.getString("password");
        Spitter spitter = new Spitter(spitterid,firstName,lastName,username,password);
        return new Spittle(id,spitter, message,time,latitude,longitude);
    }


    @Override
    public List<Spittle> getSpittles(long max, int count) {
        return jdbcTemplate.query(SPITTLE_SELECT_AFTER, this::mapRow, max, count);
    }

    @Override
    public Spittle findById(long id) {
        return jdbcTemplate.queryForObject(SPITTLE_SELECT_BY_ID, this::mapRow, id);
    }

    @Override
    public List<Spittle> findBySpitterId(int spitterId) {
        return jdbcTemplate.query(SPITTLE_SELECT_BY_SPITTER_ID, this::mapRow, spitterId);
    }

    @Override
    public long count() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) from spittle", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public List<Spittle> findRecent() {
        return jdbcTemplate.query(SPITTLE_SELECT + "ORDER BY t1.time DESC LIMIT 10", this::mapRow);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return jdbcTemplate.query(SPITTLE_SELECT_RECENT_SPITTLES, this::mapRow, count);
    }

    @Override
    public Spittle save(Spittle spittle) {
        // not allowed to update a spittle content
        long key = insertAndReturnKey(spittle);
        spittle.setId(key);
        return spittle;
    }

    private long insertAndReturnKey(Spittle spittle) {
        SimpleJdbcInsert sji = new SimpleJdbcInsert(jdbcTemplate).withTableName("spittle");
        sji.setGeneratedKeyName("id");
        Map<String, Object> params = new HashMap<>(5,1f);
        params.put("spitterId", spittle.getSpitter().getId());
        params.put("message", spittle.getMessage());
        params.put("time",spittle.getTime());
        params.put("latitude",spittle.getLatitude());
        params.put("longitude",spittle.getLongitude());
        return sji.executeAndReturnKey(params).longValue();
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(SPITTLE_DEL);
    }
}
