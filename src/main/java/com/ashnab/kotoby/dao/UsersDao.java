package com.ashnab.kotoby.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UsersDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersDao(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createUser(String username)
    {
        jdbcTemplate.update("CREATE table users(username varchar(255) not null, password varchar(255), enabled boolean);");
        jdbcTemplate.update("INSERT into users(username,password,enabled) values(?,?,true)",username, RandomStringUtils.randomAlphanumeric(8));
        jdbcTemplate.update("CREATE table authorities(username varchar(255) not null, authority varchar(50));");
        jdbcTemplate.update("INSERT into authorities(username,authority) values(?,?)",username,"USER");
    }
}
