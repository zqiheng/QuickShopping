package com.zqiheng.entity.entitydo;

import com.zqiheng.entity.entitysuper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>ProductCategory .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/20         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/20 13:58
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PRODUCTCATEGORY")
public class ProductCategory extends BaseEntity {
    private static final long serialVersionUID = 2487889087984088440L;

    @Column(name = "PRODUCT_TYPE", length = 2)
    private String productType;

    @Column(name = "PRODUCT_TYPE_NAME", length = 64)
    private String productTypeName;
}
