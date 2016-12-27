package com.xss.gxq.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xss.gxq.R;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 17:47
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public abstract void initView();

    public abstract void initData();

    //设置顶部标题
    protected void initTitle(String txt) {
        ((TextView)findViewById(R.id.tv_title)).setText(txt);
    }

    //返回上一级按钮
    protected void initBackBtn(boolean isShow) {
        TextView tv_left_back = (TextView)findViewById(R.id.tv_left_back);
        if (isShow) {
            tv_left_back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            tv_left_back.setVisibility(View.INVISIBLE);
        }
    }


    ImageView img_title_right;
    protected void initRightBtn(int resId, boolean isShow) {
        img_title_right = (ImageView)findViewById(R.id.img_right);
        if (isShow) {
            img_title_right.setVisibility(View.VISIBLE);
            img_title_right.setImageResource(resId);
        } else {
            img_title_right.setVisibility(View.INVISIBLE);
        }
    }


    //进入Activity的动画
    public void startActivityAnimation(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    //结束Activity的动画
    public void finishAnimation() {
        finish();
        overridePendingTransition(R.anim.push_left_out, R.anim.push_right_in);
    }
}
