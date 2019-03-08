package com.zqiheng.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * description:
 * <p>Infos .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/8 21:31
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class Infos {

    @Data
    public static class ShopInfos{
        /**
         * 主键id
         */
        private int id;
        private String shopID;
        private String shopName;
        private String province;
        private String city;
        private String county;
        private String detailAddress;
        private String shopType;
        private String shopTel;
        /**
         * 距离用户的距离
         */
        private double distance;
    }
}
