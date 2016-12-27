package com.xss.gxq.openApi;

import android.content.Context;
import android.content.Intent;

import com.xss.gxq.ui.home.GuideTestActivity;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 18:06
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class GxqSDK {
    private static GxqSDK sdk;
    private static Context ctx;

    private GxqSDK() {

    }

    public static GxqSDK getInstance(Context ctx) {
        ctx = ctx;
        if (sdk == null) {
            sdk = new GxqSDK();
        }
        return sdk;
    }

    public void startOpenApi(boolean isFirstEnter) {
        Intent intent = null;

            intent = new Intent(ctx, GuideTestActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }
}
