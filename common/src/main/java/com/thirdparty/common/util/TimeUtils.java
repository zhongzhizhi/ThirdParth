package com.thirdparty.common.util;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static final String FORMAT1 = "MM-dd ";

    /**获取当前时间指定格式*/
    public static String getCurFormatTime(String format){
        SimpleDateFormat sf = new SimpleDateFormat(format, Locale.CHINA);
        return sf.format(new Date());
    }

    /**
     * 获取格式为“yyyyMMdd HHmmss”的当前日期
     *
     * @return
     */
    public static String getTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        String timeStr = format.format(new Date(time));
        return timeStr;
    }

    public static String getSpecifyTime(String millisTime){
        if (millisTime == null || millisTime.isEmpty()) return "";
        Date date = new Date(Long.parseLong(millisTime));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    public static String getSpecifyTime(String millisTime,String pattern){
        if (millisTime == null || millisTime.isEmpty()) return "";
        Date date = new Date(Long.parseLong(millisTime));
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(date);
    }

    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
        String timeStr = format.format(new Date(time));
        return timeStr;
    }

    public static String msToHHMMSS(float ms) {
        float duration = ms;
        duration = duration / 1000;
        int hour = (int) duration / (60 * 60);
        float f = duration % (60 * 60);
        int min = (int) f / 60;
        f = f % 60;
        int sec = (int) f;

        String h, m, s;
        if (hour < 10) {
            h = "0" + hour;
        } else {
            h = "" + hour;
        }
        if (min < 10) {
            m = "0" + min;
        } else {
            m = "" + min;
        }
        if (sec < 10) {
            s = "0" + sec;
        } else {
            s = "" + sec;
        }
        if (false) {
            Log.d("MyUtils", "" + h + ":" + m + ":" + s);
        }
        return "" + h + ":" + m + ":" + s;
    }

    /**
     * 获取时间间隔
     *
     * @param millisecond
     * @return 是否需要换行显示
     */
    public static boolean getSpaceTime(Long millisecond) {
        Calendar calendar = Calendar.getInstance();
        Long currentMillisecond = calendar.getTimeInMillis();
        //间隔秒
        Long spaceSecond = (currentMillisecond - millisecond) / 1000;
        if (spaceSecond > 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将秒转化为 HH:mm:ss 的格式
     *
     * @param time 秒
     * @return
     */
    public static String FormatTime(int time) {
        DecimalFormat decimalFormat = null;
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat("00");
        }
        String hh = decimalFormat.format(time / 3600);
        String mm = decimalFormat.format(time % 3600 / 60);
        String ss = decimalFormat.format(time % 60);
        return hh + ":" + mm + ":" + ss;
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
    private static TimeZone timeZone = TimeZone.getTimeZone("GMT");

    public static String getHHmmSSTime(long time) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date(time));
    }


    public static String getNormalTime(Long time) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date(time));
    }

    public static String getCurrentFormatTime(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS",Locale.CHINA);
        return dateFormat.format(new Date(time));
    }

    public static String getNormalFormatTime(Long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm",Locale.CHINA);
        return dateFormat.format(new Date(time));
    }

}
