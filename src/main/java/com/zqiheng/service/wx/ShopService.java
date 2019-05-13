package com.zqiheng.service.wx;

import com.zqiheng.core.api.bo.ShopCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>ShopService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/8 20:55
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class ShopService {

    private final ShopCore shopCore;

    @Autowired
    public ShopService(ShopCore shopCore) {
        this.shopCore = shopCore;
    }

    /**
     * description:
     * <p>
     * 根据定位信息获取指定距离用户最近的商店
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 12:55:21
     */
    public Infos.ShopInfos findTheSpecifyShopInfoForWX(Params.Position position) {
        if (null != position) {
            return shopCore.findTheSpecifyShopByUserPosition(position.getLongitude(), position.getLatitude());
        }
        return null;
    }

    /**
     * description:
     * <p>
     * 获取所有店铺信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 14:48:17
     */
    public List<Shop> getAllShopListInfoForWeb() {
        return shopCore.getAllShopListInfo();
    }

    /**
     * description:
     * <p>
     * 添加店铺信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 14:48:46
     */
    public ReturnValue addShopInfo(Shop shop) {
        return shopCore.addShopInfo(shop);
    }

    /**
     * 根据 id 删除店铺信息
     *
     * @param id --
     * @return
     */
    public ReturnValue deleteShopInfo(Integer id) {
        boolean b = shopCore.deleteShopInfo(id);
        if (b) {
            return ReturnValue.createSuccess("删除成功", null);
        } else {
            return ReturnValue.createError("删除失败", null);
        }
    }
}
