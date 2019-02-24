package com.zqiheng.core.impl.support;

import com.zqiheng.common.constant.ConstEnum;
import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.core.support.CustomizeSupport;
import com.zqiheng.repository.support.RepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * description:
 * <p>CustomizeSupportImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:02
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Component
public class CustomizeSupportImpl implements CustomizeSupport {

    private final RepositorySupport repositorySupport;

    @Autowired
    public CustomizeSupportImpl(RepositorySupport repositorySupport) {
        this.repositorySupport = repositorySupport;
    }

    @Override
    public List<Object[]> query(String nativeSql, Object... params) {
        return generateQuery(nativeSql, params).getResultList();
    }

    @Override
    public long count(String nativeSql, Object... params) {
        List<?> result = generateQuery(nativeSql, params).getResultList();
        if (ArrayUtils.isEmpty(result)) {
            return 0;
        }
        if (result.size() > 1) {
            throw new NonUniqueResultException("count query not return a single result");
        }
        BigDecimal count = (BigDecimal) result.get(0);
        return count.longValue();
    }

    @Override
    public <T> List<T> query(String nativeSql, Class<T> classType, Object... params) {
        //if not contains * ,then replace to *
        nativeSql = nativeSql.toUpperCase();
        String displayColumns = nativeSql.substring(nativeSql.indexOf("SELECT") + 6, nativeSql.indexOf("FROM"));
        if (!displayColumns.contains(ConstEnum.ASTERISK.getValue())) {
            String preSql = nativeSql.substring(0, nativeSql.indexOf("SELECT") + 6);
            String postSql = nativeSql.substring(nativeSql.indexOf("FROM"));
            nativeSql = String.format("%s * %s", preSql, postSql);
            if (displayColumns.contains(ConstEnum.DOT.getValue())) {
                String prefix = displayColumns.substring(0, displayColumns.indexOf(ConstEnum.DOT.getValue()));
                nativeSql = String.format("%s %s.* %s", preSql, prefix.trim(), postSql);
            }
        }
        Query nativeQuery = repositorySupport.getEntityManager().createNativeQuery(nativeSql, classType);
        setParams(nativeQuery, params);
        return nativeQuery.getResultList();
    }

    private Query generateQuery(String nativeSql, Object... params) {
        EntityManager entityManager = repositorySupport.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery(nativeSql);
        setParams(nativeQuery, params);
        return nativeQuery;
    }

    private void setParams(Query nativeQuery, Object... params) {
        if (params != null || params.length > 0) {
            for (int position = 0; position < params.length; position++) {
                nativeQuery.setParameter(position + 1, params[position]);
            }
        }
    }


}
