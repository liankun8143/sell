package com.liankun.service.impl;

import com.liankun.dataobject.OrderDetail;
import com.liankun.dto.OrderDTO;
import com.liankun.enums.OrderStatusEnum;
import com.liankun.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private final String BUYEROPENID = "1101110";

    private final String ORDERID = "1528549552589634224";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廉琨");
        orderDTO.setBuyerAddress("西安");
        orderDTO.setBuyerPhone("1232323232");
        orderDTO.setBuyerOpenid(BUYEROPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);


        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        log.info("订单信息result={}", orderDTO);
        Assert.assertNotNull(orderDTO);

    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderDTO> orderDTOPage =  orderService.findList(BUYEROPENID, pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTOResult = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),orderDTOResult.getOrderStatus());

    }

    @Test
    public void finished() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTOResult = orderService.finished(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),orderDTOResult.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTOResult = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),orderDTOResult.getPayStatus());
    }
}