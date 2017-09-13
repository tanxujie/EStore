/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.mapper;

import java.util.List;

import com.estore.product.entity.MinorCategory;

/**
 * @author Tan XuJie
 *
 */
public interface MinorCategoryMapper {
    /**
     * 
     * @param searchCondition
     * @return
     */
    List<MinorCategory> selectAll(String searchCondition);

    /**
     * 
     * @param id
     * @return
     */
    MinorCategory select(int id);

    /**
     * 
     * @param entity
     * @return
     */
    int insert(MinorCategory entity);

    /**
     * 
     * @param ids
     * @return
     */
    int delete(int[] ids);

    /**
     * 
     * @param majorCategoryIds
     * @return
     */
    int deleteByMajorCategoryIds(int[] majorCategoryIds);

    /**
     * 
     * @param entity
     * @return
     */
    int update(MinorCategory entity);
}