/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.mapper;

import java.util.List;

import com.estore.product.entity.MajorCategory;

/**
 * @author Tan XuJie
 *
 */
public interface MajorCategoryMapper {

    /**
     * 
     * @param searchCondition
     * @return
     */
    List<MajorCategory> selectAll(String searchCondition);

    /**
     * 
     * @param id
     * @return
     */
    MajorCategory select(int id);

    /**
     * 
     * @param entity
     * @return
     */
    int insert(MajorCategory entity);

    /**
     * 
     * @param ids
     * @return
     */
    int delete(int[] ids);

    /**
     * 
     * @param majorCategory
     * @return
     */
    int update(MajorCategory entity);
}