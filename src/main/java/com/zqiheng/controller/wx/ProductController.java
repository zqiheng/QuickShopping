package com.zqiheng.controller.wx;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.ObjectUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.Response;
import com.zqiheng.entity.entitydo.Product;
import com.zqiheng.service.wx.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * description:
 * <p>ProductController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/3/1         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/3/1 21:39
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/get_all_product_infos/req")
    public Response getAllProductInfosReq(@RequestBody Params.ProductsReq productsReq){
        Validations.check(null == productsReq,"The input param is null.");
        log.info("The input param shopObj = " + productsReq.getShopObj());
        Map<String, List<Product>> allProductListTest = productService.getAllProductList(productsReq.getShopObj());
        if(ObjectUtils.isEmpty(allProductListTest)){
           return Response.createError("Not find product infos.");
        }
        return Response.createSucc(allProductListTest);
    }

    @PostMapping(value = "/get_one_product_info/req")
    public Response getOneProductInfo(@RequestBody Params.ProductReq productReq){
        Validations.check(null == productReq,"The input param is null.");
        log.info("The input params  productID = " +productReq.getProductID() + " shopObj = " + productReq.getShopObj());
        Product product = productService.getProductInfo(productReq.getShopObj(),productReq.getProductID());
        if(null == product){
            return Response.createError("Not find the product info.");
        }
        return Response.createSucc(product);
    }
}
