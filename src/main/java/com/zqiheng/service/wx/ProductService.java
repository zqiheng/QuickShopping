package com.zqiheng.service.wx;

import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.ProductCore;
import com.zqiheng.entity.entitydo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 * <p>ProductService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/1         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/1 21:36
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class ProductService {

    private final ProductCore productCore;

    @Autowired
    public ProductService(ProductCore productCore) {
        this.productCore = productCore;
    }

    public Map<String,List<Product>> getAllProductListTest(){
        // 在业务层中需对商品类型(product_type)进行分组
        List<Product> products = productCore.getAllProductList();
        return products.stream().collect(Collectors.groupingBy(Product::getProductType));
    }

    public Product getProductInfo(String productID){
        if(!StringUtils.isEmpty(productID)){
            return productCore.getProductInfo(productID);
        }
        return null;
    }
}
