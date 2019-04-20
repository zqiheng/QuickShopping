package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>ShopCategory .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/20         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/20 14:02
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SHOPCATEGORY")
public class ShopCategory extends BaseEntity {
    private static final long serialVersionUID = -4051231091031905730L;

    @Column(name = "SHOP_TYPE", length = 64)
    private String shopType;
}
