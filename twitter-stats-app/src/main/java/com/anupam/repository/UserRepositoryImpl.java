package com.anupam.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static Logger logger = LogManager.getLogger(UserRepository.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Object[]> getUsersWithHighestFollowers() {
        try {
            String sql = "select top 5 distinct " +
                    "USER_NAME," +
                    "FOLLOWERS_COUNT " +
                    "from TB_TWEET " +
                    "order by FOLLOWERS_COUNT desc";
            logger.debug("Executing: " + sql);
            List<Object[]> list = jdbcTemplate.query(sql, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int i) throws SQLException {
                    Object[] objects = new Object[3];
                    objects[0] = rs.getString("USER_NAME");
                    objects[1] = rs.getInt("FOLLOWERS_COUNT");
                    return objects;
                }
            });
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    @Override
    public List<Object[]> getCountByDateAndHour() {
        try {
            String sql = "select " +
                    "FORMATDATETIME(creation_date,'yyyy-MM-dd') as CREATION_DATE, " +
                    "HOUR(creation_date) as CREATION_HOUR, " +
                    "count(*) as COUNT " +
                    "from tb_tweet " +
                    "group by creation_date,CREATION_HOUR " +
                    "order by creation_date desc ";
            logger.debug("Executing: " + sql);
            List<Object[]> list = jdbcTemplate.query(sql, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int i) throws SQLException {
                    Object[] objects = new Object[3];
                    objects[0] = rs.getString("CREATION_DATE");
                    objects[1] = rs.getString("CREATION_HOUR");
                    objects[2] = rs.getInt("COUNT");
                    return objects;
                }
            });
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    @Override
    public List<Object[]> getCountByTagAndLanguage() {
        try {
            String sql = "select HASH_TAG," +
                    "LANGUAGE, " +
                    "count(LANGUAGE) as COUNT " +
                    "from TB_TWEET " +
                    "where HASH_TAG in (select distinct HASH_TAG from TB_TWEET) " +
                    "group by LANGUAGE,HASH_TAG order by HASH_TAG ";
            logger.debug("Executing: " + sql);
            List<Object[]> list = jdbcTemplate.query(sql, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int i) throws SQLException {
                    Object[] objects = new Object[3];
                    objects[0] = rs.getString("HASH_TAG");
                    objects[1] = rs.getString("LANGUAGE");
                    objects[2] = rs.getInt("COUNT");
                    return objects;
                }
            });
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }


}
