package com.liankun.dto;

import com.liankun.dataobject.OrderDetail;
import com.liankun.enums.OrderStatusEnum;
import com.liankun.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /***  订单状态 ***/
    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
