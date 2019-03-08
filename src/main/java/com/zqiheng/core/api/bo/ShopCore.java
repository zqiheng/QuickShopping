package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.entity.entitydo.Shop;

/**
 * description:
 * <p>SopShop .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:30
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface ShopCore extends BaseCore {

    /**
     * description:
     * <p>
     * 根据用户的经纬度来判断距离用户最近的商店
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return shop
     * @author ZQI
     * @date 2019/3/8 21:01:09
     */
    Infos.ShopInfos findTheSpecifyShopByUserPosition(String longitude, String latitude);
}
