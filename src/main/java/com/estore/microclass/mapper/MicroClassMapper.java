/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.mapper;

import java.util.List;

import com.estore.microclass.entity.MicroClass;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MicroClassMapper {

    /**
     * 
     * @param entity
     * @return
     */
    int insert(MicroClass entity);

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
    int update(MicroClass entity);

    /**
     * 
     * @param condition
     * @return
     */
    List<MicroClass> selectAll(String condition);

    /**
     * 
     * @param id
     * @return
     */
    MicroClass select(int id);
}