/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estore.product.entity.MinorCategory;
import com.estore.product.mapper.MinorCategoryMapper;
import com.estore.product.service.MinorCategoryService;
import com.estore.utils.Option;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("minorCategoryService")
public class MinorCategoryServiceImpl implements MinorCategoryService {

    @Autowired
    private MinorCategoryMapper minorCategoryMapper;

    @Override
    public void save(MinorCategory data) {
        this.minorCategoryMapper.insert(data);
    }

    @Override
    public void remove(int[] ids) {
        this.minorCategoryMapper.delete(ids);
    }

    @Override
    public void modify(MinorCategory data) {
        this.minorCategoryMapper.update(data);
    }

    @Override
    public List<MinorCategory> search(String condition) {
        return this.minorCategoryMapper.selectAll(condition);
    }

    @Override
    public MinorCategory getDetail(int id) {
        return this.minorCategoryMapper.select(id);
    }

    @Override
    public List<Option> getAllOptions(int majorCategoryId) {
        List<MinorCategory> results = this.minorCategoryMapper.selectAllByMajorCategoryId(majorCategoryId);
        if (CollectionUtils.isEmpty(results)) {
            return new ArrayList<>(0);
        }

        List<Option> options = new ArrayList<>(results.size());
        for (MinorCategory result : results) {
            options.add(new Option(result.getId(), result.getName()));
        }
        return options;
    }
}