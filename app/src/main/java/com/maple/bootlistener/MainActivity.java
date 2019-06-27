package com.maple.bootlistener;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import com.maple.bootlistener.receiver.MyReceiver;
import com.maple.bootlistener.utils.PackageUtils;
import com.maple.bootlistener.utils.SPUtils;

public class MainActivity extends AppCompatActivity {
    public static final String START_TAG = "isStart";
    // String startAppID = "com.example.androidx.viewpager2";
    String startAppID = "com.dangbeimarket";
    TextView tvInfo;
    CheckBox cbStartApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tv_info);
        cbStartApp = findViewById(R.id.cb_start_app);

        clickRefresh();
        // start other app
        boolean isStart = new SPUtils().getBoolean(START_TAG, true);
        if (isStart) {
            doStartAppWithPackageName(startAppID);
        }

        cbStartApp.setChecked(isStart);
        cbStartApp.setOnCheckedChangeListener((buttonView, isChecked) ->
                new SPUtils().put(START_TAG, isChecked)
        );
    }

    public void clickRefresh() {
        String info = "系统信息: \n"
                + "Model: " + android.os.Build.MODEL + ",\n"
                + "SDK: " + android.os.Build.VERSION.SDK + ",\n"
                + "版本：" + android.os.Build.VERSION.RELEASE + "\n";
        String msg = new SPUtils().getString(MyReceiver.BOOT_KEY, "kai");
        tvInfo.setText(info + msg);
    }

    private void doStartAppWithPackageName(String packageName) {
        String className = PackageUtils.getStartActivityName(this, packageName);
        if (!TextUtils.isEmpty(className)) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(packageName, className));
            startActivity(intent);
//            finish();
        }
    }
}
