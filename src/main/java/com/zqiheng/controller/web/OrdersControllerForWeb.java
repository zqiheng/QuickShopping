package com.zqiheng.controller.web;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Orders;
import com.zqiheng.service.wx.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description:
 * <p>OrdersControllerForWeb .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 20:24
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Controller
@RequestMapping("/web/orders")
public class OrdersControllerForWeb {

    @Autowired
    private OrdersService ordersService;

    /**
     * Web端：获取所有用户待配送的订单信息
     *
     * @param model -
     * @return
     */
    @RequestMapping("/todoOrders_list")
    public String getAllTODOOrdersListInfo(Model model) {
        List<Orders> orders = ordersService.getAllTODOOrdersListInfo();
        if (!ArrayUtils.isEmpty(orders)) {
            model.addAttribute("orders", orders);
            model.addAttribute("count", orders.size());
        } else {
            model.addAttribute("orders", null);
            model.addAttribute("count", 0);
        }
        return "orders/todoOrders/todoOrders_list";
    }

    /**
     * web端：获取所有订单信息
     * @param model model
     * @return 地址
     */
    @RequestMapping("/orders_list")
    public String getAllOrdersListInfo(Model model){
        List<Orders> orders = ordersService.getAllOrdersListInfo();
        if (!ArrayUtils.isEmpty(orders)) {
            model.addAttribute("count", orders.size());
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("count", 0);
            model.addAttribute("orders", null);
        }
            return "orders/orders_list";
    }


    /**
     * web端获取配送订单清单
     * @param ordersID
     * @param model
     * @return
     */
    @RequestMapping("get_delivery_orders_info")
    public String getDeliveryOrdersInfo(String ordersID, Model model) {
        Validations.check(StringUtils.isEmpty(ordersID), "Controller:: getDeliveryOrdersInfo()  The Orders's id is null...");
        log.info("订单ID：" + ordersID);
        Infos.OrdersInfo ordersInfo = ordersService.getOneOrdersInfoByOrderID(ordersID);
        log.info("确认配送的订单为：" + ordersInfo);
        model.addAttribute("ordersInfo", ordersInfo);
        // 返回订单配送列表清单的页面地址
        return "ordersConfirm/ordersConfirm_list";
    }

    /**
     * web端确认配送订单
     * @param ordersID ordersID
     * @return ReturnValue
     */
    @RequestMapping("confirm_delivery")
    @ResponseBody
    public ReturnValue confirmDelivery(String ordersID){
        Validations.check(null == ordersID,"confirmDelivery(): The ordersID is null...");
        return ordersService.confirmDelivery(ordersID);
    }
}
