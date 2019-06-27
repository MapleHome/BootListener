package com.maple.bootlistener;

import android.app.Application;

/**
 * @author maple
 * @time 2019-06-24
 */
public class ListenerApp extends Application {

    private static ListenerApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static ListenerApp getApp() {
        return app;
    }

}
