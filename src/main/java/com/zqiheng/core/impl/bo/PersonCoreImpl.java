package com.zqiheng.core.impl.bo;

import com.zqiheng.core.api.bo.PersonCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.entity.entitydo.Person;
import com.zqiheng.repository.PersonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * description:
 * <p>SopPersonImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:28
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class PersonCoreImpl extends GenericCore implements PersonCore {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person privilegeCheckPerson(Person person) {
        if (null == person) {
            return null;
        }
        if (null != person.getId()) {
            return convertIdToObject(Person.class,person.getId());
        } else {
            return personDao.findOne((Specification<Person>)
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("personID"), person.getPersonID())).orElse(null);
        }
    }

    @Override
    public long cheakPersonLogin(String personId, String password) {
        return personDao.count((Specification<Person>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("personID"), personId),
                criteriaBuilder.equal(root.get("password"), password)
        ));
    }
}
