/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.service;

import java.util.List;

import com.estore.user.dto.LoginAccount;
import com.estore.user.dto.LowerAgent;
import com.estore.user.dto.UserPasswordDto;
import com.estore.user.entity.User;

/**
 * @author Tan XuJie
 *
 */
public interface UserService {

    /**
     * 
     * @param phoneNumber
     * @param password
     * @return
     */
    LoginAccount authenticate(String phoneNumber, String password);

    /**
     * 
     * @param data
     */
    void save(User data);

    void save(LowerAgent agent);

    /**
     * 
     * @param ids
     */
    void remove(int[] ids);

    /**
     * 
     * @param data
     */
    void modify(User data);

    /**
     * 
     * @param data
     */
    void modifyPassword(UserPasswordDto data);

    /**
     * 
     * @param sdata
     * @return
     */
    List<User> search(User sdata);

    /**
     * 
     * @param id
     * @return
     */
    User getDetail(int id);

    /**
     * 
     * @param phoneNumber
     * @return
     */
    User findByPhoneNumber(String phoneNumber);

    /**
     * 
     * @param supperAgentId
     * @return
     */
    List<LowerAgent> searchLowerAgents(int supperAgentId, String condition);

    /**
     * 
     * @param supperAgentId
     * @return
     */
    int getLowerAgentsCount(int supperAgentId);
}