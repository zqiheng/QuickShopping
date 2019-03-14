package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.Orders;

import java.util.List;

/**
 * description:
 * <p>SopOrders .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:27
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface OrdersCore extends BaseCore {

    /**
     * description:
     * <p>
     * 添加订单信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/13 22:06:58
     */
    Orders addOrdersInfoByUserInfo(Params.AddOrdersInfo ordersInfo);


    /**
     * description:
     * <p>
     *     根据用户id，获取用户的订单信息。
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/14 10:24:10
     */
    List<Infos.OrdersInfo> getOrdersInfoByUserObj(int userObj);
}
