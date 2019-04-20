package com.zqiheng.core.impl.bo;

import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.common.utils.*;
import com.zqiheng.core.api.bo.OrdersCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.dto.Params;
import com.zqiheng.entity.entitydo.*;
import com.zqiheng.repository.OrderDetailsDao;
import com.zqiheng.repository.OrdersDao;
import com.zqiheng.repository.ProductDao;
import com.zqiheng.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Autowired
    private UserDao userDao;


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
        orders.setDiscountMoney(ordersInfo.getDiscountMoneys());
        if(ordersInfo.getDiscountMoneys() != 0){
            orders.setDiscount(true);
        } else {
            orders.setDiscount(false);
        }

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
    public List<Infos.OrdersInfo> getAllOrdersInfo(int userObj) {
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
                ordersInfo.setDiscount(param.isDiscount());
                ordersInfo.setDiscountMoneys(param.getDiscountMoney());

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

    @Override
    public Infos.OrdersInfo getOneOrdersInfo(int userObj,String ordersID) {
        User user = convertIdToObject(User.class, userObj);
        Validations.check(null == user, "The user is null...");
        Infos.OrdersInfo retVal = new Infos.OrdersInfo();
        Orders orders = null;
        if(!StringUtils.isEmpty(ordersID)){
            // 如果入参 ordersID 不为空，则返回该订单信息
            orders = ordersDao.findOne((Specification<Orders>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("userObj"),user.getId()),
                    criteriaBuilder.equal(root.get("ordersID"),ordersID)
            )).orElse(null);
        } else {
            // 如果入参 ordersID 为空，则返回该用户的最后一条订单
            List<Orders> ordersList = ordersDao.findAll((Specification<Orders>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userObj"), userObj));
            orders = ordersList.get(ordersList.size() - 1);
        }
        if(null != orders){
            retVal.setOrdersId(orders.getOrdersID());
            retVal.setOrdersRemark(orders.getOrdersRemark());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            retVal.setOrdersCreateTime(null == orders.getOrdersCreateTime() ? null : sdf.format(orders.getOrdersCreateTime()));
            retVal.setOrdersCompleteTime(null == orders.getOrdersCompleteTime() ? null : sdf.format(orders.getOrdersCompleteTime()));
            retVal.setOrdersType(orders.getOrdersType());
            retVal.setPickUpOneself(orders.isPickMode());
            retVal.setTotalMoney(orders.getTotalMoney());
            retVal.setTotalNum(orders.getTotalNum());
            retVal.setDiscount(orders.isDiscount());
            retVal.setDiscountMoneys(orders.getDiscountMoney());

            Shop shop = convertIdToObject(Shop.class, orders.getShopObj());
            Validations.check(null == shop, "The shop info is null...");
            retVal.setShopInfo(shop);
            retVal.setUserInfo(user);

            if (orders.getAddressObj() != null) {
                UserAddress userAddress = convertIdToObject(UserAddress.class, orders.getAddressObj());
                retVal.setAddressInfo(userAddress);
            }
            int id = orders.getId();
            List<Infos.OrderDetailsInfo> orderDetailsInfoList = new ArrayList<>();
            List<OrderDetails> orderDetails = orderDetailsDao.findAll((Specification<OrderDetails>)
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("orderObj"), id));
            if (!ArrayUtils.isEmpty(orderDetails)) {
                orderDetails.forEach(x -> {
                    Infos.OrderDetailsInfo info = new Infos.OrderDetailsInfo();
                    info.setBuyTime(x.getProductBuyTime());
                    info.setProductNum(x.getProductNum());
                    info.setProductPrice(x.getProductPrice());
                    Product product = convertIdToObject(Product.class, x.getProductObj());
                    Validations.check(null == product,"The product info is null....");
                    info.setProductInfo(product);
                    orderDetailsInfoList.add(info);
                });
            }
            retVal.setOrderDetailsInfoList(orderDetailsInfoList);
        }
        return retVal;
    }

    @Override
    public boolean confirmReceipt(int userObj, String ordersID) {
        User user = userDao.findOne((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), userObj)).orElse(null);
        Validations.check(null == user,"The user info is null..");
        Orders orders = ordersDao.findOne((Specification<Orders>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("ordersID"), ordersID)).orElse(null);
        Validations.check(null == orders,"The orders info is null..");

        if(orders.getOrdersType() == 3){
            // if ordersType == 3. change the state to 0.
            orders.setOrdersType(0);
            // add the complete time.
            orders.setOrdersCompleteTime(DateUtils.getCurrentTimeStamp());
            ordersDao.save(orders);
            return true;
        } else {
            throw new ServiceException("The Orders can't confirm receipt.");
        }
    }
}
