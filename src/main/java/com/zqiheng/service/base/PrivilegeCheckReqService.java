package com.zqiheng.service.base;

import com.zqiheng.common.config.RetCodeConfig;
import com.zqiheng.core.api.bo.PersonCore;
import com.zqiheng.core.api.bo.UserCore;
import com.zqiheng.dto.MyCode;
import com.zqiheng.dto.RetCode;
import com.zqiheng.entity.entitydo.Person;
import com.zqiheng.entity.entitydo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * description:
 * <p>PrivilegeCheckReqService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:48
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
@Slf4j
public class PrivilegeCheckReqService {

    private final RetCodeConfig retCodeConfig;
    private final PersonCore personCore;
    private final UserCore userCore;

    @Autowired
    public PrivilegeCheckReqService(RetCodeConfig retCodeConfig, PersonCore personCore, UserCore userCore) {
        this.retCodeConfig = retCodeConfig;
        this.personCore = personCore;
        this.userCore = userCore;
    }

    /**
     * description:
     * <p>
     * 该方法是检验前端用户参数是否有效。
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 14:09:24
     */
    @Transactional(rollbackOn = Exception.class)
    public RetCode<Object> sxPrivilegeCheckReq(Object obj) {
        RetCode<Object> retCode = new RetCode<>();
        retCode.setReturnCode(retCodeConfig.getSucc());

        if (obj instanceof Person) {
            Person person = (Person) obj;
            Person checkPerson = personCore.privilegeCheckPerson(person);
            if (null != checkPerson) {
                retCode.setObject(checkPerson);
            } else {
                retCode.setReturnCode(new MyCode(retCodeConfig.getError(),"Not found Person Info."));
            }
        }
        if (obj instanceof User) {
            User user = (User) obj;
            User checkUser = userCore.privilegeCheckUser(user);
            if (null != checkUser) {
                retCode.setObject(checkUser);
            } else {
                retCode.setReturnCode(new MyCode(retCodeConfig.getError(),"Not found User Info."));
            }
        }
        return retCode;
    }
}
