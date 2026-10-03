package com.github.farhaanaliii.wget;

import android.app.Application;

public class App extends Application {
    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        CrashHandler.init(this);
    }
    public static App getApp() {
        return app;
    }

}
