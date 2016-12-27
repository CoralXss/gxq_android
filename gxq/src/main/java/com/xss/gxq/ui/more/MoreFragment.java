package com.xss.gxq.ui.more;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xss.gxq.R;
import com.xss.gxq.db.AbsoluteProfitDB;
import com.xss.gxq.model.ProfitBarChartModel;
import com.xss.gxq.ui.adapter.ProfitBarChartAdapter;
import com.xss.gxq.ui.base.BaseFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/15 14:05
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class MoreFragment extends BaseFragment {
    private View view;

    private ListView lv_profit_list;
    private ArrayList<ProfitBarChartModel> profitBarChartModels;

    private TextView tv_open_popWindow;
    private boolean isOpen = false;
    private PopupWindow profitPopWindow;
    private ArrayList<HashMap<String, Object>> data;
    private TextView tv_profit_category;

    private TextView tv_tip;
    private TextView tv_average;

    private ProfitBarChartAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);

        initView();

        return view;
    }

    protected void initView() {
        lv_profit_list = (ListView)view.findViewById(R.id.lv_profit_list);
        profitBarChartModels = AbsoluteProfitDB.getWeekProfitBarChartModelList();
        adapter = new ProfitBarChartAdapter(getActivity(), profitBarChartModels);
        lv_profit_list.setAdapter(adapter);

        tv_tip = (TextView)view.findViewById(R.id.tv_tip);
        tv_tip.setText("近一月平均收益率");
        tv_average = (TextView)view.findViewById(R.id.tv_average);
        tv_average.setText(getAverageProfit()+"%");

        tv_open_popWindow = (TextView)view.findViewById(R.id.tv_open_popWindow);
        tv_open_popWindow.setText("七日年化收益率");
        tv_open_popWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOpen = !isOpen;
                if (isOpen) {
                    openPopWindow(tv_open_popWindow);
                } else {
                    if (profitPopWindow != null) {
                        profitPopWindow.dismiss();
                    }
                }

            }
        });
    }

    @Override
    protected void initData() {

    }

    protected void openPopWindow(TextView tv_popWindow) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_window_profit_category, null);
        profitPopWindow = new PopupWindow(view);
        profitPopWindow.setWidth(300);  //ViewGroup.LayoutParams.WRAP_CONTENT
        profitPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        profitPopWindow.setFocusable(true);
        profitPopWindow.setOutsideTouchable(true);
        //这里设置背景图片，是因为设置后，才能成功通过按钮控制popwindow的弹出和消失
        profitPopWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_profit_popwindow));

        ListView listView = (ListView) view.findViewById(R.id.lv_profit_category);
        data = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "七日年化收益率");   data.add(map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "万份年化收益率");   data.add(map1);
        final SimpleAdapter adapter0 = new SimpleAdapter(getActivity(), data, R.layout.pop_window_profit_item,
                new String[]{"name"}, new int[]{R.id.tv_profit_name});
        listView.setAdapter(adapter0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    profitBarChartModels = AbsoluteProfitDB.getWeekProfitBarChartModelList();

                    tv_tip.setText("近一月平均收益率");
                    tv_average.setText(getAverageProfit()+"%");
                    tv_open_popWindow.setText("七日年化收益率");
                } else if (position == 1) {
                    profitBarChartModels = AbsoluteProfitDB.getWanfenProfitBarChartModelList();
                    tv_tip.setText("近一月平均万份收益（元）");
                    tv_average.setText(getAverageProfit()+"");
                    tv_open_popWindow.setText("万份年化收益率");
                }
                adapter = new ProfitBarChartAdapter(getActivity(), profitBarChartModels);
                lv_profit_list.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                Toast.makeText(getActivity(), data.get(position).get("name").toString(), Toast.LENGTH_SHORT).show();
                tv_open_popWindow.setText(data.get(position).get("name").toString());
                isOpen = false;
                profitPopWindow.dismiss();
            }
        });

        int[] location = new int[2];
        int tH = 0;
        if (tv_popWindow != null && tv_popWindow instanceof TextView) {
            Rect rect = new Rect();
            tv_popWindow.getLocalVisibleRect(rect);   //局部
            tv_popWindow.getGlobalVisibleRect(rect);  //全局
            tv_popWindow.getLocationInWindow(location); // 获取在当前窗口内的绝对坐标

            tH = tv_popWindow.getHeight();
            tv_popWindow.getLocationOnScreen(location); //计算该视图在全局坐标系中的x，y值
        }
        profitPopWindow.showAsDropDown(tv_popWindow, 0, 0);

     //   Log.e("jjjj", location[0] + ", " + location[1] + ", " + tv_popWindow.getWidth());
        //      profitPopWindow.showAtLocation(tv_popWindow, Gravity.CENTER_HORIZONTAL | Gravity.TOP, location[0]-tv_popWindow.getWidth(), location[1] + tH);
        profitPopWindow.update();
    }

    private String getAverageProfit() {
        float sum = 0f;
        for (ProfitBarChartModel model: profitBarChartModels) {
            sum += model.getProfit_rate();
        }
        BigDecimal b = new BigDecimal(sum / profitBarChartModels.size());
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
    }


}
