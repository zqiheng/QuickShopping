package com.zqiheng.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * description:
 * <p>Response .<br/></p>
 * <p>
 * This Class use to define the response for all of controller methods.
 * </p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:24
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = -1939927943593233105L;

    private Integer code;
    private String message;
    private Object body;

    public Response(Integer code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
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
     * @date 2019/2/24 12:41:06
     */
    public static Response create(Integer code, String message, Object body) {
        return new Response(code, message, body);
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
     * @date 2019/2/24 12:41:09
     */
    public static Response createSucc(final Object body) {
        return new Response(QuckShopCode.SUCCESS_CODE, "Success", body);
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
     * @date 2019/2/24 12:41:12
     */
    public static Response createError(final String message) {
        return new Response(QuckShopCode.ERROR_CODE, message, null);
    }

    @Override
    public String toString() {
        return String.format("{\"code\":%d, \"message\":\"%s\", \"body\":%s}",
                code, message, body);
    }

}
