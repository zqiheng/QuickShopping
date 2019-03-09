package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.UserAddress;

import java.util.List;

/**
 * description:
 * <p>SopUserAddress .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:31
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface UserAddressCore extends BaseCore {

     /**
      * description:
      * <p>
      *     添加用户收货地址信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/3/9 22:21:16
     */
     List<UserAddress> addUserAddressInfo(int userObj, List<Params.ConsigneeInfo> consigneeInfos);
}
