package com.chinafocus.mytestanimation.global;

import android.app.Application;
import android.content.Context;

public class MyTestApplication extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

//        Global.serializableFileDir = getFilesDir().getAbsolutePath() + File.separator + "Ser";
//        Global.serializableFileDirNotDelete = getFilesDir().getAbsolutePath() + File.separator + "SerNotDelete";

//        LeakCanary.install(this);

        sContext = getApplicationContext();

    }

}
