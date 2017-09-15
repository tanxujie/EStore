/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.estore.user.dto.LoginAccount;
import com.estore.user.dto.LowerAgent;
import com.estore.user.dto.UserPasswordDto;
import com.estore.user.entity.User;
import com.estore.user.mapper.UserMapper;
import com.estore.user.service.UserService;
import com.estore.utils.Constants;

/**
 * @author Tan XuJie
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Override
    public List<User> search(User sdata) {
        return this.userMapper.selectAll(sdata);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return this.userMapper.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void save(User data) {
        this.userMapper.insert(data);
    }

    @Override
    public void remove(int[] ids) {
        this.userMapper.delete(ids);
    }

    @Override
    public void modify(User data) {
        this.userMapper.update(data);
    }

    @Override
    public User getDetail(int id) {
        return this.userMapper.select(id);
    }

    @Override
    public void modifyPassword(UserPasswordDto data) {
        this.userMapper.updatePassword(data);
    }

    @Override
    public LoginAccount authenticate(String phoneNumber, String password) {
        LoginAccount account = this.userMapper.getLoginAccountByPhoneNumberAndPassword(phoneNumber, password);
        if (account != null) {
            String token = UUID.randomUUID().toString();
            // token有效期为1个月
            this.cacheManager.getCache(Constants.GLOBAL_AUTH_TOKENS_CACHE).put(token, LocalDate.now().plusMonths(1));
            account.setAuthToken(token);
            return account;
        }
        return null;
    }

    @Override
    public List<LowerAgent> searchLowerAgents(int supperAgentId, String condition) {
        return this.userMapper.selectAllLowerAgents(supperAgentId, condition);
    }

    @Override
    public void save(LowerAgent agent) {
        User user = new User();
        user.setPhoneNumber(agent.getPhoneNumber());
        user.setPassword(agent.getPhoneNumber());
        user.setWechatNumber(agent.getWechatNumber());
        user.setName(agent.getName());
        user.setSupperAgentId(1);
        this.userMapper.insert(user);
    }
}