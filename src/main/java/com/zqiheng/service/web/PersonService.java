package com.zqiheng.service.web;

import com.zqiheng.core.api.bo.PersonCore;
import com.zqiheng.dto.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * description:
 * <p>PersonService .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 10:37
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
public class PersonService {

    @Autowired
    private PersonCore personCore;
    public void createCodeService(HttpServletResponse response, HttpSession session) throws IOException {
        BufferedImage bi = new BufferedImage(75,30,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        //设置填充颜色
        Color c = new Color(200,150,255);
        //设置字体大小样式
        Font font=new Font("宋体",Font.PLAIN,25);
        g.setColor(c);
        g.setFont(font);
        g.fillRect(0, 0, 75, 30);

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random r = new Random();
        int len=ch.length,index;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<4; i++){
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
            g.drawString(ch[index]+"", (i*15)+3, 18);
            sb.append(ch[index]);
        }
        session.setAttribute("piccode", sb.toString());
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    public ReturnValue checkAdminLoginService(HttpSession session, String personId, String password,
                                              String checkcode) {
        ReturnValue returnValue=new ReturnValue();
        if(!session.getAttribute("piccode").toString().toLowerCase().equals(checkcode.toLowerCase())){
            returnValue.setError("error");
            returnValue.setInfo("验证码错误");
            return returnValue;
        }
        //管理员登录
        if(personCore.cheakPersonLogin(personId, password)>0){
            returnValue.setSuccess("success");
            returnValue.setInfo("登录成功");
            return returnValue;
        }else{
            returnValue.setError("error");
            returnValue.setInfo("用户名或密码错误");
            return returnValue;
        }
    }
}
