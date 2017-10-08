/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.estore.base.AbstractBaseDto;
import com.estore.product.entity.ProductImage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends AbstractBaseDto {
    private static final long serialVersionUID = -3214818790707735952L;

    /**
     * 产品编码
     */
    private String code;

    /**
     * 大分类ID
     */
    private int majorCategoryId;

    /**
     * 小分类ID
     */
    private int minorCategoryId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;

    private String innerDetail;

    /**
     * 出厂价格
     */
    private BigDecimal exFactoryPrice;

    /**
     * 优惠价
     */
    private BigDecimal favorablePrice;

    /**
     * 一级价格
     */
    private BigDecimal primaryPrice;

    /**
     * 新品
     */
    private boolean newProduct;

    /**
     * 名家
     */
    private boolean byExpert;

    /**
     * 推荐
     */
    private boolean recommended;

    /**
     * 翡翠
     */
    private boolean usingEmerald;

    /**
     * 手工
     */
    private boolean handmade;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 微信号码
     */
    private String wechatNumber;

    /**
     * 颜色
     */
    private String color;

    /**
     * 种水
     */
    private String category;

    /**
     * 款式
     */
    private String style;

    /**
     * 题材
     */
    private String theme;

    /**
     * 价格区间(From)
     */
    private BigDecimal priceRangeFrom;

    /**
     * 价格区间(To)
     */
    private BigDecimal priceRangeTo;

    /**
     * 圈比
     */
    private String circleRation;

    private boolean underShelf;

    /**
     * 产品图片
     */
    private List<ProductImage> images;

    private List<String> imageInitialPreview;

    private List<PreviewConfig> imagePreviewConfig;
}