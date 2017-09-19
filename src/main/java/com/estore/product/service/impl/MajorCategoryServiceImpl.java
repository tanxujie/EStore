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

import com.estore.product.entity.MajorCategory;
import com.estore.product.mapper.MajorCategoryMapper;
import com.estore.product.service.MajorCategoryService;
import com.estore.utils.Option;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("majorCategoryService")
public class MajorCategoryServiceImpl implements MajorCategoryService {

    @Autowired
    private MajorCategoryMapper majorCategoryMapper;

    @Override
    public void save(MajorCategory data) {
        this.majorCategoryMapper.insert(data);
    }

    @Override
    public void remove(int[] ids) {
        this.majorCategoryMapper.delete(ids);
    }

    @Override
    public void modify(MajorCategory data) {
        this.majorCategoryMapper.update(data);
    }

    @Override
    public List<MajorCategory> search(String condition) {
        return this.majorCategoryMapper.selectAll(condition);
    }

    @Override
    public MajorCategory getDetail(int id) {
        return this.majorCategoryMapper.select(id);
    }

    @Override
    public List<Option> getAllOptions() {
        List<MajorCategory> results = this.majorCategoryMapper.selectAll(null);
        if (CollectionUtils.isEmpty(results)) {
            return new ArrayList<>(0);
        }

        List<Option> options = new ArrayList<>(results.size());
        for (MajorCategory result : results) {
            Option option = new Option(result.getId(), result.getName());
            options.add(option);
        }
        return options;
    }
}