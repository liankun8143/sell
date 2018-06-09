package com.liankun.dto;

import lombok.Data;

@Data
public class CastDTO {
    /** 商品id ***/
    private String productId;
    /** 商品数量 ***/
    private Integer productQuantity;

    public CastDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
