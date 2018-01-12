package angiratech.com.kisaanapp.Retrofit;

import android.content.Context;

import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Session.MySession;

/**
 * Created by NZT-59 on 5/18/2017.
 */

public class RestRetrofit {
    private MyDialog myDialog;
    Context ctx;
    private MySession mySession;

    public RestRetrofit(Context context) {
        this.ctx = context;

    }
}

//    public void UserRegistration(String name, String mobile) {
//        if (CheckInternet.isNetwork(ctx)) {
//          // String params1=AppConstants.KEY_ACTIONREGISTER;
//
//
//
//
//
//
//
//         //   myDialog.ShowProgressDialogue();
////            Map<String, String> params = new HashMap<String, String>();
////            params.put(AppConstants.KEY_ACTION, AppConstants.KEY_ACTIONREGISTER);
////            params.put(AppConstants.KEY_NAME, name);
////            params.put(AppConstants.KEY_MOBILE, mobile);
////            My_ApiClient apiClient = ApiClient.getClient().create(My_ApiClient.class);
////
////            Call<JSONObject> callback = apiClient.User_Login(params1,name,mobile);
////            callback.enqueue(new Callback<JSONObject>() {
////                @Override
////                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
////                    if(response!=null) {
////                    //    myDialog.CancelProgressDialog();
////                        Log.e("Login Response", response.toString());
////
////
////                        try {
////                            JSONObject jsonobject = new JSONObject(response.body().toString());
////                            String status = jsonobject.optString(AppConstants.KEY_CODE);
////                            String message = jsonobject.optString(AppConstants.KEY_MSG);
////                            if(status.equalsIgnoreCase(AppConstants.KEY_SUCCESS_1)){
////                                JSONObject userDetailsObject = jsonobject.getJSONObject("user");
////                                mySession.SaveStringPref(MySession.UserId, userDetailsObject.optString(AppConstants.KEY_USERID));
////                                mySession.SaveStringPref(MySession.Name, userDetailsObject.optString(AppConstants.KEY_NAME));
////                                mySession.SaveStringPref(MySession.Mobile, userDetailsObject.optString(AppConstants.KEY_MOBILE));
////
////                            }
////
////
////
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////                    }
////
////                }
////
////                @Override
////                public void onFailure(Call<JSONObject> call, Throwable t) {
////
////                }
////            });
////
////
////        }
//
//
//    }
//}
