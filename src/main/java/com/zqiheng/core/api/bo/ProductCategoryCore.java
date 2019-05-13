package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.entity.entitydo.ProductCategory;

import java.util.List;

/**
 * description:
 * <p>ProductCategoryCore .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 18:40
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface ProductCategoryCore extends BaseCore {

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 18:43:30
     */
    List<ProductCategory> getAllProductCategoryList();

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 18:52:23
     */
    ProductCategory getOneProductCategoryInfo(Integer id);

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
     * @date 2019/4/27 16:20:06
     */
    ProductCategory addProductCategoryInfo(ProductCategory productCategory);

    /**
     * description:
     * <p>
     *     删除指定商品类型信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 16:20:58
     */
    boolean deleteProductCategoryInfo(Integer id);
}
