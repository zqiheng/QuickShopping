package com.zqiheng.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * <p>ServiceException .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:45
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Getter
@Setter
@Slf4j
public class ServiceException extends RuntimeException  {

    private static final long serialVersionUID = -2356383135991113844L;

    private Integer code;

    private Object data;

    public ServiceException(String pMessage) {
        super(pMessage);
    }

    public ServiceException(Integer pCode, String pMessage) {
        super(pMessage);
        this.code = pCode;
    }

    public ServiceException(Integer pCode, String pMessage, Object pData) {
        super(pMessage);
        this.code = pCode;
        this.data = pData;
    }

     /**
      * description: service exception do not need to trace stack,so override fillInStackTrace to optimize performance.
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/2/24 12:47:01
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
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
      * @date 2019/2/24 12:47:33
     */
    public <T> T getData(Class<T> classType) {
        T t = null;
        try {
            t = (T) data;
        } catch (Exception e) {
            log.error("getData(): convert data failed", e);
        }
        return t;
    }

    @Override
    public String toString() {
        return super.toString() + ", code=" + code;
    }
}
