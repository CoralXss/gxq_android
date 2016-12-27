package com.xss.gxq.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xss.gxq.R;
import com.xss.gxq.model.ProfitBarChartModel;
import com.xss.gxq.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/21 15:05
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class ProfitBarChartAdapter extends BaseAdapter {
    private ArrayList<ProfitBarChartModel> list;
    private Context ctx;

    public ProfitBarChartAdapter(Context ctx, ArrayList<ProfitBarChartModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private float getMaxProfitRate(ArrayList<ProfitBarChartModel> profitBarChartModels) {
        float max = profitBarChartModels.get(0).getProfit_rate();
        for (int i = 1; i < profitBarChartModels.size(); i++) {
            float rate = profitBarChartModels.get(i).getProfit_rate();
            if (rate > max) {
                max = rate;
            }
        }
        return max;
    }

    //获取收益率占的长度
    private int setRatio(float ratio) {
        float max = getMaxProfitRate(list);
        BigDecimal b1 = null;
        BigDecimal b2 = null;
        if (!"".equals(ratio)) {
            b1 = new BigDecimal(ratio);
            if (max != 0) {
                b2 = new BigDecimal(max);
                try {
                    return (int) (b1.divide(b2, 3).floatValue()* (0.75 * Constants.SCREEN_WIDTH - 30));  //保留三位小数
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return -999;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_wanfen_profit_item, null);
            holder = new ViewHolder();

            holder.tv_profit_date = (TextView)view.findViewById(R.id.tv_profit_date);
            holder.tv_profit = (TextView)view.findViewById(R.id.tv_profit);
          //  holder.ll_rate = (LinearLayout)view.findViewById(R.id.ll_rate);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tv_profit_date.setText(list.get(i).getProfit_date());

        if (list.get(i).getType() == 0) {
            holder.tv_profit.setText(list.get(i).getProfit_rate() + "%");
        } else {
            holder.tv_profit.setText(list.get(i).getProfit_rate() + "");
        }


        Log.e("jjj", setRatio(list.get(i).getProfit_rate()) + "");
        holder.tv_profit.setLayoutParams(new LinearLayout.LayoutParams(setRatio(list.get(i).getProfit_rate()),
                ViewGroup.LayoutParams.WRAP_CONTENT));
        if (i == 0) {
            holder.tv_profit_date.setBackgroundColor(ctx.getResources().getColor(R.color.COLOR_FD6138));
            holder.tv_profit.setBackgroundColor(ctx.getResources().getColor(R.color.COLOR_FD6138));
        }

        return view;
    }

    class ViewHolder {
        TextView tv_profit_date;
        TextView tv_profit;
        LinearLayout ll_rate;
    }
}
