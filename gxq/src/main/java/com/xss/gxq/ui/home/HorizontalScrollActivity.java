package com.xss.gxq.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xss.gxq.R;
import com.xss.gxq.utils.CalendarUtil;
import com.xss.gxq.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/23 10:44
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class HorizontalScrollActivity  extends Activity {

    List<CityItem> cityList;
    RelativeLayout itmel;
    GridView gridView;

    private CalendarUtil calendarUtil = new CalendarUtil();
    private int cur_year;
    private int cur_month;
    private int cur_day;
    private String currentDate = "";
    private boolean isLeapYear;
    private int daysOfMonth;

    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        getWidthAndHeight();   getCurrentDate();

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        gridView = (GridView) findViewById(R.id.grid);
        //setData();
        initDates();
        setGridView();
    }

    protected void getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);  //当期日期
        cur_year = Integer.parseInt(currentDate.split("-")[0]);
        cur_month = Integer.parseInt(currentDate.split("-")[1]);
        cur_day = Integer.parseInt(currentDate.split("-")[2]);
        Log.e("DAY", cur_year + ", " + cur_month);
    }

    private void initDates() {
        list = new ArrayList<HashMap<String, String>>();
        boolean isLeapYear = calendarUtil.isLeapYear(cur_year);
        int daysOfMonth = calendarUtil.getDaysOfMonth(isLeapYear, cur_month);
        for (int i = 1; i <= daysOfMonth; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", i+"");
            map.put("week", calendarUtil.getWeekByDate(cur_year, cur_month, i));
            list.add(map);
        }
    }

    protected void getWidthAndHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;    //得到宽度
        Constants.SCREEN_HEIGHT = dm.heightPixels;  //得到高度

        Log.e("jj", Constants.SCREEN_WIDTH + ", " + Constants.SCREEN_HEIGHT);
    }

    /**设置数据*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setWeek_name("日");
        item.setCityName("深圳");
        item.setCityCode("0755");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("一");
        item.setCityName("上海");
        item.setCityCode("021");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("二");
        item.setCityName("广州");
        item.setCityCode("020");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("三");
        item.setCityName("北京");
        item.setCityCode("010");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("四");
        item.setCityName("武汉");
        item.setCityCode("027");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("五");
        item.setCityName("孝感");
        item.setCityCode("0712");
        cityList.add(item);
        item = new CityItem();
        item.setWeek_name("六");
        item.setCityName("黄石");
        item.setCityCode("0713");

        cityList.add(item);
        item.setWeek_name("六");
        item.setCityName("黄冈");
        item.setCityCode("0713");
        cityList.add(item);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
//        int size = cityList.size();
        int size = list.size();
        int length = Constants.SCREEN_WIDTH / 7;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = Constants.SCREEN_WIDTH * (size);  //(int) (size * (length + 4) * density);
        int itemWidth = (int) (length);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
 //       gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
                list);
        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list0;
        ArrayList<HashMap<String, String>> list;
        public GridViewAdapter(Context _context, List<CityItem> _list) {
            this.list0 = _list;
            this.context = _context;
        }

        public GridViewAdapter(Context _context, ArrayList<HashMap<String, String>> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.horizontal_scroll_list_item, null);
            TextView tv_week = (TextView)convertView.findViewById(R.id.tv_week);
            TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            HashMap<String, String> map = list.get(position);

            tv_week.setText(map.get("week"));
            tv_date.setText(map.get("date"));
            return convertView;
        }
    }

    public class CityItem {
        private String week_name;
        private String cityName;
        private String cityCode;

        public String getWeek_name() {
            return week_name;
        }

        public void setWeek_name(String week_name) {
            this.week_name = week_name;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
    }
}
