package com.zqiheng.repository.support;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * description:
 * <p>RepositorySupport .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:03
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Component
public class RepositorySupport {

    private final static Integer BATCH_SIZE = 50;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional(rollbackFor = Exception.class)
    public <T> void batchInsert(List<T> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            entityManager.persist(dataList.get(i));
            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public <T> void batchUpdate(List<T> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            entityManager.merge(dataList.get(i));
            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
