package com.zqiheng.controller.web;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Product;
import com.zqiheng.entity.entitydo.ProductCategory;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.service.web.ProductCategoryService;
import com.zqiheng.service.wx.ProductService;
import com.zqiheng.service.wx.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description:
 * <p>ProductControllerForWeb .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 16:08
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Controller
@RequestMapping("/web/product")
public class ProductControllerForWeb {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * Web端获取所有商品信息
     * Todo: 需改进 返回商品的所属店铺名称
     *
     * @param model
     * @return
     */
    @RequestMapping("/query_product_list_info")
    public String queryShopListInfo(Model model) {
        List<Product> products = productService.getAllProductListForWeb();
        if (ArrayUtils.isNotEmpty(products)) {
            model.addAttribute("products", products);
            model.addAttribute("count", products.size());
        } else {
            model.addAttribute("products", null);
            model.addAttribute("count", 0);
        }
        return "product/product_list";
    }


    /**
     * 请求加载 添加商品的页面
     *
     * @return
     */
    @RequestMapping("/add_product_info")
    public String addProductInfo(Model model) {
        List<Shop> shops = shopService.getAllShopListInfoForWeb();
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategoryList();
        model.addAttribute("shops", shops);
        model.addAttribute("productCategories", productCategories);
        return "product/product_add";
    }

    /**
     * 添加商品信息
     *
     * @param product product
     * @return -
     */
    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    @ResponseBody
    public ReturnValue addProductInfo(Params.ProductInfoForAdd product) {
        Validations.check(null == product, "The input param shop info is null.");
        log.info("添加的商品信息为：" + product);
        return productService.addProductInfo(product);
    }


    /**
     * 删除指定商品信息
     *
     * @param id id
     * @return -
     */
    @RequestMapping(value = "/delete_product_info", method = RequestMethod.POST)
    @ResponseBody
    public ReturnValue deleteProductInfo(Integer id) {
        Validations.check(null == id, "The input param is null.");
        return productService.deleteProductInfo(id);
    }

    /**
     * web端：获取所有商品分类信息
     *
     * @return -
     */
    @RequestMapping(value = "/product_category_Info")
    public String getAllProductCategoryInfo(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategoryList();
        if (ArrayUtils.isNotEmpty(productCategories)) {
            model.addAttribute("productCategories", productCategories);
            model.addAttribute("count", productCategories.size());
        } else {
            model.addAttribute("productCategories", null);
            model.addAttribute("count", 0);
        }
        return "productCategory/productCategory_list";
    }

    /**
     * web端：请求加载类型信息添加页面
     *
     * @return 地址
     */
    @RequestMapping(value = "add_productCategory_info")
    public String addProductCategoryInfo() {
        return "productCategory/productCategory_add";
    }

    /**
     * web端：添加店铺类型信息
     *
     * @param productCategory productCategory
     * @return ReturnValue
     */
    @RequestMapping(value = "/add_productCategory")
    @ResponseBody
    public ReturnValue addProductCategoryInfo(ProductCategory productCategory) {
        Validations.check(null == productCategory, "addProductCategoryInfo():: The input param info is null...");
        log.info("添加的信息：" + productCategory);
        ProductCategory category = productCategoryService.addProductCategoryInfo(productCategory);
        if (null != category) {
            return ReturnValue.createSuccess("添加类型成功！", category);
        }
        return ReturnValue.createError("店铺类型已存在！", null);
    }

    /**
     * web端：删除指定商品类型
     *
     * @param id id
     * @return ReturnValue
     */
    @ResponseBody
    @RequestMapping(value = "/delete_productCategory_info")
    public ReturnValue deleteProductCategoryInfo(Integer id) {
        Validations.check(null == id, "deleteProductCategoryInfo():: The input param info is null...");
        log.info("删除的ID：" + id);
        boolean flag = productCategoryService.deleteProductCategoryInfo(id);
        if (flag) {
            return ReturnValue.createSuccess("删除成功！", null);
        }
        return ReturnValue.createError("删除失败！", null);
    }

}
