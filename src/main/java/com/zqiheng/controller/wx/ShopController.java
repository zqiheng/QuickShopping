package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import com.zqiheng.service.wx.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * description:
 * <p>ShopController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/8 20:54
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RestController
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = "/find_specify_shop_by_position")
    public Response findTheSpecShopByPosition(@RequestBody Params.Position position){
        Validations.check(null == position,"The specify user position is null...");

        // 【step1】get the specify shop infos.
        log.info("【step1】get the specify shop infos.");
        Infos.ShopInfos specifyShopInfo = shopService.findTheSpecifyShopInfoForWX(position);

        // 【step2】return the info.
        log.info("【step2】return the info.");
        if(null == specifyShopInfo){
            return Response.createError("Not find the specify shop info by the position.");
        }
        return Response.createSucc(specifyShopInfo);
    }
}
