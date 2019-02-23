package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>UserAddress .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 23:39
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "USERADDRESS")
public class UserAddress extends BaseEntity {
    private static final long serialVersionUID = -1649538727508371756L;

    /**
     * 用户ID，关联User主键
     */
    @Column(name = "USER_OBJ")
    private Integer userObj;

    /**
     * 用户姓名
     */
    @Column(name = "USER_NAME",length = 64)
    private String userName;

    /**
     * 用户电话
     */
    @Column(name = "USER_PHONE",length = 11)
    private Integer phone;

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
     * 是否为默认地址，只有一条地址时，为默认地址
     */
    @Column(name = "DEFAULT_ADDRESS")
    private boolean defaultAddress;
}
