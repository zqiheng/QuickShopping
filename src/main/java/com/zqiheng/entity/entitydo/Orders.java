package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * <p>Order .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 23:46
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ORDERS")
public class Orders extends BaseEntity {
    private static final long serialVersionUID = 6494829253548465514L;

    /**
     * 订单ID，订单唯一标识
     */
    @Column(name = "ORDERS_ID", length = 64, unique = true)
    private String ordersID;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @Column(name = "ORDERS_CREATE_TIME")
    private Date ordersCreateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @Column(name = "ORDERS_COMPLETE_TIME")
    private Date ordersCompleteTime;

    /**
     * 0：已完成、1：待自提、2：待配送、3：待收货、4：已取消
     */
    @Column(name = "ORDERS_TYPE")
    private Integer ordersType;

    /**
     * 提货方式，0：上门自提，1：配送
     */
    @Column(name = "PICK_MODE")
    private boolean pickMode;

    /**
     * 关联User主键
     */
    @Column(name = "USER_OBJ")
    private Integer userObj;

    /**
     * 订单创建的门店,关联门店主键
     */
    @Column(name = "SHOP_OBJ")
    private Integer shopObj;

    /**
     * 配送地址：如果用户选择的是配送方式，则该字段需要关联用户的配送地址
     */
    @Column(name = "ADDRESS_OBJ")
    private Integer addressObj;

    /**
     * 订单备注
     */
    @Column(name = "ORDERS_REMARK")
    private String ordersRemark;

    /**
     * 该订单包含的商品总数量
     */
    @Column(name = "TOTAL_NUM")
    private int totalNum;

    /**
     * 该订单所有商品购买价格
     */
    @Column(name = "TOTAL_MONEY")
    private float totalMoney;

    /**
     * 是否为优惠订单，默认为 false
     */
    @Column(name = "DISCOUNT")
    private boolean isDiscount;

    /**
     * 优惠价格
     */
    @Column(name = "discount_money")
    private float discountMoney;
}