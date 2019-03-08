package com.zqiheng.entity.entitysuper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * description:
 * <p>BaseEntity .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/23         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/23 21:45
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1780625049197780648L;

    /**
     * MySQL key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
