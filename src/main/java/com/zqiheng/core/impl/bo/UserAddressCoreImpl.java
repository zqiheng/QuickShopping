package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.core.api.bo.UserAddressCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.UserAddress;
import com.zqiheng.repository.UserAddressDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        consigneeInfos.forEach(param -> {
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(param.getAddress());
            userAddress.setDefaultAddress(param.isIdDefaultAddress());
            userAddress.setMobile(param.getMobile());
            userAddress.setTransportDay(param.getTransportDay());
            userAddress.setConsignee(param.getConsignee());
            userAddress.setUserObj(userObj);
            newAddresses.add(userAddress);
        });
        List<UserAddress> savedAddresses = userAddressDao.saveAll(newAddresses);
        if (!ArrayUtils.isEmpty(savedAddresses)) {
            return savedAddresses;
        }
        return null;
    }

    @Override
    public List<Params.ConsigneeInfo> getUserAddressInfo(int userObj) {
        List<Params.ConsigneeInfo> retVal = new ArrayList<>();
        List<UserAddress> userAddresses = userAddressDao.findAll((Specification<UserAddress>)
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userObj"), userObj));
        if (!ArrayUtils.isEmpty(userAddresses)) {
            userAddresses.forEach(param -> {
                Params.ConsigneeInfo consigneeInfo = new Params.ConsigneeInfo();
                consigneeInfo.setId(param.getId());
                consigneeInfo.setConsignee(param.getConsignee());
                consigneeInfo.setAddress(param.getAddress());
                consigneeInfo.setIdDefaultAddress(param.isDefaultAddress());
                consigneeInfo.setMobile(param.getMobile());
                consigneeInfo.setTransportDay(param.getTransportDay());
                retVal.add(consigneeInfo);
            });
        }
        return retVal;
    }
}
