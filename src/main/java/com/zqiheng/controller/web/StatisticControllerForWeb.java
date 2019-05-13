package com.zqiheng.controller.web;

import com.zqiheng.dto.ReturnValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:
 * <p>StatisticControllerForWeb .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/5/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/5/8 13:49
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Controller
@RequestMapping("/web/statistic")
public class StatisticControllerForWeb {

    @ResponseBody
    @RequestMapping("/sale_number_weekly")
    public ReturnValue saleNumberForWeekly(){

        return ReturnValue.createError("获取数据失败",null);
    }
}
