/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.product.dto;

import java.math.BigDecimal;

import com.estore.base.AbstractBaseDto;
import com.estore.utils.Constants;
import com.estore.utils.MoneyUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductListDto extends AbstractBaseDto {
    private static final long serialVersionUID = 272587772706130480L;

    /**
     * 出厂价
     */
    private BigDecimal exFactoryPrice;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片新名称
     */
    private String imageName;

    public String getImageName() {
        return Constants.WEB_SERVICE_ROOT_URL + "/download/image/" + this.imageName;
    }

    /**
     * 返回出厂价格
     * 
     * @return
     */
    public String getPrice() {
        return MoneyUtils.format(exFactoryPrice);
    }
}
