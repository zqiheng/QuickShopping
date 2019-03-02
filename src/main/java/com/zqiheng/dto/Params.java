package com.zqiheng.dto;

import com.zqiheng.entity.entitydo.Person;
import lombok.Data;

/**
 * description:
 * <p>Params .<br/></p>
 * <p>
 *     Front end request parameter.
 * </p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 12:22
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class Params {

    @Data
    public static class GetUserInfoParam {
        private Person person;
        private String userID;
    }

    @Data
    public static class UserInfo {
        private String nickName;
        private String gender;
        private String language;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
    }

    @Data
    public static class ProductID {
        private String productID;
    }
}
