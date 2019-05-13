package com.zqiheng.dto;

import lombok.Data;

/**
 * description:
 * <p>ReturnValue .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 10:46
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Data
public class ReturnValue {

    private String success;

    private String error;

    private String info;

    private Object obj;

    public ReturnValue() {
    }

    public ReturnValue(String success, String error, String info, Object obj) {
        this.success = success;
        this.error = error;
        this.info = info;
        this.obj = obj;
    }

    public static ReturnValue createSuccess(String info,Object obj){
        return new ReturnValue("0",null,info,obj);
    }

    public static ReturnValue createError(String info,Object obj){
        return new ReturnValue(null,"1",info,obj);
    }
}
