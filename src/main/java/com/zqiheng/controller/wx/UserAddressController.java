package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import com.zqiheng.entity.entitydo.UserAddress;
import com.zqiheng.service.wx.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * description:
 * <p>UserAddressController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/9         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/9 21:51
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RestController
@RequestMapping(value = "/address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping(value = "/add_user_address/add")
    public Response addUserAddress(@RequestBody Params.AddressInfo addressInfo){
        Validations.check(null == addressInfo,"The input param is null.");
        log.info("要添加的收货地址为："+addressInfo);
        List<UserAddress> addresses = userAddressService.addUerAddress(addressInfo);
        if(!ArrayUtils.isEmpty(addresses)){
            return Response.createSucc(addresses);
        }
        return Response.createError("Add the address info error...");
    }

    @PostMapping(value = "/get_user_address/req")
    public Response getUserAddress(@RequestBody Params.AddressInfo addressInfo){
        Validations.check(null == addressInfo,"The input param is null.");
        log.info("查询用户地址的条件为："+addressInfo.getUserObj());
        List<Params.ConsigneeInfo> userAddress = userAddressService.getUserAddress(addressInfo.getUserObj());
        if(!ArrayUtils.isEmpty(userAddress)){
            return Response.createSucc(userAddress);
        }
        return Response.createError("Not found the userObj info.");
    }
}
