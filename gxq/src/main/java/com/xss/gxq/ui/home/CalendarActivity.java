package com.xss.gxq.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xss.gxq.R;
import com.xss.gxq.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/23 10:44
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class CalendarActivity extends Activity {

    List<CityItem> cityList;
    RelativeLayout itmel;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        getWidthAndHeight();

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        gridView = (GridView) findViewById(R.id.grid);
        setData();
        setGridView();
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
        int size = cityList.size();
        int length = Constants.SCREEN_WIDTH / 7;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = Constants.SCREEN_WIDTH ;  //(int) (size * (length + 4) * density);
        int itemWidth = (int) (length);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        //       gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(7); // 设置列数量=列表集合数

//        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
//                cityList);
//        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
/*    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;
        public GridViewAdapter(Context _context, List<CityItem> _list) {
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
            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            CityItem city = list.get(position);

            tv_week.setText(city.getWeek_name());
            tvCity.setText(city.getCityName());
            tvCode.setText(city.getCityCode());
            return convertView;
        }
    }  */

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
