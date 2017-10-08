/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estore.microclass.entity.MicroClassVideo;
import com.estore.microclass.mapper.MicroClassVideoMapper;
import com.estore.microclass.service.MicroClassVideoService;
import com.estore.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("microClassVideoService")
@Slf4j
public class MicroClassVideoServiceImpl implements MicroClassVideoService {

    @Autowired
    private MicroClassVideoMapper microClassVideoMapper;

    @Override
    public void save(MicroClassVideo data) {
        this.microClassVideoMapper.insert(data);
    }

    @Override
    public void remove(int id) {
        MicroClassVideo mcv = this.microClassVideoMapper.select(id);
        if (null != mcv && StringUtils.isNotBlank(mcv.getNewName())) {
            try {
                FileUtils.forceDelete(new File(Constants.VIDEO_DIR_PATH + mcv.getNewName()));
            } catch (IOException e) {
                log.error("文件删除失败", e);
            }
        }
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