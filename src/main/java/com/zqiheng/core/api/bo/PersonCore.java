package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.entity.entitydo.Person;

/**
 * description:
 * <p>SopPerson .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:28
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface PersonCore extends BaseCore {

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/2/24 14:12:26
     */
    Person privilegeCheckPerson(Person person);

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return 
      * @author ZQI
      * @date 2019/4/21 10:48:37
     */
    long cheakPersonLogin(String personId, String password);
}
