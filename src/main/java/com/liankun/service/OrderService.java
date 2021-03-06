package com.liankun.service;

import com.liankun.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    /** 创建订单***/
    OrderDTO create(OrderDTO orderDTO );


    /** 查询单个订单***/
    OrderDTO findOne(String orderId);


    /** 查询列表订单***/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);


    /** 取消订单***/
    OrderDTO cancel(OrderDTO orderDTO);


    /** 完结订单***/
    OrderDTO finished(OrderDTO orderDTO);


    /** 支付订单***/
    OrderDTO paid(OrderDTO orderDTO);

}
