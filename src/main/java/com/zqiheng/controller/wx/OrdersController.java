package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

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

    @PostMapping(value = "/add_orders_info/add")
    public Response addOrdersInfo(@RequestBody Params.AddOrdersInfo ordersInfo){
        Validations.check(null == ordersInfo,"The input params in null.");
        log.info("添加订单的信息：" + ordersInfo);
        return Response.createSucc(null);
    }
}
