package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>OrderDetails .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 23:54
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "ORDERDETAILS")
public class OrderDetails extends BaseEntity {

    private static final long serialVersionUID = -7865532992130929326L;

    @Column(name = "ORDER_DETAILS_ID",length = 64)
    private String orderDetailsID;

    /**
     * 所属订单，关联Order主键
     */
    @Column(name = "ORDER_OBJ")
    private Integer orderObj;

    /**
     * 购买商品，关联Product主键
     */
    @Column(name = "PRODUCT_OBJ")
    private Integer productObj;

    @Column(name = "PRODUCT_NUM",length = 4)
    private Integer productNum;

    @Column(name = "PRODUCT_PRICE")
    private Float productPrice;
}
