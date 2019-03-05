package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.UserCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.entity.entitydo.User;
import com.zqiheng.repository.UserDao;
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
 * <p>SopUserImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:19
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class UserCoreImpl extends GenericCore implements UserCore {

    @Autowired
    private UserDao userDao;

    @Override
    public User privilegeCheckUser(User user) {
        if (null == user) {
            return null;
        }
        if (null != user.getId()) {
            return convertIdToObject(User.class, user.getId());
        } else {
            return userDao.findOne((Specification<User>)
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userID"), user.getUserID())).orElse(null);
        }
    }

    @Override
    public User findUserInfoByUserID(String userID) {
        if(!StringUtils.isEmpty(userID)){
            return userDao.findOne((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userID"),userID)).orElse(null);
        }
        return null;
    }

    @Override
    public User addUserInfo(User user) {
        return userDao.save(user);
    }

    @Override
    public User findUserInfoByUserName(String userName) {
        return userDao.findOne((Specification<User>)
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userNick"),userName)).orElse(null);
    }
}
