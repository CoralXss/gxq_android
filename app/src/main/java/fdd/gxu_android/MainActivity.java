package fdd.gxu_android;

import android.app.Activity;
import android.os.Bundle;

import com.xss.gxq.openApi.GxqSDK;

public class MainActivity extends Activity {
    private GxqSDK sdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdk = GxqSDK.getInstance(this);
        sdk.startOpenApi(false);

    }


}
