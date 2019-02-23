package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * <p>Person .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 23:28
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PERSON")
public class Person extends BaseEntity {
    private static final long serialVersionUID = -985309940452860298L;

    /**
     * 工号
     */
    @Column(name = "PERSON_ID",length = 64)
    private String personID;

    @Column(name = "PERSON_NAME",length = 64)
    private String personName;

    @Column(name = "GENDER",length = 1)
    private int gender;

    /**
     * 出生日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @Column(name = "BORN_DATE")
    private Date bornDate;

    /**
     * 入职日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @Column(name = "ENTRY_DATE")
    private Date entryDate;

    /**
     * 状态，0：在职、1：离职、2：禁用
     */
    @Column(name = "STATE",length = 1)
    private Integer state;

    /**
     * 权限，
     */
    @Column(name = "PURVIEW",length = 1)
    private Integer purview;

    /**
     * 后台系统登陆密码
     */
    @Column(name = "PASSWORD",length = 64)
    private String password;
}
