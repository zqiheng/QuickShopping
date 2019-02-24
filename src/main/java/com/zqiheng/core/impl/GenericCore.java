package com.zqiheng.core.impl;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.repository.support.RepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 * <p>GenericBO .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:13
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public abstract class GenericCore implements BaseCore {

    @Autowired
    private RepositorySupport repositorySupport;

    @Override
    public <T> T convertIdToObject(Class<T> domainType, int id) {
        return repositorySupport.getEntityManager().find(domainType, id);
    }
}
