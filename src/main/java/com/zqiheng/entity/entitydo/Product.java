package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>Product .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 22:59
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 61298391944706694L;

    /**
     * 商品条码
     */
    @Column(name = "PRODUCT_ID",length = 13)
    private String productID;

    /**
     * 商品名称
     */
    @Column(name = "PRODUCT_NAME",length = 64)
    private String productName;

    /**
     * 商品品牌
     */
    @Column(name = "PRODUCT_BRAND",length = 64)
    private String productBrand;

    /**
     * 商品制作公司
     */
    @Column(name = "FACTORY_NAME",length = 64)
    private String factoryName;

    /**
     * 注册地址
     */
    @Column(name = "REGISTERED_ADDRESS",length = 64)
    private String registeredAddress;

    /**
     * 产品规格
     */
    @Column(name = "PRODUCT_NORM",length = 64)
    private String productNorm;

    /**
     * 包装单位
     */
    @Column(name = "PRODUCT_PACKING_UNIT",length = 64)
    private String productPackingUnit;

    /**
     * 备注信息
     */
    @Column(name = "PRODUCT_REMARK")
    private String productRemark;

    /**
     * 类型标记，A B C ...
     */
    @Column(name = "PRODUCT_TYPE",length = 2)
    private String productType;

    /**
     * 类型名称
     */
    @Column(name = "PRODUCT_TYPE_NAME",length = 64)
    private String productTypeName;

    /**
     * 进货价格
     */
    @Column(name = "PRODUCT_PROPOSED_PRICE")
    private Float productProposedPrice;

    /**
     * 实际价格
     */
    @Column(name = "PRODUCT_REAL_PRICE")
    private Float productRealPrice;

    /**
     * 优惠活动价格
     */
    @Column(name = "PRODUCT_ACTIVITY_PRICE")
    private Float productActivityPrice;

    /**
     * 商品图片,URL地址
     */
    @Column(name = "PRODUCT_PICTURE")
    private String productPicture;

    /**
     * 商品库存
     */
    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;

    /**
     * 商品销量
     */
    @Column(name = "SELL_QUANTITY")
    private Integer sellQuantity;

    /**
     * 商品所属店铺，关联店铺主键
     */
    @Column(name = "SHOP_OBJ")
    private Integer shopObj;
}
