package com.liankun.controller;

import com.liankun.VO.ProductInfoVO;
import com.liankun.VO.ProductVO;
import com.liankun.VO.ResultVO;
import com.liankun.dataobject.ProductCategory;
import com.liankun.dataobject.ProductInfo;
import com.liankun.service.CategoryService;
import com.liankun.service.ProductService;
import com.liankun.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        /****  查询 ***/
        List<ProductInfo> productInfoList = productService.findUpAll();
//        List<Integer> categoryTypeList = new ArrayList<>();
//
//        for (ProductInfo productInfo : productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        /**** 拼装 **/
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());


            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for(ProductInfo productInfo : productInfoList){
                if (productVO.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }



        return ResultVOUtil.sucess(productVOList);

    }
}
