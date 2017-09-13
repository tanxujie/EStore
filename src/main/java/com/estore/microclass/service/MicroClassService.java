/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.microclass.service;

import java.util.List;

import com.estore.microclass.entity.MicroClass;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public interface MicroClassService {

    /**
     * 
     * @param data
     */
    void save(MicroClass data);

    /**
     * 
     * @param ids
     */
    void remove(int[] ids);

    /**
     * 
     * @param data
     */
    void modify(MicroClass data);

    /**
     * 
     * @param condition
     * @return
     */
    List<MicroClass> search(String condition);

    /**
     * 
     * @return
     */
    List<MicroClass> searchForApp();

    /**
     * 
     * @param id
     * @return
     */
    MicroClass getDetail(int id);
}