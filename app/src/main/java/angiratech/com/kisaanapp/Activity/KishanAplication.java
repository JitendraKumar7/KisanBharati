package angiratech.com.kisaanapp.Activity;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by SONY on 29-04-2017.
 */

public class KishanAplication extends Application {
    //Static value Holders.
    private static KishanAplication mInstance;
    private RequestQueue mRequestQueue;
    public static Context context;
    public static final String TAG = KishanAplication.class.getSimpleName();

    public static synchronized KishanAplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());

       // ActiveAndroid.initialize(this);
        context = getApplicationContext();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
        printHashKey();

        // Univarsal Image Loader
        DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                //.resetViewBeforeLoading(true)
                //.showImageOnLoading(R.drawable.logo)
                .build();
        ImageLoaderConfiguration imgConfig = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(imgOptions)
                //.imageDownloader(new BaseImageDownloader(context, 5 * 3000, 50 * 1000))
                .imageDecoder(new BaseImageDecoder(true))
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                //.writeDebugLogs()
                //.threadPriority(ImageLoaderConfiguration.Builder.DEFAULT_THREAD_PRIORITY)
                .build();
        ImageLoader.getInstance().init(imgConfig);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }





    public static boolean getCurrentActivity() {
        ActivityManager am = (ActivityManager) mInstance.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        String packageName = componentInfo.getPackageName();
        String className = componentInfo.getClassName();
        Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
//        if (className.equalsIgnoreCase("com.bhartiagri.babsapp.AddCommodityActivity")) {
//            return true;
//        } else if (className.equalsIgnoreCase("com.webappmate.utilityinternet.AppDtlActivity")) {
//            return true;
//        } else if (className.equalsIgnoreCase("com.webappmate.utilityinternet.HomeActivity")) {
//            return true;
//        } else if (className.equalsIgnoreCase("com.webappmate.utilityinternet.StatePermissionActivity")) {
//            return true;
//        } else if (className.equalsIgnoreCase("com.webappmate.utilityinternet.SplashActivity")) {
//            return true;
//        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.bhartiagri.babsapp", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
}
