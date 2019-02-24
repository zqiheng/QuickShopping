package com.zqiheng.aop;

import com.zqiheng.common.config.RetCodeConfig;
import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * description:
 * <p>ControllerAspect .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 16:30
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@ControllerAdvice
public class ControllerAspect {

    /**
     * ServiceException logger ,in case of close ServiceException log in the future.
     */
    private static final Logger SERVICE_LOGGER = LoggerFactory.getLogger(ServiceException.class);

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Response serviceExceptionHandler(ServiceException e) {
        SERVICE_LOGGER.error(">>>>>> serviceExceptionHandler(): ", e);
        return Response.create(e.getCode(), e.getLocalizedMessage(), e.getData());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Response errorHandler(Exception e) {
        log.error(">>>>>> errorHandler(): ", e);
        return Response.create(RetCodeConfig.SYSTEM_ERROR, StringUtils.isEmpty(e.getLocalizedMessage()) ? "System internal error, please contact the administrator!" : e.getLocalizedMessage(), null);
    }
}
