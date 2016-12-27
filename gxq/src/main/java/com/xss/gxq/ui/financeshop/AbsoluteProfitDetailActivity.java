package com.xss.gxq.ui.financeshop;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseActivity;
import com.xss.gxq.utils.NumberUtil;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/21 20:09
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AbsoluteProfitDetailActivity extends BaseActivity {
    private SeekBar seekBar;
    private ImageView img_profit_picture;
    private TextView tv_profit;

    private TextView tv_deadline;
    private TextView tv_profit_rate;

    private ImageView img_compute_profit;  //计算器
    private Dialog profitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_profit_detail);
        initBackBtn(true);
        initRightBtn(R.drawable.pic_share, true);
        initTitle("");

        initView();
    }

    @Override
    public void initView() {
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        img_profit_picture = (ImageView)findViewById(R.id.img_profit_picture);
        tv_profit = (TextView)findViewById(R.id.tv_profit);

        tv_deadline = (TextView)findViewById(R.id.tv_deadline);
        tv_profit_rate = (TextView)findViewById(R.id.tv_profit_rate);

        img_compute_profit = (ImageView)findViewById(R.id.img_compute_profit);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                float money = (200000-100) / 100 * (seekBar.getProgress()) + 100;
                int deadline = Integer.parseInt("33");  //tv_deadline.getText().toString()
                float profit_rate = Float.parseFloat("0.08");  //
                tv_profit.setText(NumberUtil.getExpectedProfit(deadline, profit_rate, money) + "");

                Log.e("jj", seekBar.getProgress() + ",  " + NumberUtil.getExpectedProfit(deadline, profit_rate, money));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        img_compute_profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfitDialog();
            }
        });
    }

    private TextView tv_profit_dialog;
    protected void openProfitDialog() {
        profitDialog = new Dialog(this, R.style.myDialogTheme);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_compute_profit, null);
        profitDialog.setContentView(v);

        final EditText edt_money = (EditText)v.findViewById(R.id.edt_money);
        tv_profit_dialog = (TextView)v.findViewById(R.id.tv_profit_dialog);
        edt_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String money = edt_money.getText().toString();
                if (!"".equals(money)) {
                    String rate = NumberUtil.getExpectedProfit(33, 0.08f, Float.parseFloat(money))+"";
                    tv_profit_dialog.setText(NumberUtil.doubleFormat(rate));
                }
            }
        });
        ImageView img_close = (ImageView)profitDialog.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profitDialog.dismiss();
            }
        });


        Window dialogWindow = profitDialog.getWindow();              //获得这个dialog的window
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
    //    lp.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        lp.alpha = 1f;
        dialogWindow.setAttributes(lp);
        profitDialog.show();
    }

    @Override
    public void initData() {

    }
}
