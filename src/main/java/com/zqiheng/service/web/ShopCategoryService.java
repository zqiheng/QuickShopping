package com.zqiheng.service.web;

import com.zqiheng.core.api.bo.ShopCategoryCore;
import com.zqiheng.entity.entitydo.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>ShopCategoryService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/27         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/27 15:18
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
public class ShopCategoryService {

    @Autowired
    private ShopCategoryCore shopCategoryCore;

    /**
     * description:
     * <p>
     *     获取所有店铺分类信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:34:47
     */
    public List<ShopCategory> getAllShopCategoryInfo() {
        return shopCategoryCore.getAllShopCategoryInfo();
    }

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:35:40
     */
    public ShopCategory addShopCategoryInfo(ShopCategory shopCategory) {
        return shopCategoryCore.addShopCategoryInfo(shopCategory);
    }


    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 15:36:18
     */
    public boolean deleteShopCategoryInfo(Integer id) {
        return shopCategoryCore.deleteShopCategoryInfo(id);
    }
}
