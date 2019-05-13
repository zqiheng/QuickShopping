package com.zqiheng.controller.web;

import com.zqiheng.dto.ReturnValue;
import com.zqiheng.service.web.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * description:
 * <p>PersonController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 10:36
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Controller
@RequestMapping("/person")
public class PersonControllerForWeb {

    @Autowired
    private PersonService personService;

    @RequestMapping("/login")
    public String adminLogin(){
        return "login";
    }

    @RequestMapping("/code")
    public void createCodeCotroller(HttpServletResponse response, HttpSession session) throws IOException {
        personService.createCodeService(response,session);
    }

    //用户登录
    @RequestMapping(value="/checkUserLogin",method=RequestMethod.POST)
    @ResponseBody
    public ReturnValue checkUserLogin(HttpSession session, String personId, String password, String checkcode){
        return personService.checkAdminLoginService(session, personId, password, checkcode);
    }

    //登录成功
    @RequestMapping("/index")
    public String loginSuccess(){
        return "index";
    }

    // 数据统计页面
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
