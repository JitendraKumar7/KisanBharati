package angiratech.com.kisaanapp.Session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SONY on 29-04-2017.
 */

public class MySession {
    private SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static MySession myPreferences;
    public static final String PrefName = "KisaanApp";
    public static String UserId = "UserId";
    public static String CategoryId = "CategoryId";
    public static String Name = "Name";
    public static String Email = "Email";
    public static String Mobile = "Mobile";
    public static String GcmId = "GcmId";
    public static String FireBaseToken = "firetoken";
    public static String ImeiNo = "ImeiNo";
    public static String ProfImage = "ProfImage";
    public static String OTPVerification = "OtpVerification";
    public static String Address = "Address";
    public static String City = "city";
    public static String District = "district";
    public static String State = "state";
    public static String FirstRun = "firstRun";
    public static String IS_LOGED_IN = "islogin";
    public static String MAX_TEMP = "maxtemp";
    public static String MIN_TEMP = "mintemp";
    public static String CITY = "city";

    //Banner Offline Tags
    public static String BannerId = "bannerid";
    public static String BannerImage = "bannerimage";
    public static String Bannerweb = "bannerweb";

    //News offinetags
    public static String user_id = "user_id";
    public static String news_id = "news_id";
    public static String heading = "heading";
    public static String Newsimage = "image";
    public static String description = "description";
    public static String actual_news_time = "actual_news_time";
    public static String category_id = "category_id";
    public static String category_name_en = "category_name_en";
    public static String category_name_hi = "category_name_hi";
    public static String read_status = "read_status";
    public static String created_at = "created_at";


    public static String registerMilisecond = "registerMiliseond";
    public static String finishRegisterMilisecond = "finishRegistermiliseond";
    public static String Banner = "bannercategory";

    public MySession(Context context) {
        pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public void SaveStringPref(String Key, String Value) {
        editor = pref.edit();
        editor.putString(Key, Value);
        editor.commit();
    }

    public void logOutUser() {
        editor = pref.edit();
        editor.clear();
        editor.commit();
    }


    public static MySession getActiveInstance(Context ctxt) {
        if (myPreferences == null) {
            myPreferences = new MySession(ctxt);
            return myPreferences;
        } else {
            return myPreferences;
        }
    }

    /*********** get boolean shared preferences *****************/
    public static boolean getbooleanPreferences(String Key, boolean Value) {
        boolean value = getbooleanPreferences(MySession.FirstRun, false);
        return value;
    }

    public void setLogInStatus(boolean status) {
        editor = pref.edit();
        editor.putBoolean(IS_LOGED_IN, status);
        editor.commit();
    }

    public boolean getLogInStatus() {
        return pref.getBoolean(IS_LOGED_IN, false);
    }


//    public static int getIntegerPreferences(String con, int key) {
//       SharedPreferences pref = con.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
//        int value = pref.getInt(key, 0);
//        return value;
//    }


    public void SaveIntPref(String Key, int Value) {
        editor = pref.edit();
        editor.putInt(Key, Value);
        editor.commit();
    }

    public void SaveFloatPref(String Key, float Value) {
        editor = pref.edit();
        editor.putFloat(Key, Value);
        editor.commit();
    }

    public void SaveBooleanPref(String Key, boolean Value) {
        editor = pref.edit();
        editor.putBoolean(Key, Value);
        editor.commit();
    }

    public void SaveLongPref(String Key, long Value) {
        editor = pref.edit();
        editor.putLong(Key, Value);
        editor.commit();
    }


    public long getRegisterDateMilisecond() {
        return pref.getLong(registerMilisecond, 0);
    }

    public long getFinishRegisterDateMilisecond() {
        return pref.getLong(registerMilisecond, 0);
    }

    public String getUserId() {
        return pref.getString(UserId, "");
    }

    public String getMaxTemp() {
        return pref.getString(MAX_TEMP, "");
    }

    public String getMinTemp() {
        return pref.getString(MIN_TEMP, "");
    }

    public String getCity() {
        return pref.getString(CITY, "");
    }

    public String getName() {
        return pref.getString(Name, "");
    }

    public String getEmail() {
        return pref.getString(Email, "");
    }

    public String getMobile() {
        return pref.getString(Mobile, "");
    }

    public String getGcmId() {
        return pref.getString(GcmId, "");
    }

    public String getImeino() {
        return pref.getString(ImeiNo, "ABC");
    }

    public String getProfImage() {
        return pref.getString(ProfImage, "");
    }

    public boolean getOTPVerification() {
        return pref.getBoolean(OTPVerification, false);
    }

    public String getFireBaseToken() {
        return pref.getString(FireBaseToken, "");
    }
}
