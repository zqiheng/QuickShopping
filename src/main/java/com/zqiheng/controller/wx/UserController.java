package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import com.zqiheng.dto.RetCode;
import com.zqiheng.entity.entitydo.Person;
import com.zqiheng.entity.entitydo.User;
import com.zqiheng.service.base.PrivilegeCheckReqService;
import com.zqiheng.service.web.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * description:
 * <p>UserController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:35
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeCheckReqService privilegeCheckReqService;

    @PostMapping(value = "/user/get_user_info/req")
    public Response getUserInfo(@RequestBody Params.GetUserInfoParam params){
        log.info("Input UserController::getUserInfo()...");
        // 【Step1】 check the input params.
        log.info("【Step1】 check the input params.");
        Validations.check(null == params,"The input param info is null");
        Person person = params.getPerson();
        Validations.check(null == person,"The person info is null");

        // 【Step2】 check the person info is exists.
        log.info("【Step2】 check the person info is exists.");
        RetCode<Object> retCode = privilegeCheckReqService.sxPrivilegeCheckReq(person);
        Validations.isOK(retCode.getReturnCode());

        // 【Step3】 main process.
        log.info("【Step3】 main process.");
        RetCode<User> userRetCode = userService.findUserInfoByUserID(params.getUserID());
        Validations.isOK(userRetCode.getReturnCode());

        // 【Step4】 post process.
        log.info("【Step4】 post process.");
        return Response.createSucc(userRetCode.getObject());
    }
}
