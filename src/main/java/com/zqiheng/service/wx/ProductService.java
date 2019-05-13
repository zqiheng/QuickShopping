package com.zqiheng.service.wx;

import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.ProductCore;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.ReturnValue;
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

     /**
      * description:
      * <p>
      *     微信端根据店铺id获取该店铺所有商品信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return
      * @author ZQI
      * @date 2019/4/21 16:39:19
     */
    public Map<String,List<Product>> getAllProductList(int shopObj){
        // 在业务层中需对商品类型(product_type)进行分组
        List<Product> products = productCore.getAllProductList(shopObj);
        return products.stream().collect(Collectors.groupingBy(Product::getProductType));
    }

     /**
      * description:
      * <p>
      *     微信端，根据店铺id和商品编码获取指定商品信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return
      * @author ZQI
      * @date 2019/4/21 17:48:49
     */
    public Product getProductInfo(int shopObj,String productID){
        if(!StringUtils.isEmpty(productID)){
            return productCore.getProductInfo(shopObj,productID);
        }
        return null;
    }

    /**
     * web端获取所有商品信息
     * @return
     */
    public List<Product> getAllProductListForWeb() {
        return productCore.getallProductListInfo();
    }

    /**
     * web端添加商品信息
     * @param product
     * @return
     */
    public ReturnValue addProductInfo(Params.ProductInfoForAdd product) {
        return productCore.addProductInfo(product);
    }

    /**
     * web端删除商品信息
     * @param id
     * @return
     */
    public ReturnValue deleteProductInfo(Integer id) {
        boolean deleteFlag = productCore.deleteProudctInfo(id);
        if(deleteFlag){
            return ReturnValue.createSuccess("删除成功！",null);
        } else {
            return ReturnValue.createError("删除失败！",null);
        }
    }
}
