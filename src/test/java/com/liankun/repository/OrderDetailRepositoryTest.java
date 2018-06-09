package com.liankun.repository;

import com.liankun.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = repository.findByOrderId("1111144");

        Assert.assertNotEquals(0, orderDetailList.size());

    }

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456700");
        orderDetail.setOrderId("1111144");
        orderDetail.setProductIcon("http:/*****.jpg");
        orderDetail.setProductId("123232");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.6));
        orderDetail.setProductQuantity(3);

        orderDetail = repository.save(orderDetail);

        Assert.assertNotNull(orderDetail);
    }

}