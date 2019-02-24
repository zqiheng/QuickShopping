package com.zqiheng.common.utils;

/**
 * description:
 * <p>BooleanUtils .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 10:49
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class BooleanUtils {

    public BooleanUtils() {
    }

    public static final String BOOLEAN_TRUE = "1";

    public static final String BOOLEAN_FALSE = "0";

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 10:50:21
     */
    public static boolean isTrue(Boolean flag) {
        return (null != flag) && flag;
    }

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 10:50:33
     */
    public static boolean isFalse(Boolean flag) {
        return (null == flag) || !flag;
    }

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 10:50:53
     */
    public static boolean getBoolean(String value) {
        boolean isBoolean = Boolean.getBoolean(value);
        if (!isBoolean) {
            isBoolean = BOOLEAN_TRUE.equals(value);
        }
        return isBoolean;
    }
}
