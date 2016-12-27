package com.xss.gxq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xss.gxq.ui.base.BaseActivity;
import com.xss.gxq.ui.home.GuideActivity;
import com.xss.gxq.ui.home.SplashActivity;
import com.xss.gxq.utils.Constants;

public class WelcomeActivity extends BaseActivity {
    private SharedPreferences sp;

    private boolean is_first_in = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

    }

    @Override
    public void initView() {
        sp = getSharedPreferences(Constants.SP_IS_FIRST_IN, 0);
        SharedPreferences.Editor editor = sp.edit();

        is_first_in = sp.getBoolean(Constants.IS_FIRST, false);  //第一次进入，默认值为false

        Intent intent = null;
        if (is_first_in) {   //第一次进入   false
            intent = new Intent(WelcomeActivity.this, SplashActivity.class);
        } else {
            intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        }
        editor.putBoolean(Constants.IS_FIRST, true);
        editor.commit();
        startActivityAnimation(intent);
        finish();
    }

    @Override
    public void initData() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
