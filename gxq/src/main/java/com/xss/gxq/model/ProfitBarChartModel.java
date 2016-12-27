package com.xss.gxq.model;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/21 15:05
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class ProfitBarChartModel extends BaseModel {
    private int type;      //0 代表七日  1代表万份
    private String profit_date;
    private float profit_rate;

    public ProfitBarChartModel(int type, String profit_date, float profit_rate) {
        this.type = type;
        this.profit_date = profit_date;
        this.profit_rate = profit_rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProfit_date() {
        return profit_date;
    }

    public void setProfit_date(String profit_date) {
        this.profit_date = profit_date;
    }

    public float getProfit_rate() {
        return profit_rate;
    }

    public void setProfit_rate(float profit_rate) {
        this.profit_rate = profit_rate;
    }
}
