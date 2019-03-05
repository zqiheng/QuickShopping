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
    @Column(name = "ORDERS_ID",length = 64,unique = true)
    private String ordersID;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @Column(name = "ORDERS_CREATE_TIME")
    private Date ordersCreateTime;

    /**
     * 0：待支付、1：已支付、2：已取消、3：待配送、4：已完成
     */
    @Column(name = "ORDERS_TYPE")
    private Integer ordersType;

    /**
     * 关联User主键
     */
    @Column(name = "USER_OBJ")
    private Integer userObj;

    /**
     * 提货方式，0：上门自提，1：配送
     */
    @Column(name = "PICK_MODE")
    private Integer pickMode;

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
}
