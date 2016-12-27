package com.xss.gxq.ui.myfinance;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseFragment;
import com.xss.gxq.ui.views.MyLineChartView;
import com.xss.gxq.utils.Constants;

import java.util.ArrayList;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/15 14:05
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class MyFinanceFragment extends BaseFragment {
    private View view;

    private LinearLayout ll;

    private LineChart lineChart;
    double coordData[] = new double[] {3.6014, 1.0601, 1.0898, 2.0740, 1.1334, 1.1023, 3.5293};  //该扇形控件，并不控制所有的扇形所占的比例相加等于100
    String xContent[] = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    private MyLineChartView myLineChartView;
    private String[] XLabels;
    private String[] YLabels;
    private String[] data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_finance, container, false);

        initView();
        initData();

        return view;
    }

    protected void initView() {
        lineChart = (LineChart)view.findViewById(R.id.lineChart);
        lineChart.animateX(2500);                    //设置从X轴显示的动画
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawGridLines(true);
        lineChart.getAxisRight().setEnabled(false);  //设置右边的Y轴不可见
        setData(7, 20);

        initMyLineData();
        myLineChartView = (MyLineChartView)view.findViewById(R.id.myChartView);
        myLineChartView.setYSpan(0.19f);
//        myLineChartView.setMarginLeftOrRight(10);                     //设置与父控件之间的左右间距
 //       myLineChartView.setXYPadding(10, 0, 10);        //设置XY轴文本与轴之间的间距
        myLineChartView.setXYSLength(Constants.SCREEN_WIDTH, 220);
        myLineChartView.setData(XLabels, YLabels, data, "七日年化收益率");
    }

    private void setData(int count, float range) {
        //1.设置X轴文本 - ArrayList<String>
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(xContent[i]);
        }

        //2.设置坐标轴数据 - ArrayList<Entry>
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            //float mult = range / 2f;
            // float val = (float) (Math.random() * mult) + 30;                          // + (float)
            // ((mult *
            float val = (float)coordData[i];       Log.e("Line", val + "");               // 0.1) / 10);
            yVals.add(new Entry(val, i));
        }

        LineDataSet dataSet1 = new LineDataSet(yVals, "Y轴数据集1");  //设置折线的属性
        dataSet1.setLineWidth(2f);
        dataSet1.setColor(Color.GREEN);
        dataSet1.setCircleSize(2f);

        //3.将Y轴数据集添加到列表中  - ArrayList<LineDataSet>
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(dataSet1);      //折线数据

        //4. 整合X轴和折线数据
        LineData data = new LineData(xVals, dataSets);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(11f);

        lineChart.setData(data);
    }

    protected void initData() {

    }

    /**
     * 注：因为没有显示原点，所以原点Y轴表示的数据是(y0=YLable[1] - y_span, XLabel[0]) ,
     * 	       所以折线上的数据需要大于y0，才能正常显示，不然就显示在X轴以下了
     */
    protected void initMyLineData() {
        XLabels = new String[]{"08-17", "08-18", "08-19", "08-20", "08-21", "08-22", "08-23"};  //X轴刻度文字
        YLabels = new String[]{"", "2.944", "3.131", "3.319", "3.499"};
        data = new String[]{"2.820", "3.15", "3.274", "3.25", "3.388", "3.183", "3.199"};  //显示的折线数据
    }

}
