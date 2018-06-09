package com.liankun.service.impl;

import com.liankun.dataobject.ProductInfo;
import com.liankun.dto.CastDTO;
import com.liankun.enums.ProductStatusEnum;
import com.liankun.enums.ResultEnum;
import com.liankun.exception.SellException;
import com.liankun.repository.ProductInfoRepository;
import com.liankun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> optional = repository.findById(productId);
        return optional.get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> page = repository.findAll(pageable);
        return page;
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CastDTO> castDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CastDTO> castDTOList) {
        for(CastDTO castDTO : castDTOList){
            ProductInfo productInfo = repository.findById(castDTO.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - castDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }
}
