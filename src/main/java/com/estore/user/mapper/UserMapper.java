/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.estore.user.dto.LoginAccount;
import com.estore.user.dto.LowerAgent;
import com.estore.user.dto.UserPasswordDto;
import com.estore.user.entity.User;

/**
 * @author Tan XuJie
 *
 */
public interface UserMapper {

    /**
     * 
     * @param entity
     * @return
     */
    int insert(User entity);

    /**
     * 
     * @param ids
     * @return
     */
    int delete(int[] ids);

    /**
     * 
     * @param entity
     * @return
     */
    int update(User entity);

    /**
     * 
     * @param data
     * @return
     */
    int updatePassword(UserPasswordDto data);

    /**
     * 
     * @param condition
     * @return
     */
    List<User> selectAll(User condition);

    /**
     * 
     * @param id
     * @return
     */
    User select(int id);

    /**
     * 
     * @param phoneNumber
     * @param password
     * @return
     */
    LoginAccount getLoginAccountByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    /**
     * 
     * @param phoneNumber
     * @return
     */
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * 
     * @param supperAgentId
     * @return
     */
    List<LowerAgent> selectAllLowerAgents(@Param("supperAgentId") int supperAgentId, @Param("condition") String condition);
}