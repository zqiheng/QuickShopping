package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.UserAddressCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.UserAddress;
import com.zqiheng.repository.UserAddressDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * <p>SopUserAddressImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:32
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class UserAddressCoreImpl extends GenericCore implements UserAddressCore {

    @Autowired
    private UserAddressDao userAddressDao;

    @Override
    public List<UserAddress> addUserAddressInfo(int userObj, List<Params.ConsigneeInfo> consigneeInfos) {
        // 【step1】delete all the address by the specify userObj.
        List<UserAddress> addresses = userAddressDao.findAll((Specification<UserAddress>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userObj"), userObj));
        userAddressDao.deleteAll(addresses);

        // 【step2】save all the new address.
        List<UserAddress> newAddresses = new ArrayList<>();
        consigneeInfos.forEach(param->{
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(param.getAddress());
            userAddress.setDefaultAddress(param.isIdDefaultAddress());
            userAddress.setPhone(param.getMobile());
            userAddress.setTransportDay(param.getTransportDay());
            userAddress.setUserName(param.getConsignee());
            userAddress.setUserObj(userObj);
            newAddresses.add(userAddress);
        });
        List<UserAddress> savedAddresses = userAddressDao.saveAll(newAddresses);
        if(!ArrayUtils.isEmpty(savedAddresses)){
            return savedAddresses;
        }
        return null;
    }
}
