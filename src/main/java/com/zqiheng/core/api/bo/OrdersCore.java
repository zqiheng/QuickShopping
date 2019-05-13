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
     * 根据用户id，获取用户的订单信息。
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/14 10:24:10
     */
    List<Infos.OrdersInfo> getAllOrdersInfo(int userObj);

    /**
     * description:
     * <p>
     *      小程序端，根据用户id 和 订单ID获取指定订单信息。
     *      如果 ordersID 为空，则返回该用户最新创建的订单
     *      如果 ordersID 不为空，则返回该用户指定的订单。
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/19 22:03:24
     */
    Infos.OrdersInfo getOneOrdersInfo(int userObj, String ordersID);

    /**
     * description:
     * <p>
     *      根据指定订单的ID获取订单信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/25 17:49:48
     */
    Infos.OrdersInfo getOneOrdersInfo(String ordersID);

    /**
     * description:
     * <p>
     *     微信小程序端：用户确认收货，将待收货订单改为已完成订单。
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/20 19:34:27
     */
    boolean confirmReceipt(int userObj, String ordersID);

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 20:29:13
     */
    List<Orders> getAllTODOOrdersInfo();

    /**
     * description:
     * <p>
     *      web端：管理员确认订单派送
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 14:06:54
     */
    boolean confirmDelivery(String ordersID);

    /**
     * description:
     * <p>
     *     获取所有订单信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/27 16:51:21
     */
    List<Orders> getAllOrdersListInfo();
}
