/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estore.microclass.entity.MicroClassVideo;
import com.estore.microclass.mapper.MicroClassVideoMapper;
import com.estore.microclass.service.MicroClassVideoService;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("microClassVideoService")
public class MicroClassVideoServiceImpl implements MicroClassVideoService {

    @Autowired
    private MicroClassVideoMapper microClassVideoMapper;

    @Override
    public void save(MicroClassVideo data) {
        this.microClassVideoMapper.insert(data);
    }

    @Override
    public void remove(int id) {
        this.microClassVideoMapper.delete(id);
    }

    @Override
    public void modify(MicroClassVideo data) {
        this.microClassVideoMapper.update(data);
    }

    @Override
    public List<MicroClassVideo> search(String condition) {
        return this.microClassVideoMapper.selectAll(condition);
    }

    @Override
    public MicroClassVideo getDetail(int id) {
        return this.microClassVideoMapper.select(id);
    }

    @Override
    public List<MicroClassVideo> searchByMicroClassId(int microClassId) {
        return this.microClassVideoMapper.selectAllByMicroClassId(microClassId);
    }
}