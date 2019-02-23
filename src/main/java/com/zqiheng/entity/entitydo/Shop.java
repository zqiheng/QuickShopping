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

    @Column(name = "SHOP_ID",length = 64)
    private String shopID;

    @Column(name = "SHOP_NAME",length = 64)
    private String shopName;

    @Column(name = "SHOP_ADDRESS",length = 64)
    private String shopAddress;

    @Column(name = "SHOP_TYPE",length = 64)
    private String shopType;

    @Column(name = "SHOP_TEL",length = 11)
    private String shopTel;
}
