package com.zqiheng.core.impl.bo;

import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.core.api.bo.ProductCategoryCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.entity.entitydo.ProductCategory;
import com.zqiheng.repository.ProductCategoryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * description:
 * <p>ProductCategoryCoreImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/4/21         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/4/21 18:41
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class ProductCategoryCoreImpl extends GenericCore implements ProductCategoryCore {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getAllProductCategoryList() {
        return productCategoryDao.findAll();
    }

    @Override
    public ProductCategory getOneProductCategoryInfo(Integer id) {
        return productCategoryDao.findById(id).orElse(null);
    }

    @Override
    public ProductCategory addProductCategoryInfo(ProductCategory productCategory) {
        if(null == productCategory){
            return null;
        }
        ProductCategory category = productCategoryDao.findOne((Specification<ProductCategory>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("productType"),productCategory.getProductType())).orElse(null);
        if(null != category){
            log.info("重复的店铺类型：" + productCategory.getProductType());
            return null;
        } else {
            return productCategoryDao.save(productCategory);
        }
    }

    @Override
    public boolean deleteProductCategoryInfo(Integer id) {
        if (null == id) {
            return false;
        } else {
            ProductCategory category = productCategoryDao.findById(id).orElse(null);
            if (null != category) {
                productCategoryDao.delete(category);
                return true;
            } else {
                return false;
            }
        }
    }
}
