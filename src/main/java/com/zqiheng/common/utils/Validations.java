package com.zqiheng.common.utils;

import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.dto.QuckShopCode;

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
            throw new ServiceException(QuckShopCode.ERROR_CODE, message);
        }
    }

    public static boolean isSuccess(final QuckShopCode code) {
        return code != null && QuckShopCode.SUCCESS_CODE == code.getCode();
    }

    public static boolean isNotSuccess(QuckShopCode code) {
        return !isSuccess(code);
    }

}
