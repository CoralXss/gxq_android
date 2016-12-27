package com.xss.gxq.utils;

import java.math.BigDecimal;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/22 10:53
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class NumberUtil {

    public static double getExpectedProfit(int deadline, float profit_rate, float money) {
        //利息=[ 1 + (期限 / 年) * 年化收益率] * 本金
        float result = ((float)deadline / (float)365) * profit_rate * money;;
        BigDecimal b = new BigDecimal(result);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //使较大数值的double数据不用科学记数法显示
    public static String doubleFormat(String amount) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(Double.parseDouble(amount)) + "";
    }
}
