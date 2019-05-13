package com.zqiheng.repository;

import com.zqiheng.entity.entitysuper.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * description:
 * <p>BaseDao .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 11:16
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@NoRepositoryBean
public  interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

}
