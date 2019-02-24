package com.zqiheng.common.config;

import com.zqiheng.dto.MyCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * description:
 * <p>RetCodeConfig .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:51
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@PropertySource("classpath:code.properties")
@ConfigurationProperties(prefix="rc")
@Component
@Setter
@Getter
public class RetCodeConfig {

    public static final int SUCCESS_CODE = 0;
    public static final int WARNING_CODE = 1;
    public static final int ERROR_CODE = 2;
    public static final int SYSTEM_ERROR = 2037;

    private MyCode succ;                          //succ = (0, "succ")
    private MyCode warn;                          //warn = (1, "%s")
    private MyCode error;                         //error = (2, "%s")
}
