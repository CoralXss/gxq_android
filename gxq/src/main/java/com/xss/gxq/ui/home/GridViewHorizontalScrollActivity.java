package com.xss.gxq.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseActivity;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 17:51
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class GridViewHorizontalScrollActivity extends BaseActivity {
    private IndicatorViewPager indicatorViewPager;
    private ViewPager viewPager;
    private Indicator fixedIndicatorView;

    private LayoutInflater inflater;

    private GridView grid;
    String[] tv_txt_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        initData();
        initView();
         setData();
    }

    @Override
    public void initView() {
        inflater = LayoutInflater.from(getApplicationContext());
        grid = (GridView)findViewById(R.id.grid);

        int size = tv_txt_arr.length;
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int)(d.getWidth()*0.95), LinearLayout.LayoutParams.FILL_PARENT);
       grid.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        grid.setColumnWidth(itemWidth); // 设置列表项宽
        grid.setHorizontalSpacing(5); // 设置列表项水平间距
        grid.setStretchMode(GridView.NO_STRETCH);
        grid.setNumColumns(size); // 设置列数量=列表集合数
    }

    @Override
    public void initData() {
        tv_txt_arr = new String[]{"1", "2", "3", "4", "5", "6", "7"};
    }

    /**设置数据*/
    private void setData() {
        grid.setAdapter(new GridViewAdapter(this, tv_txt_arr));
    }

    private class GridViewAdapter extends BaseAdapter {
        String[] tv_txt_arr;
        private Context ctx;

        public GridViewAdapter(Context ctx, String[] tv_txt_arr) {
            this.ctx = ctx;
            this.tv_txt_arr = tv_txt_arr;
        }

        @Override
        public int getCount() {
            return tv_txt_arr.length;
        }

        @Override
        public Object getItem(int i) {
            return tv_txt_arr[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.grid_view_item, null);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tv_1 = (TextView) convertView.findViewById(R.id.tv);
//            holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
//            holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
//            holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
//            holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
//            holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
//            holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);


            holder.tv_1.setText(tv_txt_arr[i]);
//            holder.tv_2.setText(arr[1]);
//            holder.tv_3.setText(arr[2]);
//            holder.tv_4.setText(arr[3]);
//            holder.tv_5.setText(arr[4]);
//            holder.tv_6.setText(arr[5]);
//            holder.tv_7.setText(arr[6]);

            return convertView;
        }

        class ViewHolder {
            TextView tv_1;
//            TextView tv_2;
//            TextView tv_3;
//            TextView tv_4;
//            TextView tv_5;
//            TextView tv_6;
//            TextView tv_7;
        }
    }


    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
        private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
        private ViewHolder holder = null;

        private String[][] tv_txt_arr = {{"1", "2", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"}};

        @Override
        public int getCount() {
            return tv_txt_arr.length;
        }

        //初始化小圆点
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.indicator_tab_guide, container, false);
                }
                return convertView;
            //return null;
        }

        //初始化指引图片
        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
//                convertView = new View(getApplicationContext());
//                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.viewpager_guide_test, container, false);
               convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
            holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
            holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
            holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
            holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
            holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
            holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);

            String[] arr = tv_txt_arr[position];

                holder.tv_1.setText(arr[0]);
                holder.tv_2.setText(arr[1]);
                holder.tv_3.setText(arr[2]);
                holder.tv_4.setText(arr[3]);
                holder.tv_5.setText(arr[4]);
                holder.tv_6.setText(arr[5]);
                holder.tv_7.setText(arr[6]);


            return convertView;
        }

        class ViewHolder {
            TextView tv_1;
            TextView tv_2;
            TextView tv_3;
            TextView tv_4;
            TextView tv_5;
            TextView tv_6;
            TextView tv_7;
        }
    };
}
