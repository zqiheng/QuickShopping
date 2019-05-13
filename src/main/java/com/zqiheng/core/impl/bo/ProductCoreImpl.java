package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.Validations;
import com.zqiheng.core.api.bo.ProductCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Params;
import com.zqiheng.dto.ReturnValue;
import com.zqiheng.entity.entitydo.Product;
import com.zqiheng.entity.entitydo.ProductCategory;
import com.zqiheng.repository.ProductCategoryDao;
import com.zqiheng.repository.ProductDao;
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
 * <p>SopProductImpl .<br/></p>
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
@Slf4j
@Service
public class ProductCoreImpl extends GenericCore implements ProductCore {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<Product> getAllProductList(int shopObj){
        return productDao.findAll((Specification<Product>)
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("shopObj"),shopObj));
    }

    @Override
    public Product getProductInfo(int shopObj, String productID) {
        return productDao.findOne((Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.equal(root.get("productID"), productID),
                        criteriaBuilder.equal(root.get("shopObj"), shopObj))).orElse(null);
    }

    @Override
    public List<Product> getallProductListInfo() {
        return productDao.findAll();
    }

    @Override
    public ReturnValue addProductInfo(Params.ProductInfoForAdd productInfoForAdd) {
        if(null == productInfoForAdd){
            return null;
        }
        // step1: check the productID is exist the spec shop.
        Product check = productDao.findOne((Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("productID"), productInfoForAdd.getProductID()),
                criteriaBuilder.equal(root.get("shopObj"), productInfoForAdd.getShopObj())
        )).orElse(null);
        if(null == check){
            // step2: get the productCategory info.
            ProductCategory productCategory = productCategoryDao.findById(productInfoForAdd.getProductCategoryObj()).orElse(null);
            Validations.check(null == productCategory,"The product category info is null.");

            // step3: save the productInfoForAdd info.
            Product product = new Product();
            product.setStockQuantity(productInfoForAdd.getStockQuantity());
            product.setSellQuantity(0);
            product.setFactoryName(productInfoForAdd.getFactoryName());
            product.setProductActivityPrice(null);
            product.setProductBrand(productInfoForAdd.getProductBrand());
            product.setProductID(productInfoForAdd.getProductID());
            product.setProductName(productInfoForAdd.getProductName());
            product.setProductNorm(productInfoForAdd.getProductNorm());
            product.setProductPackingUnit(productInfoForAdd.getProductPackingUnit());
            product.setProductPicture(productInfoForAdd.getProductPicture());
            product.setProductProposedPrice(productInfoForAdd.getProductProposedPrice());
            product.setProductRealPrice(productInfoForAdd.getProductRealPrice());
            product.setProductRemark(productInfoForAdd.getProductRemark());
            product.setProductType(productCategory.getProductType());
            product.setProductTypeName(productCategory.getProductTypeName());
            product.setRegisteredAddress(productInfoForAdd.getRegisteredAddress());
            product.setShopObj(productInfoForAdd.getShopObj());
            return ReturnValue.createSuccess("添加成功！",productDao.save(product));
        } else {
            return ReturnValue.createError("该商品已经存在！",null);
        }
    }

    @Override
    public boolean deleteProudctInfo(Integer id) {
        Product product = productDao.findById(id).orElse(null);
        if(null != product){
            productDao.delete(product);
            return true;
        }
        return false;
    }
}
