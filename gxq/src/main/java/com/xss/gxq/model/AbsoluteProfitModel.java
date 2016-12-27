package com.xss.gxq.model;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/16 19:17
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AbsoluteProfitModel extends BaseModel {
    private String profit_title;
    private String profit_rate;
    private String profit_time;
    private String profit_finished_percent;

    public AbsoluteProfitModel() {
        
    }

    public AbsoluteProfitModel(String profit_title, String profit_rate, String profit_time, String profit_finished_percent) {
        this.profit_title = profit_title;
        this.profit_rate = profit_rate;
        this.profit_time = profit_time;
        this.profit_finished_percent = profit_finished_percent;
    }

    public String getProfit_title() {
        return profit_title;
    }

    public void setProfit_title(String profit_title) {
        this.profit_title = profit_title;
    }

    public String getProfit_rate() {
        return profit_rate;
    }

    public void setProfit_rate(String profit_rate) {
        this.profit_rate = profit_rate;
    }

    public String getProfit_time() {
        return profit_time;
    }

    public void setProfit_time(String profit_time) {
        this.profit_time = profit_time;
    }

    public String getProfit_finished_percent() {
        return profit_finished_percent;
    }

    public void setProfit_finished_percent(String profit_finished_percent) {
        this.profit_finished_percent = profit_finished_percent;
    }
}
