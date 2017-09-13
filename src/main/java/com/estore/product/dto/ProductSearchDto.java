/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.product.dto;

import com.estore.utils.Constants;

import lombok.Data;

/**
 * @author Tan XuJie
 *
 */
@Data
public class ProductSearchDto {

    /**
     * 通用检索条件
     */
    private String condition;

    /**
     * 种类分页；新品、名家、推荐、翡翠、手工
     */
    private String tabCategory;

    /**
     * 新品
     */
    private boolean newProduct;

    /**
     * 手传
     */
    private boolean handOn;

    /**
     * 水滴
     */
    private boolean waterDrop;

    /**
     * 无事卡
     */
    private boolean nothingCards;

    /**
     * 108产品
     */
    private boolean products108;

    /**
     * 雕刻件
     */
    private boolean engraving;

    /**
     * 排序字段：默认、价格、人气
     */
    private String orderBy;

    /**
     * 排序方向：ASC、DESC
     */
    private String orderDirection;

    /**
     * 大分类ID
     */
    private int majorCategoryId;

    /**
     * 小分类ID
     */
    private int minorCategoryId;

    private int limit = Constants.RECORD_COUNT_PER_PAGE;

    private int page;

    public int getMajorCategoryId() {
        int rt = this.majorCategoryId;
        if (this.handOn) {
            rt = Constants.MAJOR_CATEGORY_HAND_ON;
        } else if (this.waterDrop) {
            rt = Constants.MAJOR_CATEGORY_WATER_DROP;
        } else if (this.nothingCards) {
            rt = Constants.MAJOR_CATEGORY_NOTHING_CARDS;
        } else if (this.products108) {
            rt = Constants.MAJOR_CATEGORY_108_PRODUCTS;
        } else if (this.engraving) {
            rt = Constants.MAJOR_CATEGORY_ENGRAVING;
        }
        return rt;
    }
    public int getOffset() {
        return limit * page;
    }
    public boolean isDesc() {
        return Constants.ORDER_DESC.equalsIgnoreCase(this.orderDirection);
    }
}
