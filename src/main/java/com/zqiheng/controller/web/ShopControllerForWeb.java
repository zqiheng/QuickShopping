package com.zqiheng.controller.web;

import com.sun.deploy.util.ArrayUtil;
import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.entity.entitydo.ShopCategory;
import com.zqiheng.service.web.ShopCategoryService;
import com.zqiheng.service.wx.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description:
 * <p>ShopController .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 12:54
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Controller
@RequestMapping("/web/shop")
public class ShopControllerForWeb {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

     /**
      * description:
      * <p>
      *     web端获取商铺列表信息请求加载页面
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/4/21 12:57:55
     */
    @RequestMapping("/query_shop_list_info")
    public String queryShopListInfo(Model model){
        List<Shop> shops = shopService.getAllShopListInfoForWeb();
        if(ArrayUtils.isNotEmpty(shops)){
            model.addAttribute("shop",shops);
            model.addAttribute("count",shops.size());
        } else {
            model.addAttribute("shop",null);
            model.addAttribute("count",0);
        }
        return "shop/shop_list";
    }


    /**
     * 请求加载新增店铺页面
     * @return 地址路由
     */
    @RequestMapping("/add_shop_info")
    public String addShopInfo(){
        return "shop/shop_add";
    }

    /**
     * 添加店铺信息
     * @param shop shop
     * @return
     */
    @RequestMapping(value = "/add_shop",method = RequestMethod.POST)
    @ResponseBody
    public ReturnValue addShopInfo(Shop shop){
        Validations.check(null == shop,"The input param shop info is null.");
        return shopService.addShopInfo(shop);
    }

    /**
     * 根据id删除店铺信息
     * @param id id
     * @return
     */
    @RequestMapping(value = "/delete_shop_info",method = RequestMethod.POST)
    @ResponseBody
    public ReturnValue deleteShopInfoByID(Integer id){
        Validations.check(null == id,"The input param is null.");
        return shopService.deleteShopInfo(id);
    }

    /**
     * web端：获取所有店铺类别信息
     * @param model model
     * @return
     */
    @RequestMapping(value = "/shop_category_Info")
    public String getAllShopCategoryInfo(Model model){
        List<ShopCategory> shopCategories= shopCategoryService.getAllShopCategoryInfo();
        if(ArrayUtils.isNotEmpty(shopCategories)){
            model.addAttribute("shopCategories",shopCategories);
            model.addAttribute("count",shopCategories.size());
        } else {
            model.addAttribute("shopCategories",null);
            model.addAttribute("count",0);
        }
        return "shopCategory/shopCategory_list";
    }


    /**
     * web端：请求加载添加店铺分类信息页面
     * @return
     */
    @RequestMapping(value = "/add_shopCategory_info")
    public String addShopCategoryInfo(){
        return "shopCategory/shopCategory_add";
    }

    /**
     * web端：添加店铺分类信息
     * @param shopCategory shopCategory
     * @return ReturnValue
     */
    @RequestMapping(value = "/add_shopCategory")
    @ResponseBody
    public ReturnValue addShopCategoryInfo(ShopCategory shopCategory){
        Validations.check(null == shopCategory,"addShopCategoryInfo(): The ShopCategory info is null.");
        ShopCategory category = shopCategoryService.addShopCategoryInfo(shopCategory);
        if(null != category){
            return ReturnValue.createSuccess("添加分类信息成功！",category);
        } else {
            return ReturnValue.createError("添加分类信息失败！",null);
        }
    }

    /**
     * 删除指定店铺分类信息
     * @param id id
     * @return ReturnValue
     */
    @RequestMapping(value = "/delete_shopCategory_info")
    @ResponseBody
    public ReturnValue deleteShopCategoryInfo(Integer id){
        Validations.check(null == id,"deleteShopCategoryInfo(): The input param id is null.");
        boolean flag = shopCategoryService.deleteShopCategoryInfo(id);
        if(flag){
            return ReturnValue.createSuccess("删除成功！",null);
        } else {
            return ReturnValue.createError("删除失败！",null);
        }
    }
}
