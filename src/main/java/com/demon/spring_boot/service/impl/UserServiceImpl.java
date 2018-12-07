package com.demon.spring_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demon.spring_boot.entity.User;
import com.demon.spring_boot.service.UserService;

/**
 * jdbcTemplate 访问mysql
 * 
 * @author xuliang
 * @since 2018年12月7日 上午11:02:35
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void insert(User user) {
        jdbcTemplate.update("insert into user(id, name, age) values(?, ?, ?)", user.getId(), user.getName(), user.getAge());
    }

    @Override
    public void removeById(Long id) {
        jdbcTemplate.update("delete from user where id=?", id);
    }

    @Override
    public Integer countUser() {
        return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }

    @Override
    public void removeAllUser() {
        jdbcTemplate.update("delete from user");
    }

}
