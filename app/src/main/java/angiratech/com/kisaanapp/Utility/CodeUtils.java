package angiratech.com.kisaanapp.Utility;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;


import com.angiratech.kisaanapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CodeUtils {

    /**
     * This method checks the internet connection in the device and returns true/false
     *
     * @param $context
     * @return boolean
     */
    public static boolean isConnectingToInternet(Context $context) {
        ConnectivityManager connectivity = (ConnectivityManager) $context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    /*public static ProgressDialog generateWaitingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        return progressDialog;
    }*/

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void changestatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * This method converts the byte array received from the webservices into String.
     *
     * @param byteArray byte Array received from webservice
     * @return String
     */
    public static String convetResponse(byte[] byteArray) {
        StringBuilder builder = new StringBuilder();
        if (byteArray != null) {
            for (int i = 0; i < byteArray.length; i++) {
                builder.append((char) byteArray[i]);
            }
            return builder.toString();
        } else {
            return null;
        }
    }

    public static String getDeviceuniqueID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        return deviceId;
    }


    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    public static String timstampToDate(String unix_timestamp) throws ParseException {
        long timestamp = Long.parseLong(unix_timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        String date = sdf.format(timestamp);
        return date.toString();
    }

//    public static void checkpermissionStatus(Context context) {
//        if (PermissionHelper.areExplicitPermissionsRequired()) {
//            if (!PermissionHelper.isAlreadyAllPermissionsGranted(context)) {
//                PermissionHelper.sh(context, "Grant Permission Access");
//            }
//        }
//    }

    public static String timestampToTimeAMPM(String unix_timestamp) {
        long timestamp = Long.parseLong(unix_timestamp) * 1000;
        Date date = new Date(timestamp);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        String hr = "";
        if (String.valueOf(hour).length() == 1) {
            hr = "0" + hour;
        } else {
            hr = "" + hour;
        }
        String min = "";
        if (String.valueOf(minute).length() == 1) {
            min = "0" + minute;
        } else {
            min = "" + minute;
        }
        String time = hr + ":" + min + " " + (cal.get(Calendar.AM_PM) == Calendar.SUNDAY ? "AM" : "PM");
        return time;
    }

    public static enum SERVICE_STATUS {
        SUCCESS,
        FAILED,
        PHONE_NOT_VERIFIED,
        INVALID_TOKEN,
        LOGOUT,
        NULL
    }

    /**
     * This method returns the message returned by the service
     *
     * @param response
     * @return message
     */
    public static String getServiceMessage(JSONObject response) {
        try {
            return response.getString(AppConstants.KEY_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static SERVICE_STATUS serviceExecutionStatus(String response) throws JSONException {
        JSONObject obj = new JSONObject(response);
        if (obj.getString(AppConstants.KEY_CODE).equals(AppConstants.ERROR_CODE_SUCCESS)) {
            return SERVICE_STATUS.SUCCESS;
        } else if (obj.getString(AppConstants.KEY_CODE).equals(AppConstants.ERROR_CODE_FAILURE)) {
            return SERVICE_STATUS.FAILED;
        } else if (obj.getString(AppConstants.KEY_CODE).equals(AppConstants.ERROR_CODE_OTPNOTVERIFIED)) {
            return SERVICE_STATUS.PHONE_NOT_VERIFIED;
        } else if (obj.getString(AppConstants.KEY_CODE).equals(AppConstants.ERROR_CODE_TOKEN_MISMATCH)) {
            return SERVICE_STATUS.INVALID_TOKEN;
        } else if (obj.getString(AppConstants.KEY_CODE).equals(AppConstants.ERROR_CODE_LOGOUT)) {
            return SERVICE_STATUS.LOGOUT;
        } else if (response.equals("")) {
            return SERVICE_STATUS.NULL;
        } else {
            return SERVICE_STATUS.NULL;
        }
    }

}
