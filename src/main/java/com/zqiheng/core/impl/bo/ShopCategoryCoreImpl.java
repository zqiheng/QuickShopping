package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.Validations;
import com.zqiheng.core.api.bo.ShopCategoryCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.entity.entitydo.ShopCategory;
import com.zqiheng.repository.ShopCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <p>ShopCategoryCoreImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/27         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/27 15:17
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
public class ShopCategoryCoreImpl extends GenericCore implements ShopCategoryCore {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getAllShopCategoryInfo() {
        return shopCategoryDao.findAll();
    }

    @Override
    public ShopCategory addShopCategoryInfo(ShopCategory shopCategory) {
        if(null == shopCategory){
            return null;
        }
        return shopCategoryDao.save(shopCategory);
    }

    @Override
    public boolean deleteShopCategoryInfo(Integer id) {
        if (null == id) {
            return false;
        } else {
            ShopCategory shopCategory = shopCategoryDao.findById(id).orElse(null);
            if (null != shopCategory) {
                shopCategoryDao.delete(shopCategory);
                return true;
            } else {
                return false;
            }
        }
    }
}
