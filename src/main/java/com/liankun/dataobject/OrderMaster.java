package com.liankun.dataobject;

import com.liankun.enums.OrderStatusEnum;
import com.liankun.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@DynamicUpdate
@Entity
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /***  订单状态 ***/
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();

    private Integer payStatus=PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
