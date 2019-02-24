package com.zqiheng.common.constant;

/**
 * description:
 * <p>ConstEnum .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:05
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public enum ConstEnum {
    /**
     *
     */
    NULL(null),
    EMPTY(""),
    DOT("."),
    HYPHEN("-"),
    COMMA(","),
    PERCENT("%"),
    ASTERISK("*"),
    WELL("#"),;
    private String value;

    ConstEnum(String value) {
        this.value = value;
    }

    public static ConstEnum get(String value) {
        if (value instanceof String) {
            for (ConstEnum posConstEnum : ConstEnum.values()) {
                if (value.equals(posConstEnum.getValue())) {
                    return posConstEnum;
                }
            }
        }
        return NULL;
    }

    public String getValue() {
        return value;
    }

    public static boolean equals(ConstEnum posConstEnum, String value) {
        if (value instanceof String) {
            return value.equals(posConstEnum.getValue());
        }
        return (null == posConstEnum.getValue());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
