package com.wxj.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);

    public static Date LongToDate(long mills) {
        return new Date(mills);
    }

    public static long DateToLong(Date date) {
        return date.getTime();
    }

    public static Date DateFormat(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error("[DateFormat] err: ", e);
        }
        return date;
    }

    public static Date DateFormat(Long dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        Date date = null;
        try {
            date = new Date(dateString);
        } catch (Exception e) {
            logger.error("[DateFormat] err: ", e);
        }
        return date;
    }

    public static String DateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        return sdf.format(date);
    }

    public static Date getMonthAfterDay(Date base, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }
    public static Date getYearAfterDay(Date base, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }
    public static Date getMinuteAfterDay(Date base, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 根据当前时间和有效时长得出过期时间
     *
     * @param minutes 有效时长
     * @return
     */
    public static Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }
}
