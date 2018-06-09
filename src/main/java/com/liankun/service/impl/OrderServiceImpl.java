package com.liankun.service.impl;

import com.liankun.dataobject.OrderDetail;
import com.liankun.dataobject.OrderMaster;
import com.liankun.dataobject.ProductInfo;
import com.liankun.dto.CastDTO;
import com.liankun.dto.OrderDTO;
import com.liankun.enums.OrderStatusEnum;
import com.liankun.enums.PayStatusEnum;
import com.liankun.enums.ResultEnum;
import com.liankun.exception.SellException;
import com.liankun.repository.OrderDetailRepository;
import com.liankun.repository.OrderMasterRepository;
import com.liankun.service.OrderService;
import com.liankun.service.ProductService;
import com.liankun.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductService productService;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

//        List<CastDTO> castDTOList = new ArrayList<>();

        //1.查询商品（数量，价格）

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);


            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

//            CastDTO castDTO = new CastDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            castDTOList.add(castDTO);

        }


        //3.写入数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CastDTO> castDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CastDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.decreaseStock(castDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {


        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finished(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
