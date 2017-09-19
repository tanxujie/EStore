/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.service;

import java.util.List;

import com.estore.product.entity.MajorCategory;
import com.estore.utils.Option;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MajorCategoryService {

    List<Option> getAllOptions();

    /**
     * 
     * @param data
     */
    void save(MajorCategory data);

    /**
     * 
     * @param ids
     */
    void remove(int[] ids);

    /**
     * 
     * @param data
     */
    void modify(MajorCategory data);

    /**
     * 
     * @return
     */
    List<MajorCategory> search(String condition);

    /**
     * 
     * @param id
     * @return
     */
    MajorCategory getDetail(int id);
}