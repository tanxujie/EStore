/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estore.microclass.entity.MicroClass;
import com.estore.microclass.entity.MicroClassVideo;
import com.estore.microclass.mapper.MicroClassMapper;
import com.estore.microclass.mapper.MicroClassVideoMapper;
import com.estore.microclass.service.MicroClassService;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Service("microClassService")
public class MicroClassServiceImpl implements MicroClassService {

    @Autowired
    private MicroClassMapper microClassMapper;

    @Autowired
    private MicroClassVideoMapper microClassVideoMapper;

    @Override
    public void save(MicroClass data) {
        this.microClassMapper.insert(data);
    }

    @Override
    public void remove(int[] ids) {
        this.microClassMapper.delete(ids);
    }

    @Override
    public void modify(MicroClass data) {
        this.microClassMapper.update(data);
    }

    @Override
    public List<MicroClass> search(String condition) {
        return this.microClassMapper.selectAll(condition);
    }

    @Override
    public MicroClass getDetail(int id) {
        return this.microClassMapper.select(id);
    }

    /**
     * 为手机端查询微客堂视频
     */
    @Override
    public List<MicroClass> searchForApp(String authToken) {
        List<MicroClass> results = this.microClassMapper.selectAll(null);
        if (CollectionUtils.isNotEmpty(results)) {
            for (MicroClass result : results) {
                List<MicroClassVideo> videos = this.microClassVideoMapper.selectAllByMicroClassId(result.getId());
                if (CollectionUtils.isNotEmpty(videos)) {
                    for (MicroClassVideo video : videos) {
                        video.setAuthToken(authToken);
                    }
                }
                result.setMicroClassVideos(videos);
            }
        }
        return results;
    }
}