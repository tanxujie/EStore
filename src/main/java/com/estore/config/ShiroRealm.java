/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.estore.user.dto.UserDto;
import com.estore.user.entity.User;
import com.estore.user.service.UserService;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserDto userDto = (UserDto)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        //authInfo.addStringPermissions(userDto.getPermissionCodes());
        authInfo.addRole(userDto.getRoleCode());
        return null;
    }

    /**
     * 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        User user = this.userService.findByPhoneNumber(userToken.getUsername());
        if (null == user) {
            throw new AuthenticationException("None user exists.");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), null);
    }
}