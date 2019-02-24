package com.zqiheng.common.utils;

import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.dto.MyCode;

/**
 * description:
 * <p>Validations .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:42
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class Validations {

    public Validations() {
    }

    public static void check(final boolean expression, final String message) {
        if (expression) {
            throw new ServiceException(MyCode.ERROR_CODE, message);
        }
    }

    public static boolean isSuccess(final MyCode code) {
        return code != null && MyCode.SUCCESS_CODE == code.getCode();
    }

    public static void isOK(final MyCode code) {
        if (code == null) {
            throw new RuntimeException("MyCode is null");
        }
        if (!isSuccess(code)) {
            throw new ServiceException(code.getCode(), code.getMessage());
        }
    }

    public static boolean isNotSuccess(MyCode code) {
        return !isSuccess(code);
    }

}
