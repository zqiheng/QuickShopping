package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>Shop .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 22:55
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SHOP")
public class Shop extends BaseEntity {
    private static final long serialVersionUID = -3698407887400843102L;

    /**
     * 店铺ID，唯一
     */
    @Column(name = "SHOP_ID",length = 64,unique = true)
    private String shopID;

    /**
     * 店铺名称
     */
    @Column(name = "SHOP_NAME",length = 64)
    private String shopName;

    /**
     * 省
     */
    @Column(name = "PROVINCE",length = 12)
    private String province;

    /**
     * 市
     */
    @Column(name = "CITY",length = 12)
    private String city;

    /**
     * 区/县
     */
    @Column(name = "COUNTY",length = 12)
    private String county;

    /**
     * 详细地址
     */
    @Column(name = "DETAIL_ADDRESS",length = 64)
    private String detailAddress;


    /**
     * 店铺类型
     */
    @Column(name = "SHOP_TYPE",length = 64)
    private String shopType;

    /**
     * 店铺电话（座机）
     */
    @Column(name = "SHOP_TEL",length = 15)
    private String shopTel;

    /**
     * 维度
     */
    @Column(name = "LATITUDE",length = 16)
    private String latitude;

    /**
     * 经度
     */
    @Column(name = "LONGITUDE",length = 16)
    private String longitude;
}
