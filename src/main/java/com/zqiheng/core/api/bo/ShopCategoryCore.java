package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.entity.entitydo.ShopCategory;

import java.util.List;

/**
 * description:
 * <p>ShopCategoryCore .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/27         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/27 15:17
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface ShopCategoryCore extends BaseCore {

    /**
     * description:
     * <p>
     * 获取所有店铺分类信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:21:05
     */
    List<ShopCategory> getAllShopCategoryInfo();

    /**
     * description:
     * <p>
     *     添加店铺类型信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:31:35
     */
    ShopCategory addShopCategoryInfo(ShopCategory shopCategory);

    /**
     * description:
     * <p>
     *     删除店铺类型信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:32:08
     */
    boolean deleteShopCategoryInfo(Integer id);
}
