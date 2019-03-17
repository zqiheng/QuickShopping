package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.*;
import com.zqiheng.core.api.bo.OrdersCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.*;
import com.zqiheng.repository.OrderDetailsDao;
import com.zqiheng.repository.OrdersDao;
import com.zqiheng.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 * <p>SopOrdersImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:27
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class OrdersCoreImpl extends GenericCore implements OrdersCore {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private ProductDao productDao;


    @Override
    public Orders addOrdersInfoByUserInfo(Params.AddOrdersInfo ordersInfo) {
        if (null == ordersInfo) {
            return null;
        }
        User user = convertIdToObject(User.class, ordersInfo.getUserObj());
        Validations.check(null == user, "The user info is null...");
        Shop shop = convertIdToObject(Shop.class, ordersInfo.getShopObj());
        Validations.check(null == shop, "The shop info is null...");

        Orders orders = new Orders();
        orders.setOrdersID(BaseMethod.generateId());
        orders.setOrdersCreateTime(DateUtils.getCurrentTimeStamp());
        orders.setShopObj(shop.getId());
        orders.setUserObj(user.getId());
        orders.setPickMode(ordersInfo.isPickUpOnself());
        orders.setOrdersRemark(ordersInfo.getOrdersRemark());

        /**  订单状态：0：已完成、1：待自提、2：待配送、3：待收货、4：已取消 */
        // if payFlag is true.
        if (ordersInfo.isPayFlag()) {
            if (ordersInfo.isPickUpOnself()) {
                // 订单改为待自提状态
                orders.setOrdersType(1);
                // 用户自提，不用关联配送地址
                orders.setAddressObj(null);
            } else {
                // 订单改为待配送状态
                orders.setOrdersType(2);
                // 用户选择派送，需要关联地址
                UserAddress userAddress = convertIdToObject(UserAddress.class, ordersInfo.getAddressObj());
                Validations.check(null == userAddress, "The consignee address info is null...");
                orders.setAddressObj(userAddress.getId());
            }
        } else {
            // 用户取消付款，订单改为已取消状态
            orders.setOrdersType(4);
        }
        List<Params.GoodsItems> goodsItemsList = ordersInfo.getGoodsItems();
        // 计算该订单所有商品的数量和购买价格
        int totalNum = 0;
        float totalMoney = 0;
        for (Params.GoodsItems items : goodsItemsList) {
            totalNum += items.getCount();
            totalMoney += (items.getCount() * items.getPrice());
        }
        orders.setTotalMoney(totalMoney);
        orders.setTotalNum(totalNum);
        Orders savedOrders = ordersDao.save(orders);

        // 保存订单商品信息
        List<OrderDetails> orderDetails = new ArrayList<>();
        if (!ArrayUtils.isEmpty(goodsItemsList)) {
            goodsItemsList.forEach(param -> {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderDetailsID(savedOrders.getOrdersID());
                orderDetail.setOrderObj(savedOrders.getId());
                orderDetail.setProductBuyTime(DateUtils.getCurrentTimeStamp());
                orderDetail.setProductNum(param.getCount());
                orderDetail.setProductObj(param.getGoodsId());
                orderDetail.setProductPrice(param.getPrice());
                /*************** 减少购买商品的库存信息 **************/
                Product product = convertIdToObject(Product.class,param.getGoodsId());
                Validations.check(null == product,"The product Info is null");
                product.setSellQuantity((null == product.getSellQuantity() ? 0 : product.getSellQuantity()) + param.getCount());
                product.setStockQuantity(product.getStockQuantity() - param.getCount());
                productDao.save(product);

                orderDetails.add(orderDetail);
            });
        } else {
            Validations.check(true, "The goods list in null.");
        }
        List<OrderDetails> saveAll = orderDetailsDao.saveAll(orderDetails);
        Validations.check(ArrayUtils.isEmpty(saveAll), "Save the Order Details fail...");
        return savedOrders;
    }

    @Override
    public List<Infos.OrdersInfo> getOrdersInfoByUserObj(int userObj) {
        User user = convertIdToObject(User.class, userObj);
        Validations.check(null == user, "The user is null...");
        List<Infos.OrdersInfo> retVal = new ArrayList<>();
        List<Orders> ordersList = ordersDao.findAll((Specification<Orders>)
                (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userObj"), userObj));
        // 根据ID 倒序排序
        ordersList = ordersList.stream().sorted(Comparator.comparing(Orders::getId).reversed()).collect(Collectors.toList());
        if (!ArrayUtils.isEmpty(ordersList)) {
            ordersList.forEach(param -> {
                Infos.OrdersInfo ordersInfo = new Infos.OrdersInfo();
                ordersInfo.setOrdersId(param.getOrdersID());
                ordersInfo.setOrdersRemark(param.getOrdersRemark());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ordersInfo.setOrdersCreateTime(null == param.getOrdersCreateTime() ? null : sdf.format(param.getOrdersCreateTime()));
                ordersInfo.setOrdersCompleteTime(null == param.getOrdersCompleteTime() ? null : sdf.format(param.getOrdersCompleteTime()));
                ordersInfo.setOrdersType(param.getOrdersType());
                ordersInfo.setPickUpOneself(param.isPickMode());
                ordersInfo.setTotalMoney(param.getTotalMoney());
                ordersInfo.setTotalNum(param.getTotalNum());

                Shop shop = convertIdToObject(Shop.class, param.getShopObj());
                Validations.check(null == shop, "The shop info is null...");
                ordersInfo.setShopInfo(shop);

                ordersInfo.setUserInfo(user);

                if (param.getAddressObj() != null) {
                    UserAddress userAddress = convertIdToObject(UserAddress.class, param.getAddressObj());
                    ordersInfo.setAddressInfo(userAddress);
                }

                List<Infos.OrderDetailsInfo> orderDetailsInfoList = new ArrayList<>();
                List<OrderDetails> orderDetails = orderDetailsDao.findAll((Specification<OrderDetails>)
                        (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("orderObj"), param.getId()));
                if (!ArrayUtils.isEmpty(orderDetails)) {
                    orderDetails.forEach(x -> {
                        Infos.OrderDetailsInfo info = new Infos.OrderDetailsInfo();
                        info.setBuyTime(x.getProductBuyTime());
                        info.setProductNum(x.getProductNum());
                        info.setProductPrice(x.getProductPrice());
                        Product product = convertIdToObject(Product.class, x.getProductObj());
                        info.setProductInfo(product);
                        orderDetailsInfoList.add(info);
                    });
                }
                ordersInfo.setOrderDetailsInfoList(orderDetailsInfoList);
                retVal.add(ordersInfo);
            });
        }
        return retVal;
    }
}
