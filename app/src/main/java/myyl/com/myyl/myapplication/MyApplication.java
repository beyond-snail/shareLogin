package myyl.com.myyl.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;

import myyl.com.myyl.utils.SharedUtil;


public class MyApplication extends Application {

    public static final String TAG = "MyApplication";

    private static MyApplication mInstance = null;
    public static SharedUtil shareUtil;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.init(this);
        shareUtil = new SharedUtil(this);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }

    }

    public static MyApplication getmInstance() {
        return mInstance;
    }

}
