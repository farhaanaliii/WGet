package com.github.farhanaliofficial.wget.app;

import com.github.farhanaliofficial.wget.handler.CrashHandler;
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
