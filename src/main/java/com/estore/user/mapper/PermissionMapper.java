/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author Tan XuJie
 *
 */
@Deprecated
public interface PermissionMapper {

    List<String> findByRoleCode(@Param("roleCode") String roleCode);
}