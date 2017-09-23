/**
 * Copyright(C) 2017 the original author Tan XuJie.
 * All rights reserved.
 */
package com.estore.utils;

import java.math.BigDecimal;

/**
 * @author Tan XuJie
 *
 */
public final class Constants {

    private Constants() {
        throw new RuntimeException("Constants cann't be instantiated.");
    }

    /**
     * 新品
     */
    public static final String IS_NEW_PRODUCTION = "NEWPRD";

    /**
     * 名家
     */
    public static final String IS_MADE_BY_EXPORT = "EXPORT";

    /**
     * 推荐
     */
    public static final String IS_RECOMMENDED = "RECMD";

    /**
     * 翡翠
     */
    public static final String IS_EMERALD = "EMERALD";

    /**
     * 手工
     */
    public static final String IS_HAND_MADE = "HANDMD";

    /**
     * 默认
     */
    public static final String ORDER_BY_DEFAULT = "DEFAULT";

    /**
     * 价格
     */
    public static final String ORDER_BY_PRICE = "PRICE";

    /**
     * 人气
     */
    public static final String ORDER_BY_POPULARITY = "POPULAR";

    /**
     * 升序
     */
    public static final String ORDER_ASC = "ASC";

    /**
     * 降序
     */
    public static final String ORDER_DESC = "DESC";

    /**
     * 10000元
     */
    public static final BigDecimal MONEY_TEN_THOUSAND = BigDecimal.valueOf(10000);

    /**
     * 人民币符号
     */
    public static final String MONEY_RENMINBI_SIGN = "¥";

    /**
     * 人民币单位：元
     */
    public static final String MONEY_RENMINBI_UNIT = "元";

    /**
     * 人民币单位：万元
     */
    public static final String MONEY_RENMINBI_TEN_THOUSAND_UNIT = "万元";

    /**
     * 最大图片大小
     */
    public static final long MAX_UPLOAD_IMAGE_SIZE = 1024*1024;

    /**
     * 最大视频大小
     */
    public static final long MAX_UPLOAD_VIDEO_SIZE = (long)1.5*1024*1024*1024;

    public static final int RECORD_COUNT_PER_PAGE = 24;

    //public static final int MAJOR_CATEGORY_HAND_ON = 2;
    //public static final int MAJOR_CATEGORY_WATER_DROP = 3;
    //public static final int MAJOR_CATEGORY_NOTHING_CARDS = 4;
    //public static final int MAJOR_CATEGORY_108_PRODUCTS = 5;
    //public static final int MAJOR_CATEGORY_ENGRAVING = 6;
    
    public static final int MAJOR_CATEGORY_HAND_ON = 1;
    public static final int MAJOR_CATEGORY_WATER_DROP = 5;
    public static final int MAJOR_CATEGORY_NOTHING_CARDS = 11;
    public static final int MAJOR_CATEGORY_108_PRODUCTS = 2;
    public static final int MAJOR_CATEGORY_ENGRAVING = 6;

    public static final String GLOBAL_AUTH_TOKENS_CACHE = "EStoreAuthTokensCache";

    /**
     * 产品图片目录路径
     */
    //public static final String PRODUCT_IMAGE_DIR_PATH = "/usr/local/share/applications/EStoreStorage/Images/";
    public static final String PRODUCT_IMAGE_DIR_PATH = "C:/EStoreStorage/Images/";

    /**
     * 微课堂视频路径
     */
    //public static final String MICRO_CLASS_VIDEO_DIR_PATH = "/usr/local/share/applications/EStoreStorage/Videos/";
    public static final String MICRO_CLASS_VIDEO_DIR_PATH = "C:/EStoreStorage/Videos/";

    /**
     * 后台应用服务根URL地址。
     * 将来会替换为域名。
     */
    //public static final String WEB_SERVICE_ROOT_URL = "http://www.akunzhubao.com";
    public static final String WEB_SERVICE_ROOT_URL = "http://192.168.1.102:8080";
}
