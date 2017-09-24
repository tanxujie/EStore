/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.mapper;

import java.util.List;

import com.estore.microclass.entity.MicroClassVideo;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MicroClassVideoMapper {

    /**
     * 
     * @param microClassVideo
     * @return
     */
    int insert(MicroClassVideo entity);

    /**
     * 
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 
     * @param microClass
     * @return
     */
    int update(MicroClassVideo entity);

    /**
     * 
     * @param condition
     * @return
     */
    List<MicroClassVideo> selectAll(String condition);

    /**
     * 
     * @param microClassId
     * @return
     */
    List<MicroClassVideo> selectAllByMicroClassId(int microClassId);

    /**
     * 
     * @param id
     * @return
     */
    MicroClassVideo select(int id);
}