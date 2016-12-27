package com.xss.gxq.utils;

import java.util.Calendar;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/24 10:53
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class CalendarUtil {
    private int daysOfMonth = 0;   //某月的天数
    private int dayOfWeek = 0;     //具体某一天是星期几

    //判断是否为闰年
    public boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    //得到某月有多少天
    public int getDaysOfMonth(boolean isLeapYear, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapYear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }
                break;
        }
        return daysOfMonth;
    }

    ////判断某年中的某一月的某一天是星期几
    public String getWeekByDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String week = "";
        switch (dayOfWeek) {
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
        }
        return week;

    }

    //指定某年中的某一月的第一天是星期几
    public int getDayOfWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return dayOfWeek;
    }

    //判断某一天是否是周六
    public boolean isSunday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY;//||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY
    }
}
