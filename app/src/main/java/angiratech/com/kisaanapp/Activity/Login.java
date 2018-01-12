package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Interface.ResponceLisnter;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.AppConstants;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyLog;
import angiratech.com.kisaanapp.Utility.MyToast;
import angiratech.com.kisaanapp.Volley.VollyRequests;

/**
 * Created by SONY on 29-04-2017.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {
    protected TextView toolbarName;
    protected Toolbar toolbar;
    protected EditText etInputPhoneNo;
    private String deviceId = "", imeiNum = "";
    private MySession mySession;
    private MyDialog myDialog;
    private VollyRequests vollyRequests = new VollyRequests();
    private Button btnLogin, btn_registration;
    android.app.ProgressDialog ProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setupToolbar();


    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
            toolbarName.setText("Sign In");
            toolbarName.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Sign In");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    private void initView() {
        mySession = new MySession(Login.this);
        myDialog = new MyDialog(Login.this);
        toolbarName = (TextView) findViewById(R.id.toolbarName);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etInputPhoneNo = (EditText) findViewById(R.id.et_input_phoneNo);
        btn_registration = (Button) findViewById(R.id.btn_registration);
        btn_registration.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(Login.this);

        //   deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        //  TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //  imeiNum = tm.getDeviceId();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:
                if (validation()) {
                    //showPicDialog();
                    userLoginRequest();

                }
                break;

            case R.id.btn_registration:
                Intent registerIntent = new Intent(Login.this, Registration.class);
                startActivity(registerIntent);
                finish();
                break;

            default:
                break;
        }

    }

    private void userLoginRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            new Webservice().userLoginRequest(etInputPhoneNo.getText().toString(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1")) {
                                JSONObject userDetailsObject = response.getJSONObject("user");
                                mySession.SaveStringPref(MySession.UserId, userDetailsObject.optString(AppConstants.KEY_USERID));
                                mySession.SaveStringPref(MySession.Name, userDetailsObject.optString(AppConstants.KEY_NAME));
                                mySession.SaveStringPref(MySession.Mobile, userDetailsObject.optString(AppConstants.KEY_MOBILE));
                                mySession.getActiveInstance(Login.this).setLogInStatus(true);
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                Login.this.finish();

                            } else if (response.optString("statuscode").equalsIgnoreCase("2")) {
                                String msg = response.optString("statusmessage");
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    myDialog.CancelProgressDialog();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Please check Your Internet", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validation() {
        if (etInputPhoneNo.getText().toString().trim().equals("")) {
            etInputPhoneNo.setError("Please Enter your Mobile No.");
            etInputPhoneNo.requestFocus();
            return false;
        } else if (!isValidMail(etInputPhoneNo.getText().toString().trim())) {
            etInputPhoneNo.setError("Please Enter Valid Mobile No.");
            etInputPhoneNo.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isValidMail(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    ResponceLisnter responseListner = new ResponceLisnter() {
        @Override
        public void getResponce(String responce, String Tag) {
            if (Tag.equalsIgnoreCase(AppConstants.TAG_Login)) {
                userLoginResponce(responce);
            }
        }

        @Override
        public void getResponceError(String errorStr, String Tag) {
            myDialog.CancelProgressDialog();
            MyToast.Lmsg(getApplicationContext(), errorStr);
            MyLog.ShowELog("Register Response", errorStr);

        }
    };

    private void userLoginResponce(String responce) {
        myDialog.CancelProgressDialog();
        if (responce != null) {
            myDialog.CancelProgressDialog();
            Log.e("Login Response", responce);

            try {
                JSONObject jsonobject = new JSONObject(responce);
                String status = jsonobject.optString(AppConstants.KEY_CODE);
                String message = jsonobject.optString(AppConstants.KEY_MSG);
                if (status.equalsIgnoreCase(AppConstants.KEY_SUCCESS_1)) {
                    JSONObject userDetailsObject = jsonobject.getJSONObject("user_details");
                    mySession.SaveStringPref(MySession.UserId, userDetailsObject.optString(AppConstants.KEY_USERID));
                    mySession.SaveStringPref(MySession.Name, userDetailsObject.optString(AppConstants.KEY_NAME));
                    mySession.SaveStringPref(MySession.Address, userDetailsObject.optString(AppConstants.KEY_ADDRESS));
                    mySession.SaveStringPref(MySession.City, userDetailsObject.optString(AppConstants.KEY_CITY));
                    mySession.SaveStringPref(MySession.District, userDetailsObject.optString(AppConstants.KEY_DISTRICT));
                    mySession.SaveStringPref(MySession.State, userDetailsObject.optString(AppConstants.KEY_STATE));
                    mySession.SaveStringPref(MySession.Email, userDetailsObject.optString(AppConstants.KEY_EMAIL));
                    mySession.SaveStringPref(MySession.Mobile, userDetailsObject.optString(AppConstants.KEY_MOBILE));
                    mySession.getActiveInstance(Login.this).setLogInStatus(true);
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
