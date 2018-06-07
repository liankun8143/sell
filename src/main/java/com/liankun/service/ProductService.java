package com.liankun.service;

import com.liankun.dataobject.ProductCategory;
import com.liankun.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);



    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减少库存


}
