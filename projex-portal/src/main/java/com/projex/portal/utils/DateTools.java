package com.projex.portal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateTools {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取两个日期间的日期列表
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static List<String> getDateList(String start, String end) throws ParseException {
        // 转换日期类型 string -> date
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);

        List<String> listDate = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
                listDate.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            return listDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDate;
    }

    /**
     * 获取两个时间段之间的天数差
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static Long getDiffByDate(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse(start);
        Date secondDate = sdf.parse(end);
        long diffInMills = Math.abs(secondDate.getTime() - firstDate.getTime());
        return TimeUnit.DAYS.convert(diffInMills, TimeUnit.MILLISECONDS);
    }
}
