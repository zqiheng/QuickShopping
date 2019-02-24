package com.zqiheng.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * <p>DateUtils .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/2/24         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/2/24 10:51
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
public class DateUtils {

    public DateUtils() {
    }

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return
      * @author ZQI
      * @date 2019/2/24 11:04:09
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * description:
     * <p></p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 10:59:47
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    /**
     * description:
     * <p>
     * any string format can parse to date.
     * like:
     * 2019-01-01 00-00-00
     * 2019-01-01-00-00-00
     * 2019-01-01-00.00.00
     * 2019-01-01-00.00-00
     * </p>
     * change history:
     * date             defect             person             comments
     * -------------------------------------------------------------------------------------------------------------------
     *
     * @return
     * @author ZQI
     * @date 2019/2/24 11:00:09
     */
    public static Date parseStringToDate(String date) {
        try {
            String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1HH$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
            return new SimpleDateFormat(parse).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return 
      * @author ZQI
      * @date 2019/2/24 11:02:34
     */
    public static Timestamp parseStringToTimestamp(String str) {
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parseStringToDate(str)));
    }

     /**
      * description:
      * <p></p>
      * change history:
      * date             defect             person             comments
      * -------------------------------------------------------------------------------------------------------------------
      * 
      * @return 
      * @author ZQI
      * @date 2019/2/24 11:02:18
     */
    public static String parseTimestampToSpecFormat(Timestamp timestamp){
        return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS").format(timestamp);
    }

}
