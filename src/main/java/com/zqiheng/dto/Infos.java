package com.zqiheng.dto;

import com.zqiheng.entity.entitydo.Product;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.entity.entitydo.User;
import com.zqiheng.entity.entitydo.UserAddress;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    /**
     * 自定义返回参数封装。【订单信息】
     */
    @Data
    public static class OrdersInfo {
        private String ordersId;
        private String ordersCreateTime;  // 【yyyy-MM-dd HH:mm:ss】
        private String ordersCompleteTime; // 【yyyy-MM-dd HH:mm:ss】
        private int ordersType;
        private boolean pickUpOneself;
        private User userInfo;
        private Shop shopInfo;
        private UserAddress addressInfo;
        private String ordersRemark;
        private int totalNum;
        private float totalMoney;
        private float discountMoneys;
        private boolean discount;
        private List<OrderDetailsInfo> orderDetailsInfoList;
    }

    /**
     * 自定义返回参数封装。【订单详细列表商品信息】
     */
    @Data
    public static class OrderDetailsInfo{
        private Product productInfo;
        private int productNum;
        private float productPrice;
        private Date buyTime;
    }

    @Data
    public static class ConfirmReceiptReturnParam{
        private boolean updateFlag;
    }

    @Data
    public static class StatisticsSaleNumber {
        private String shopName;
        private List<Integer> saleNumber;
    }

    @Data
    public static class WeeklyInfo {
        private Timestamp first;
        private Timestamp last;
    }
}
