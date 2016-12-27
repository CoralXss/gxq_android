package com.xss.gxq.db;

import com.xss.gxq.model.AbsoluteProfitModel;
import com.xss.gxq.model.ProfitBarChartModel;

import java.util.ArrayList;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/16 19:22
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AbsoluteProfitDB {

    public static ArrayList<AbsoluteProfitModel> getProfitModelList() {
        ArrayList<AbsoluteProfitModel> profitModels = new ArrayList<AbsoluteProfitModel>();
        AbsoluteProfitModel model = new AbsoluteProfitModel("滚盈贷365期", "6.00%", "32天", "0.34");
        profitModels.add(model);
        model = new AbsoluteProfitModel("新人专享94期", "8.00%", "13天", "1");
        profitModels.add(model);
        model = new AbsoluteProfitModel("新人专享92期", "9.00%", "365天", "1");
        profitModels.add(model);
        model = new AbsoluteProfitModel("新人专享90期", "10.00%", "181天", "0");
        profitModels.add(model);

        return profitModels;
    }

    public static ArrayList<ProfitBarChartModel> getWeekProfitBarChartModelList() {
        ArrayList<ProfitBarChartModel> profitBarChartModels = new ArrayList<ProfitBarChartModel>();
        ProfitBarChartModel model = new ProfitBarChartModel(0, "2015-09-18", 1.1920f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(0, "2015-09-19", 3.0140f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(0, "2015-09-20", 2.2460f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(0, "2015-09-21", 1.5170f);
        profitBarChartModels.add(model);

        return profitBarChartModels;
    }

    public static ArrayList<ProfitBarChartModel> getWanfenProfitBarChartModelList() {
        ArrayList<ProfitBarChartModel> profitBarChartModels = new ArrayList<ProfitBarChartModel>();
        ProfitBarChartModel model = new ProfitBarChartModel(1,  "2015-09-18", 0.842f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(1, "2015-09-19", 0.874f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(1, "2015-09-20", 0.906f);
        profitBarChartModels.add(model);
        model = new ProfitBarChartModel(1, "2015-09-21", 0.999f);
        profitBarChartModels.add(model);

        return profitBarChartModels;
    }

}
