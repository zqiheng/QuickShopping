package com.zqiheng.dto;

import com.zqiheng.entity.entitydo.Person;
import lombok.Data;

import java.util.List;

/**
 * description:
 * <p>Params .<br/></p>
 * <p>
 *     Front end request parameter.
 * </p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:22
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class Params {

    @Data
    public static class GetUserInfoParam {
        private Person person;
        private String userID;
    }

    @Data
    public static class UserInfo {
        private String nickName;
        private String gender;
        private String language;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
    }

    /**
     * 查询多个商品需要的参数
     */
    @Data
    public static class ProductsReq {
        private int shopObj;
    }

    /**
     * 查询一个商品信息需要的参数
     */
    @Data
    public static class ProductReq {
        private String productID;
        private int shopObj;
    }

    /**
     * 存放用户的定位信息
     */
    @Data
    public static class Position {
        /**
         * 经度
         */
        private String longitude;
        /**
         * 纬度
         */
        private String latitude;
    }

    /**
     * 添加收货人地址时的入参
     */
    @Data
    public static class AddressInfo {
        private int userObj;
        private List<ConsigneeInfo> consigneeInfos;
    }

    @Data
    public static class ConsigneeInfo {
        /**
         * 主键id
         */
        private int id;
        /**
         * 收货人姓名
         */
        private String consignee;
        /**
         * 收货人电话
         */
        private String mobile;
        /**
         * 收货人地址
         */
        private String address;
        /**
         * 收货时间
         */
        private String transportDay;

        /**
         * 是否为默认地址
         */
        private boolean idDefaultAddress;
    }

    /**
     * 添加订单信息所需要的入参
     */
    @Data
    public static class AddOrdersInfo {
        private int userObj;
        private int shopObj;
        private int addressObj;
        private boolean pickUpOnself;
        // 标明用户支付时是选择确认还是取消支付
        private boolean payFlag;
        private String ordersRemark;
        List<GoodsItems> goodsItems;
        private float discountMoneys;
    }

    @Data
    public static class GoodsItems {
        private int goodsId;   // id
        private String productID;
        private String productName;
        /*private String productBrand;*/
        /*private String factoryName;*/
        /*private String productNorm;*/
        /*private String productPackingUnit;*/
        /*private String productPicture;*/
        /*private String productRemark;*/
        private String productType;
        private String productTypeName;
        /*private String registeredAddress;*/
        /*private int sellQuantity;*/
        private int shopObj;
        /*private int stockQuantity;*/
        private float activityPrice;
        private float price;
        private int count;
        private float totalMoney;
    }

    @Data
    public static class GetOrdersInfoParams {
        private int userObj;
    }

    @Data
    public static class GetOneOrdersInfoParams {
        private int userObj;
        private String ordersID;
    }
}
