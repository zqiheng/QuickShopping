package com.zqiheng.service.web;

import com.zqiheng.common.config.RetCodeConfig;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.UserCore;
import com.zqiheng.dto.MyCode;
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
public class UserService{

    @Autowired
    private UserCore userCore;

    @Autowired
    private RetCodeConfig retCodeConfig;

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/2/24 14:45:30
     */
    public RetCode<User> findUserInfoByUserID(String userID){
        RetCode<User> retCode = new RetCode<>();
        retCode.setReturnCode(retCodeConfig.getSucc());

        if(!StringUtils.isEmpty(userID)){
            User userInfo = userCore.findUserInfoByUserID(userID);
            if(null != userInfo){
                retCode.setObject(userInfo);
            }else{
                retCode.setReturnCode(new MyCode(retCodeConfig.getError(),"This User info is no exists."));
            }
        }
        return retCode;
    }
}
