package com.zqiheng.core.impl.bo;

import com.zqiheng.common.utils.ArrayUtils;
import com.zqiheng.common.utils.BaseMethod;
import com.zqiheng.common.utils.StringUtils;
import com.zqiheng.core.api.bo.ShopCore;
import com.zqiheng.core.impl.GenericCore;
import com.zqiheng.dto.Infos;
import com.zqiheng.entity.entitydo.Shop;
import com.zqiheng.repository.ShopDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * description:
 * <p>SopShopImpl .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 13:31
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
@Service
public class ShopCoreImpl extends GenericCore implements ShopCore {

    private final ShopDao shopDao;

    @Autowired
    public ShopCoreImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    /**
     * description:
     * <p>
     * 根据用户的经纬度来判断距离用户最近的商店
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return shop
     * @author ZQI
     * @date 2019/3/8 21:01:09
     */
    @Override
    public Infos.ShopInfos findTheSpecifyShopByUserPosition(String longitude, String latitude) {
        Infos.ShopInfos retVal = null;
        List<Shop> shops = shopDao.findAll();
        Map<Double, Shop> shopMap = new HashMap<>();
        if (!ArrayUtils.isEmpty(shops)) {
            shops.forEach(param -> {
                if (!StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(longitude)
                        && !StringUtils.isEmpty(param.getLatitude()) && !StringUtils.isEmpty(param.getLongitude())) {
                    double dis = BaseMethod.distance(Double.valueOf(longitude)
                            , Double.valueOf(latitude)
                            , Double.valueOf(param.getLongitude())
                            , Double.valueOf(param.getLatitude()));
                    shopMap.put(dis, param);
                }
            });
        }
        Map<Double, Shop> shopMap1 = BaseMethod.sortMapByKey(shopMap, true);
        Set<Map.Entry<Double, Shop>> entries = shopMap1.entrySet();
        for (Map.Entry<Double, Shop> entry : entries) {
            log.info("距离最近用户最近的商店距离为：" + entry.getKey() + " 商店为：" + entry.getValue());
            retVal = new Infos.ShopInfos();
            retVal.setDistance(entry.getKey());
            Shop value = entry.getValue();
            retVal.setId(value.getId());
            retVal.setCity(value.getCity());
            retVal.setCounty(value.getCounty());
            retVal.setDetailAddress(value.getDetailAddress());
            retVal.setProvince(value.getProvince());
            retVal.setShopID(value.getShopID());
            retVal.setShopName(value.getShopName());
            retVal.setShopTel(value.getShopTel());
            retVal.setShopType(value.getShopType());
            if (null != entry.getKey()) {
                break;
            }
        }
        return retVal;
    }
}
