package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>User .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 21:43
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6072620870531516478L;

    @Column(name = "USER_ID", length = 64,unique = true)
    private String userID;

    /**
     * User Nick, From WeChat.
     */
    @Column(name = "USER_NICK", length = 64,unique = true)
    private String userNick;

    /**
     * URL of the user avatar image, From WeChat.
     */
    @Column(name = "USER_AVATAR_URL", length = 128)
    private String userAvatarUrl;

    /**
     * User Gender，0：none、1：male、2-female，From WeChat.
     */
    @Column(name = "USER_GENDER")
    private Integer userGender;

    /**
     * country，From WeChat.
     */
    @Column(name = "COUNTRY", length = 64)
    private String country;

    /**
     * province，From WeChat.
     */
    @Column(name = "PROVINCE", length = 64)
    private String province;

    /**
     * city，From WeChat.
     */
    @Column(name = "CITY", length = 64)
    private String city;

    /**
     * language, From WeChat.
     */
    @Column(name = "LANGUAGE", length = 32)
    private String language;
}
