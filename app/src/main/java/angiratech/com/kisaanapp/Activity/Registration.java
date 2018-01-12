package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.AppConstants;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.CodeUtils;
import angiratech.com.kisaanapp.Utility.MyToast;

/**
 * Created by SONY on 30-04-2017.
 */

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private MySession mySession;
    private MyDialog myDialog;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private LinearLayout registerLayout;
    private EditText input_name, input_phone;
    private String key;
    private JSONObject jObj = null;
    private String newFireBaseToken;
   private Button btn_register;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        setupToolbar();
        CodeUtils.changestatusBarColor(Registration.this);
        mySession = new MySession(Registration.this);
        myDialog = new MyDialog(Registration.this);


    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarName);
            toolbarTitle.setText("Registration");
            toolbarTitle.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    private void init() {
        input_name = (EditText) findViewById(R.id.input_name);
        input_phone = (EditText) findViewById(R.id.input_phone);
        registerLayout = (LinearLayout) findViewById(R.id.registerLayout);
        btn_register = (AppCompatButton) findViewById(R.id.btn_register);
      btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if (CheckInternet.isConnectedNetwork(this)) {
                    validation();
                }
                break;

            default:
                break;
        }
    }
    private boolean validation() {
        if (input_name.getText().toString().trim().equals("")) {
            input_name.setError("Please Enter Name .");
            input_name.requestFocus();
            return false;
        } else if (input_phone.getText().toString().trim().equals("")) {
            input_phone.setError("Please Enter your Mobile.");
            input_phone.requestFocus();
            return false;

        } else {
           myDialog.ShowProgressDialogue();
            new Webservice().RegisterRequest(key, input_name.getText().toString(), input_phone.getText().toString(), new JsonHttpResponseHandler() {
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
                                mySession.SaveStringPref(MySession.Email, userDetailsObject.optString(AppConstants.KEY_EMAIL));
                                mySession.SaveStringPref(MySession.Mobile, userDetailsObject.optString(AppConstants.KEY_MOBILE));
                                mySession.getActiveInstance(Registration.this).setLogInStatus(true);
                                Intent intent = new Intent(Registration.this, Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                Registration.this.finish();

                            } else {
                                MyToast.Lmsg(Registration.this, response.toString());

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("RESPONSE", e.toString());

                        }
                    }


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    // TODO Auto-generated method stub
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    myDialog.CancelProgressDialog();

                }

            });


        }
        return true;

    }





}
