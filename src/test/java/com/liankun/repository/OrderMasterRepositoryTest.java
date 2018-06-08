package com.liankun.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liankun.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository repository;


    private final String OPENID = "111112";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("廉琨");
        orderMaster.setBuyerPhone("123123213");
        orderMaster.setBuyerAddress("123456789123");
        orderMaster.setOrderAmount(new BigDecimal(2.0));
        orderMaster.setBuyerOpenid(OPENID);

        orderMaster = repository.save(orderMaster);
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(1, 3);

        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid(OPENID, pageRequest);

        Assert.assertNotEquals(0, orderMasterPage.getTotalElements());
    }
}