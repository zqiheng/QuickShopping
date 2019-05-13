package com.zqiheng.core.api.bo;

import com.zqiheng.core.api.BaseCore;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Product;

import java.util.List;

/**
 * description:
 * <p>SopProduct .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:29
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public interface ProductCore extends BaseCore {

    /**
     * description:
     * <p>
     * get all Product info.
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/5 21:34:16
     */
    public List<Product> getAllProductList(int shopObj);

    /**
     * description:
     * <p>
     * get one product info by the spec productID and shopObj.
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/5 21:34:34
     */
    public Product getProductInfo(int shopObj, String productID);

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 16:15:17
     */
    List<Product> getallProductListInfo();

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 17:51:39
     */
    ReturnValue addProductInfo(Params.ProductInfoForAdd product);

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/4/21 18:01:05
     */
    boolean deleteProudctInfo(Integer id);
}
