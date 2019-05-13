package com.zqiheng.common.utils;

import com.zqiheng.common.exception.ServiceException;
import com.zqiheng.dto.Infos;

import javax.sql.rowset.serial.SerialException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * description:
 * <p>BaseMethod .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 10:47
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class BaseMethod {

    private BaseMethod() {
    }

    private static final Object LOCKER_A = new Object();
    private static final Object LOCKER_B = new Object();

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 10:49:37
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> obj = ConcurrentHashMap.newKeySet();
        return t -> obj.add(keyExtractor.apply(t));
    }

    /**
     * description:
     * <p>
     * 根据两点经纬度计算距离
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @param longitude1 起点经度
     * @param latitude1  起点纬度
     * @param longitude2 终点经度
     * @param latitude2  终点纬度
     * @return 距离 千米
     * @author ZQI
     * @date 2019/3/8 20:41:56
     */
    public static double distance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // km 地球半径平均值
        double EARTH_RADIUS = 6371.0;

        // 将角度转化为弧度
        double radLat1 = ConvertDegreesToRadians(latitude1);
        double radLat2 = ConvertDegreesToRadians(latitude2);
        double radLon1 = ConvertDegreesToRadians(longitude1);
        double radLon2 = ConvertDegreesToRadians(longitude2);

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west

        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist;
    }

    /**
     * description:
     * <p>
     * 将角度转化为弧度
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return double
     * @author ZQI
     * @date 2019/3/8 20:37:45
     */
    private static double ConvertDegreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    /**
     * description:
     * <p>
     * 根据Map的Key值，对Map排序
     * asc: true  升序  1 2 3 ...
     * asc: false 降序  ...3 2 1
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/3/8 21:16:57
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortMapByKey(Map<K, V> map, boolean asc) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> stream = map.entrySet().stream();
        if (asc) {
            stream.sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            stream.sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

     /**
      * description:
      * <p>
      *     订单号生成规则【唯一】
      *
      * </p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      *
      * @return
      * @author ZQI
      * @date 2019/3/13 21:48:43
     */
    public static String generateId() {
        synchronized (LOCKER_A){
            Random random = new Random(System.currentTimeMillis());
            int value = random.nextInt();
            while (value < 10000000) {
                value = random.nextInt();
                if(value > 99999999){
                    continue;
                }
            }
            synchronized (LOCKER_B){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String format = simpleDateFormat.format(new Date());
                return format + value;
            }
        }
    }


    /**
     * 根据指定时间获取 指定时间所在周的周一和周天的日期
     * @param specTime 指定时间
     * @return
     */
    public static Infos.WeeklyInfo getFirstAndLastDateForWeekly(Date specTime){
        if(null == specTime){
            return null;
        }
        Infos.WeeklyInfo retVal = new Infos.WeeklyInfo();

        Calendar cal = Calendar.getInstance();
        cal.setTime(specTime);

        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        /*System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));*/
        retVal.setFirst(Timestamp.valueOf(sdf.format(cal.getTime())));
        cal.add(Calendar.DATE, 6);
        /*System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));*/
        retVal.setLast(Timestamp.valueOf(sdf.format(cal.getTime())));
        return retVal;
    }


    /**
     * 获取指定日期的后一天
     * @param specTime 指定日期
     * @return
     */
    public static Timestamp getNextDay(Date specTime){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specTime.toString());
        } catch (Exception e){
            throw new ServiceException("日期转换异常！");
        }

        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day + 1);
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }

}
