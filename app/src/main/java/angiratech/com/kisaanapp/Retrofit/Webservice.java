package angiratech.com.kisaanapp.Retrofit;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import angiratech.com.kisaanapp.Utility.AppConstants;

/**
 * Created by NZT-59 on 5/18/2017.
 */

public class Webservice {

    public static final String BASE_URL = "http://www.angiratech.com/kisaan/api.php?";

    private static AsyncHttpClient client = new AsyncHttpClient();
    public static final String BASE_URL_for_Weather = "http://api.openweathermap.org/data/2.5/forecast/daily";

    public static void RegisterRequest(String key, String name, String mobile, JsonHttpResponseHandler httpResponseHandler) {

        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONREGISTER);
        params.put(AppConstants.KEY_NAME, name);
        params.put(AppConstants.KEY_MOBILE, mobile);
        // params.put("device_id", "2726f75cfd253778");

        Webservice.get(BASE_URL, params, httpResponseHandler);
    }


    public static void get(String apiNameUrl, RequestParams params, JsonHttpResponseHandler jsonHttpResponseHandler) {
        String finalUrl = BASE_URL;
        Log.e("Get url...", "" + finalUrl);
        client.get(finalUrl, params, jsonHttpResponseHandler);
    }

    public static void getLogin(String apiNameUrl, RequestParams params, JsonHttpResponseHandler jsonHttpResponseHandler) {
        String finalUrl = AppConstants.BASE_URL_KISAN;
        Log.e("Get url...", "" + finalUrl);
        client.get(finalUrl, params, jsonHttpResponseHandler);
    }

    public static void getCrop(String apiNameUrl, RequestParams params, JsonHttpResponseHandler jsonHttpResponseHandler) {
        String finalUrl = AppConstants.BASE_URL_CROP;
        Log.e("Get url...", "" + finalUrl);
        client.get(finalUrl, params, jsonHttpResponseHandler);
    }

    public static void getWeather(String lat, String lon, JsonHttpResponseHandler jsonHttpResponseHandler) {
        String finalUrl = BASE_URL_for_Weather;
        RequestParams requestParams = new RequestParams();
        requestParams.put("id", "524901");
        requestParams.put("lat", lat);
        requestParams.put("lon", lon);
        requestParams.put("cnt", "10");
        //	requestParams.put("appid", "2de143494c0b295cca9337e1e96b00e0");
        requestParams.put("appid", "37fee3e4b21b4eb76faf18820871c779");

        Log.e("Post url...", "" + finalUrl);
        client.get(finalUrl, requestParams, jsonHttpResponseHandler);
    }

    public static void userLoginRequest(String mobile, JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONLOGIN);
        params.put(AppConstants.KEY_MOBILE, mobile);
        // params.put("device_id", "2726f75cfd253778");
        Webservice.getLogin(AppConstants.BASE_URL_KISAN, params, httpResponseHandler);
    }


    public static void GetcropList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONCROPLIST);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetseedList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONSEEDLIST);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetYantraList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_YANTRA_LIST);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetSchemeList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_SCHEME_LIST);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetNewsList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONNEWS);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);


    }

    public static void GetDealerList(String UserName, String mobile, String Firm, String Address, String Scope, String DistrictId, JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_DEALER_REGISTER);
        params.put("name", UserName);
        params.put("mobile", mobile);
        params.put("firm_name", Firm);
        params.put("address", Address);
        params.put("district", DistrictId);

        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetStateList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_DISTRICT_LIST);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void HelpCenterRequest(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_HELP_LIST);

        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void MarketRequest(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_MARKET);

        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetDetailList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_DEALER_LIST);

        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetBlogList(JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_BLOG_LIST);

        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void GetComment(String user_id, String id, String comment, JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEYCOMMENT);
        params.put("uid", user_id);
        params.put("blog_id", id);
        params.put("comment", comment);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);
    }

    public static void FeedbackRequest(String user_id,String Feedback,JsonHttpResponseHandler httpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put(AppConstants.KEY_ACTION, AppConstants.KEY_FEEDBACK);
        params.put(AppConstants.KEY_UID,user_id);
        params.put(AppConstants.KEY_FEED, Feedback);
        Webservice.getCrop(AppConstants.BASE_URL_CROP, params, httpResponseHandler);


    }







}
