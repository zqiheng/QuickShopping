package com.zqiheng.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * description:
 * <p>RetCode .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 14:04
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class RetCode<T> implements Serializable {

    private static final long serialVersionUID = 1882168130130358979L;

    private MyCode returnCode;                  // Return MyCode. it include return code and return message
    private T object;
}
