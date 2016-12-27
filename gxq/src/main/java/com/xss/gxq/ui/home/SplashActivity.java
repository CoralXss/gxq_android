package com.xss.gxq.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 17:52
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash();
    }

    protected void splash() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainTabActivity.class);
                startActivityAnimation(intent);
                finish();
            }
        };
        timer.schedule(task, 1000);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
