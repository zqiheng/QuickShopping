package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import com.zqiheng.entity.entitydo.Orders;
import com.zqiheng.service.wx.OrdersService;
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
 * <p>OrdersController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/11         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/11 17:21
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(value = "/add_orders_info/add")
    public Response addOrdersInfo(@RequestBody Params.AddOrdersInfo ordersInfo){
        Validations.check(null == ordersInfo,"The input params in null.");
        log.info("添加订单的信息：" + ordersInfo);

        Orders orders = ordersService.addOrdersInfo(ordersInfo);
        if(null == orders){
            return Response.createError("Add orders fail...");
        }
        return Response.createSucc(orders);
    }

    @PostMapping(value = "/get_all_orders_info/req")
    public Response getAllOrdersInfo(@RequestBody Params.GetOrdersInfoParams getOrdersInfoParams){
        Validations.check(null == getOrdersInfoParams,"The input params is null..");
        log.info("查询订单的条件为："+getOrdersInfoParams.getUserObj());
        List<Infos.OrdersInfo> ordersInfo = ordersService.getOrdersInfo(getOrdersInfoParams);
        if(ArrayUtils.isEmpty(ordersInfo)){
           return Response.createError("Query fail...");
        }
        return Response.createSucc(ordersInfo);
    }

    @PostMapping(value = "/get_one_orders_info/req")
    public Response getOneOrdersInfo(@RequestBody Params.GetOneOrdersInfoParams getOneOrdersInfoParams){
        Validations.check(null == getOneOrdersInfoParams,"The input params is null..");
        log.info("查询订单的条件为：userObj: " + getOneOrdersInfoParams.getUserObj() + " ordersID: " + getOneOrdersInfoParams.getOrdersID());
        Infos.OrdersInfo oneOrdersInfo = ordersService.getOneOrdersInfo(getOneOrdersInfoParams);
        if(null == oneOrdersInfo){
            return Response.createError("Query fail...");
        }
        return Response.createSucc(oneOrdersInfo);
    }

    @PostMapping(value = "/confirm_receipt/update")
    public Response confirmReceipt(@RequestBody Params.ConfirmReceiptParams confirmReceiptParams){
        Validations.check(null == confirmReceiptParams,"The input params is null..");
        log.info("修改订单的用户：" + confirmReceiptParams.getUserObj() + "订单号：" + confirmReceiptParams.getOrdersID());
        Infos.ConfirmReceiptReturnParam confirmReceiptReturnParam = ordersService.confirmReceipt(confirmReceiptParams);
        if(null == confirmReceiptReturnParam){
            return Response.createError("Fix fail...,Please wait for a some time...");
        }
        return Response.createSucc(confirmReceiptReturnParam);
    }
}
