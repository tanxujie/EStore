package com.estore.product.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PreviewConfig {
    private String caption;
    private int size;
    private String width = "120px";
    private String key;
}