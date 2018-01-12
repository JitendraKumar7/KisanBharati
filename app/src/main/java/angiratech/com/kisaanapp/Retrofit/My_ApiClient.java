package angiratech.com.kisaanapp.Retrofit;

import org.json.JSONObject;

import angiratech.com.kisaanapp.Utility.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NZT-59 on 5/18/2017.
 */

public interface My_ApiClient {

    @GET("api.php/")
    Call<JSONObject> User_Login(@Query(AppConstants.KEY_ACTION) String param1, @Query(AppConstants.KEY_NAME) String param2, @Query(AppConstants.KEY_MOBILE) String param3);


}
