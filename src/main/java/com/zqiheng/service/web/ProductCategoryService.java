package com.zqiheng.service.web;

import com.zqiheng.core.api.bo.ProductCategoryCore;
import com.zqiheng.entity.entitydo.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>ProductCategoryService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 18:39
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryCore productCategoryCore;

    /**
     * description:
     * <p>
     *     获取所有商品分类信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 18:49:54
     */
    public List<ProductCategory> getAllProductCategoryList() {
        return productCategoryCore.getAllProductCategoryList();
    }

     /**
      * description:
      * <p>
      *     根据指定的id获取商品分类信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return
      * @author ZQI
      * @date 2019/4/21 18:51:42
     */
    public ProductCategory getOneProductCategoryInfo(Integer id){
        return productCategoryCore.getOneProductCategoryInfo(id);
    }

    /**
     * description:
     * <p>
     *     添加商品类型信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 16:18:55
     */
    public ProductCategory addProductCategoryInfo(ProductCategory productCategory) {
        return productCategoryCore.addProductCategoryInfo(productCategory);
    }

    /**
     * description:
     * <p>
     *     删除指定商品类型
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 16:26:04
     */
    public boolean deleteProductCategoryInfo(Integer id) {
        return productCategoryCore.deleteProductCategoryInfo(id);
    }
}