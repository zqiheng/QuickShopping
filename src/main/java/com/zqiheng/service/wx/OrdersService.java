package com.zqiheng.service.wx;

import com.zqiheng.core.api.bo.OrdersCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.Orders;
import com.zqiheng.entity.entitydo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>OrdersService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/13         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/13 21:33
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
@Slf4j
public class OrdersService {

    @Autowired
    private OrdersCore ordersCore;

     /**
      * description:
      * <p>
      *     添加订单信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/3/14 10:09:14
     */
    public Orders addOrdersInfo(Params.AddOrdersInfo addOrdersInfo){
        if(null == addOrdersInfo){
            return null;
        }
        return ordersCore.addOrdersInfoByUserInfo(addOrdersInfo);
    }

     /**
      * description:
      * <p>
      *     获取用户全部订单信息
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/3/19 22:18:41
     */
    public List<Infos .OrdersInfo> getOrdersInfo(Params.GetOrdersInfoParams getOrdersInfoParams){
        if(null == getOrdersInfoParams){
            return null;
        }
        return ordersCore.getAllOrdersInfo(getOrdersInfoParams.getUserObj());
    }

    /**
     * description:
     * <p>
     *      获取用户一条订单信息
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/19 22:21:07
     */
    public Infos.OrdersInfo getOneOrdersInfo(Params.GetOneOrdersInfoParams getOneOrdersInfoParams) {
        if (null == getOneOrdersInfoParams) {
            return null;
        }
        return ordersCore.getOneOrdersInfo(getOneOrdersInfoParams.getUserObj(), getOneOrdersInfoParams.getOrdersID());
    }
}
