/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.service;

import java.util.List;

import com.estore.microclass.entity.MicroClassVideo;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MicroClassVideoService {

    /**
     * 
     * @param data
     */
    void save(MicroClassVideo data);

    /**
     * 
     * @param ids
     */
    void remove(int[] ids);

    /**
     * 
     * @param data
     */
    void modify(MicroClassVideo data);

    /**
     * 
     * @param condition
     * @return
     */
    List<MicroClassVideo> search(String condition);

    /**
     * 
     * @param microClassId
     * @return
     */
    List<MicroClassVideo> searchByMicroClassId(int microClassId);
    
    /**
     * 
     * @param id
     * @return
     */
    MicroClassVideo getDetail(int id);
}