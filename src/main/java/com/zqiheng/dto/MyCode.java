package com.zqiheng.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * description:
 * <p>QuckShopCode .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:29
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyCode {

    public static final int SUCCESS_CODE = 0;
    public static final int WARNING_CODE = 1;
    public static final int ERROR_CODE = 2;
    public static final int SYSTEM_ERROR = 2037;

    private int code;
    private String message;

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 12:41:23
     */
    public MyCode(String temp) {
        if (null == temp) {
            return;
        }
        int splitIndex = temp.indexOf(",");
        this.code = Integer.parseInt(temp.substring(1, splitIndex).trim());
        int index = temp.indexOf("\"");

        final int notFound = -1;
        if (notFound == index) {
            this.message = temp.substring(splitIndex + 1, temp.length() - 1).trim();
        } else {
            this.message = temp.substring(index + 1, temp.lastIndexOf("\"")).trim();
        }
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
     * @date 2019/2/24 12:41:27
     */
    public MyCode(int pCode, String pMessage) {
        this.code = pCode;
        this.message = pMessage;
    }

    public MyCode(MyCode shopCode, String... mes) {
        String tempMessage = shopCode.getMessage();
        this.code = shopCode.getCode();
        this.message = (mes.length >= count(tempMessage, "%s")) ? String.format(tempMessage, mes) : tempMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyCode target = (MyCode) o;
        return code == target.code;
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
     * @date 2019/2/24 12:41:35
     */
    public boolean equals(MyCode target) {
        if (target == null) {
            return false;
        }
        return this.code == target.getCode();
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + message.hashCode();
        return result;
    }

    private int count(String source, String subString) {
        int count = 0;
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(subString)) {
            return count;
        }
        int index = source.indexOf(subString);
        while (-1 != index) {
            count = count + 1;
            index = source.indexOf(subString, index + 1);
        }
        return count;
    }
}
