package com.zqiheng.service.wx;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.core.api.bo.UserAddressCore;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>UserAddressService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/9         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/9 22:15
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class UserAddressService {

    @Autowired
    private UserAddressCore userAddressCore;

     /**
      * description:
      * <p>
      *     添加用户的地址信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/3/9 22:18:10
     */
    public List<UserAddress> addUerAddress(Params.AddressInfo addressInfo){
        if(null == addressInfo){
            return null;
        }
        int userObj = addressInfo.getUserObj();
        List<Params.ConsigneeInfo> consigneeInfos = addressInfo.getConsigneeInfos();
        List<UserAddress> addresses = userAddressCore.addUserAddressInfo(userObj, consigneeInfos);
        if(!ArrayUtils.isEmpty(addresses)){
            return addresses;
        }
        return null;
    }
}
