package Tool;

import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 *
 * @author broucetrong
 * @version V1.0
 * @since 2018/6/25
 */
public class DateUtils {

    /**
     * 获得入参日期下周一的日期
     *
     * @param date 入参日期
     * @return 入参日期的下周一
     */
    public static Date getNextMonday(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        // 获得入参日期是一周的第几天
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        // 获得入参日期相对于下周一的偏移量（在国外，星期一是一周的第二天，所以下周一是这周的第九天）
        // 若入参日期是周日，它的下周一偏移量是1
        int nextMondayOffset = dayOfWeek == 1 ? 1 : 9 - dayOfWeek;

        // 增加到入参日期的下周一
        cd.add(Calendar.DAY_OF_MONTH, nextMondayOffset);
        return cd.getTime();
    }

    /**
     * 获得入参日期下周日的日期
     *
     * @param date 入参日期
     * @return 入参日期的下周日
     */
    public static Date getNextSunday(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        // 获得入参日期是一周的第几天
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        // 获得入参日期相对于下周日的偏移量（在国外，星期一是一周的第二天，所以下周日相对于本周来说，是第15天）
        // 若入参日期是周日，它的下周日偏移量是7
        int nextMondayOffset = dayOfWeek == 1 ? 7 : 15 - dayOfWeek;

        // 增加到入参日期的下周日
        cd.add(Calendar.DAY_OF_MONTH, nextMondayOffset);
        return cd.getTime();
    }

    /**
     * 获得入参日期下个月的第一天
     *
     * @param date 入参日期
     * @return 入参日期下个月的第一天
     */
    public static Date firstDayOfNextMonth(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        //获取下个月第一天：
        cd.add(Calendar.MONTH, 1);
        //设置为1号,当前日期既为次月第一天
        cd.set(Calendar.DAY_OF_MONTH,1);

        return cd.getTime();
    }

    /**
     * 获得入参日期次年的第一天
     *
     * @param date 入参日期
     * @return 入参日期次年的第一天
     */
    public static Date firstDayOfNextYear(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        //获取次年第一天：
        cd.add(Calendar.YEAR, 1);
        //设置为1月1号,当前日期既为次年第一天
        cd.set(Calendar.MONTH, 0);
        cd.set(Calendar.DAY_OF_MONTH, 1);

        return cd.getTime();
    }
}
