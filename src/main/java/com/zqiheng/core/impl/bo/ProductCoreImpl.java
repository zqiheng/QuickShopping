package com.zqiheng.core.impl.bo;

import com.zqiheng.core.api.bo.ProductCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.entity.entitydo.Product;
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

    @Override
    public List<Product> getAllProductList(){
        return productDao.findAll();
    }

    @Override
    public Product getProductInfo(String productID) {
        return productDao.findOne((Specification<Product>)
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("productID"),productID)).orElse(null);
    }
}
