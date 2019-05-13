package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.BaseMethod;
import com.zqiheng.common.utils.Validations;
import com.zqiheng.core.api.bo.StatisticCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.entity.entitydo.Orders;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.repository.OrdersDao;
import com.zqiheng.repository.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 * <p>StatisticCoreImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/5/8         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/5/8 13:55
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Service
public class StatisticCoreImpl extends GenericCore implements StatisticCore {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Infos.StatisticsSaleNumber> getSaleNumberForWeekly() {
        List<Infos.StatisticsSaleNumber> retVal = new ArrayList<>();

        // 【1】获取所有的店铺信息
        List<Shop> shops = shopDao.findAll();
        if(ArrayUtils.isNotEmpty(shops)){
            shops.forEach(x ->{
                Infos.StatisticsSaleNumber data = new Infos.StatisticsSaleNumber();
                data.setShopName(x.getShopName());

                // 【2】根据店铺ID查询出该店铺当前周的所有已完成订单

                //--------------------------------------------------------//
                //   根据当前时间计算当前周的周一和周七的日期             //
                //--------------------------------------------------------//
                Infos.WeeklyInfo weeklyInfo = BaseMethod.getFirstAndLastDateForWeekly(new Date());
                Validations.check(null == weeklyInfo,"The code is error.Please check...");
                //--------------------------------------------------------//
                // 根据条件组合查询指定订单（周一   ---   周七）          //
                //--------------------------------------------------------//
                List<Orders> orders = ordersDao.findAll((Specification<Orders>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("shopObj"), x.getId()),
                        criteriaBuilder.between(root.get("ordersCompleteTime"), weeklyInfo.getFirst(), weeklyInfo.getLast())
                ));

                //-------------------------------------------------------------------------------------------//
                //  对查询出的订单按照日期进行分组（按星期 分 7组），并统计每一组订单的销售商品的总量        //
                //-------------------------------------------------------------------------------------------//
                List<Orders> ordersList = orders.stream().sorted(Comparator.comparing(Orders::getOrdersCompleteTime)).collect(Collectors.toList());
                if(ArrayUtils.isNotEmpty(ordersList)){

                }

                retVal.add(data);
            });
        }
        return retVal;
    }
}
