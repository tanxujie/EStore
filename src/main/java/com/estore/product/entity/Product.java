/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.estore.base.AbstractBaseEntity;
import com.estore.utils.Constants;
import com.estore.utils.MoneyUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends AbstractBaseEntity {
    private static final long serialVersionUID = -3214818790707735952L;

    private String authToken;
    /**
     * 产品编码
     */
    private String code;

    /**
     * 大分类ID
     */
    private int majorCategoryId;

    /**
     * 大分类名称
     */
    private String majorCategoryName;

    /**
     * 小分类ID
     */
    private int minorCategoryId;

    /**
     * 小分类名称
     */
    private String minorCategoryName;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;

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

    private String[] subConditions;

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

    /**
     * 
     */
    private MultipartFile[] images;

    /**
     * 
     */
    private String imageName;

    /**
     * 
     */
    private String[] imageNames;

    /**
     * 
     */
    private int[] delImageIds;

    private String videoName;

    public boolean isHasVideo() {
        return StringUtils.endsWith(this.videoName, "mp4");
    }
    /**
     * 图片源地址
     * 
     * @return
     */
    public String getImageSrc() {
        return Constants.WEB_SERVICE_ROOT_URL + "/download/image/" + this.imageName + "?authToken=" + this.authToken;
    }

    public String getVideoSrc() {
        return Constants.WEB_SERVICE_ROOT_URL + "/download/video/" + this.videoName + "?authToken=" + this.authToken;
    }

    public String getExFactoryPriceStr() {
        return MoneyUtils.format(this.exFactoryPrice);
    }

    public String getFavorablePriceStr() {
        return MoneyUtils.format(this.favorablePrice);
    }

    public String getPrimaryPriceStr() {
        return MoneyUtils.format(this.primaryPrice);
    }

    public String getPriceRangeFromStr() {
        return MoneyUtils.format(this.priceRangeFrom);
    }

    public String getPriceRangeToStr() {
        return MoneyUtils.format(this.priceRangeTo);
    }

    /**
     * 图片源地址
     * 
     * @return
     */
    public List<String> getImageSrcs() {
        if (ArrayUtils.isEmpty(this.imageNames)) {
            return new ArrayList<String>(0);
        }

        List<String> list = new ArrayList<>(this.imageNames.length);
        for (String imageName : this.imageNames) {
            list.add(Constants.WEB_SERVICE_ROOT_URL + "/download/image/" + imageName + "?authToken=" + this.authToken);
        }
        return list;
    }
}