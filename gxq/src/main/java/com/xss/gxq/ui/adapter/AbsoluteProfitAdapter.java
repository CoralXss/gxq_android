package com.xss.gxq.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xss.gxq.R;
import com.xss.gxq.model.AbsoluteProfitModel;
import com.xss.gxq.ui.views.CircularProgressDrawable;
import com.xss.gxq.ui.views.LabelView;

import java.util.ArrayList;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/16 12:44
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AbsoluteProfitAdapter extends BaseAdapter {
    private ImageView img_drawable;
    private CircularProgressDrawable drawable;
    private Animator rateAnimation;

    private Context ctx;
    private ArrayList<AbsoluteProfitModel> list;

    public AbsoluteProfitAdapter(Context ctx, ArrayList<AbsoluteProfitModel> list) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_absolute_profit_item, null);

            holder.ll_label = (LinearLayout)view.findViewById(R.id.ll_label);
            holder.tv_title = (TextView)view.findViewById(R.id.tv_title);
            holder.tv_year_profit = (TextView)view.findViewById(R.id.tv_year_profit);
            holder.tv_invest_time = (TextView)view.findViewById(R.id.tv_invest_time);
            holder.tv_rate = (TextView)view.findViewById(R.id.tv_rate);

            holder.img_drawable = (ImageView)view.findViewById(R.id.img_drawable);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tv_title.setText(list.get(i).getProfit_title());
        holder.tv_year_profit.setText(list.get(i).getProfit_rate());
        holder.tv_invest_time.setText(list.get(i).getProfit_time());

        float rate = Float.parseFloat(list.get(i).getProfit_finished_percent());
        addLabel(rate, holder.ll_label, holder.tv_rate);

        initView(holder.img_drawable, rate);  //完成率进度条
      //  holder.img_drawable.setImageDrawable(drawable);


        return view;
    }

    class ViewHolder {
        LinearLayout ll_label;
        TextView tv_title;
        TextView tv_year_profit;
        TextView tv_invest_time;
        ImageView img_drawable;
        TextView tv_rate;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void initView(ImageView img, float rate) {
        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(ctx.getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(ctx.getResources().getColor(R.color.color_E8E8E8))
                .setRingColor(ctx.getResources().getColor(R.color.color_FF9125))
                        //              .setCenterColor(getResources().getColor(android.R.color.holo_blue_dark))
                .create();
        img.setImageDrawable(drawable);

        rateAnimation = prepareStyle2Animation(rate);
        rateAnimation.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private Animator prepareStyle2Animation(float rate) {
        AnimatorSet animation = new AnimatorSet();
        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, rate);
        progressAnimation.setDuration(1200);
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                ctx.getResources().getColor(android.R.color.holo_red_dark),
                ctx.getResources().getColor(R.color.color_FF9125));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(1200);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
    }

    //给布局控件添加 标签  以及 设置 完成进度的文本值
    protected void addLabel(float rate, View v, TextView tv) {
        LabelView label = new LabelView(ctx);
        int rate0 = (int)(rate * 100);  Log.e("hh", rate0+"");
        if (rate0 == 100) {
            label.setText("售罄");
            label.setBackgroundColor(ctx.getResources().getColor(R.color.color_E8E8E8));
            label.setTargetView(v, 10, LabelView.Gravity.RIGHT_TOP);

            tv.setText("售罄");
            tv.setTextColor(ctx.getResources().getColor(R.color.color_E8E8E8));
        } else if (rate0 == 0) {

            label.setText("新人专享");
            label.setBackgroundColor(ctx.getResources().getColor(R.color.color_FF9125));
            label.setTargetView(v, 10, LabelView.Gravity.RIGHT_TOP);

            tv.setText(rate0 + "%");
            tv.setTextColor(ctx.getResources().getColor(R.color.color_FF9125));
        } else {
            label.setText("");
            label.setBackgroundColor(ctx.getResources().getColor(R.color.color_ffffff));
            label.setTargetView(v, 10, LabelView.Gravity.RIGHT_TOP);

            tv.setText(rate0 + "%");
            tv.setTextColor(ctx.getResources().getColor(R.color.color_FF9125));
        }
    }
}
