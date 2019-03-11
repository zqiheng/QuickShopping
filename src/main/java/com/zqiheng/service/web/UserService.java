package com.zqiheng.service.web;

import com.zqiheng.common.config.RetCodeConfig;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.UserCore;
import com.zqiheng.dto.MyCode;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.RetCode;
import com.zqiheng.entity.entitydo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * <p>UserService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 14:44
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserCore userCore;

    @Autowired
    private RetCodeConfig retCodeConfig;

    public RetCode<User> findUserInfoByUserID(String userID) {
        RetCode<User> retCode = new RetCode<>();
        retCode.setReturnCode(retCodeConfig.getSucc());

        if (!StringUtils.isEmpty(userID)) {
            User userInfo = userCore.findUserInfoByUserID(userID);
            if (null != userInfo) {
                retCode.setObject(userInfo);
            } else {
                retCode.setReturnCode(new MyCode(retCodeConfig.getError(), "This User info is no exists."));
            }
        }
        return retCode;
    }

    /**
     * description:
     * <p>
     * 添加用户信息。
     * 备注：如果数据中存在用户信息，则不保存
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/5 21:35:52
     */
    public User addUserInfo(Params.UserInfo userInfo) {
        User param = null;
        if (null != userInfo) {
            param = userCore.findUserInfoByUserName(userInfo.getNickName());
            if (null == param) {
                User user = new User();
                user.setUserNick(userInfo.getNickName());
                user.setUserID(userInfo.getNickName() + "." + 0001);
                user.setUserGender(Integer.valueOf(userInfo.getGender()));
                user.setCountry(userInfo.getCountry());
                user.setProvince(userInfo.getProvince());
                user.setCity(userInfo.getCity());
                user.setUserAvatarUrl(userInfo.getAvatarUrl());
                user.setLanguage(userInfo.getLanguage());
                return userCore.addUserInfo(user);
            } else {
                log.info("该名称的用户信息已经存在。" + userInfo.getNickName());
            }
        }
        return param;
    }
}
