package com.zqiheng.service.wx;

import com.zqiheng.core.api.bo.ShopCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Infos.ShopInfos findTheSpecifyShopInfo(Params.Position position) {
        if (null != position) {
            return shopCore.findTheSpecifyShopByUserPosition(position.getLongitude(),position.getLatitude());
        }
        return null;
    }
}
