package com.maple.bootlistener.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.maple.bootlistener.MainActivity;
import com.maple.bootlistener.utils.SPUtils;


/**
 * 开机广播 监听者
 *
 * @author maple
 * @time 2019-06-24
 */
public class MyReceiver extends BroadcastReceiver {
    public static final String BOOT_KEY = "boot log";

    public MyReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SPUtils spUtils = new SPUtils();
        String msg = spUtils.getString(BOOT_KEY, "广播日志：\n");
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            msg += "设备启动完成 ：" + System.currentTimeMillis() + "\n";
            toMainPage(context);
        }
        spUtils.put(BOOT_KEY, msg);
    }

    private void toMainPage(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}