/**
 * Copyright(C) 2012-2017 the original author <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>.
 * All rights reserved.
 */
package com.estore.utils;

import java.math.BigDecimal;

/**
 * @author  <a href="mailto:tanxujie@gmail.com">Tan XuJie</a>
 */
public final class MoneyUtils {

    private MoneyUtils() {
        throw new RuntimeException();
    }

    /**
     * 
     * @param value
     * @return
     */
    public static final String format(BigDecimal value) {
        if (null == value || BigDecimal.ZERO.compareTo(value) == 0) {
            return "0元";
        }

        if (Constants.MONEY_TEN_THOUSAND.compareTo(value) > 0) {
            return Constants.MONEY_RENMINBI_SIGN 
                    + value.toString() 
                    + Constants.MONEY_RENMINBI_UNIT;
        } else {
            return Constants.MONEY_RENMINBI_SIGN 
                    + value.divide(Constants.MONEY_TEN_THOUSAND) 
                    + Constants.MONEY_RENMINBI_TEN_THOUSAND_UNIT;
        }
    }
}
