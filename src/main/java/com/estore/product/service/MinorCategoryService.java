/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.service;

import java.util.List;

import com.estore.product.entity.MinorCategory;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MinorCategoryService {

    /**
     * 保存小分类信息
     * @param data
     */
    void save(MinorCategory data);

    /**
     * 删除小分类信息
     * @param ids
     */
    void remove(int[] ids);

    /**
     * 更新小分类信息
     * 
     * @param data
     */
    void modify(MinorCategory data);

    /**
     * 查询小分类信息
     * @return
     */
    List<MinorCategory> search(String condition);

    /**
     * 取得小分类详情
     * 
     * @param id
     * @return
     */
    MinorCategory getDetail(int id);
}