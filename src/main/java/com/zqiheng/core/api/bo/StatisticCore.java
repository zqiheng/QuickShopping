package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.dto.Infos;

import java.util.List;

/**
 * description:
 * <p>StatisticCore .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/5/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/5/8 13:55
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface StatisticCore extends BaseCore {

     /**
      * description:
      * <p>
      *     获取每个店铺当前周的每一天商品的销量：从周一至周七
      *     如：45  83  23  0  89  67  99
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return
      * @author ZQI
      * @date 2019/5/8 13:58:04
     */
    List<Infos.StatisticsSaleNumber> getSaleNumberForWeekly();
}
