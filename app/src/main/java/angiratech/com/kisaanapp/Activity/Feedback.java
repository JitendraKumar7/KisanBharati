package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyToast;

/**
 * Created by Sony on 06-11-2017.
 */


public class Feedback extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btn_feedback;
    private EditText edt_feedback;
    private Toolbar toolbar;
    private TextView toolbarName;
    private TextView toolbarTitle;
    private MySession mySession;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private String user_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.suggestion);
        mySession = new MySession(Feedback.this);
        myDialog = new MyDialog(Feedback.this);
        init();
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText(getResources().getString(R.string.feedback));
            img_back = (ImageView) toolbar.findViewById(R.id.img_back);
            img_back.setVisibility(View.VISIBLE);
            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            toolbarImg = (ImageView) toolbar.findViewById(R.id.toolbarImg);
            toolbarImg.setVisibility(View.VISIBLE);
            toolbarImg.setOnClickListener(this);
            setSupportActionBar(toolbar);
        }
    }

    private void init() {
        btn_feedback = (AppCompatButton) findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(this);
        edt_feedback = (EditText) findViewById(R.id.edt_feedback);
        user_id = mySession.getUserId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_feedback:
                if (CheckInternet.isConnectedNetwork(this)) {
                    validation();
                }
                break;

            case R.id.toolbarImg:
                Intent notific = new Intent(Feedback.this, Notifications.class);
                startActivity(notific);
                break;


            default:
                break;
        }
    }

    private boolean validation() {
        if (edt_feedback.getText().toString().trim().equals("")) {
            edt_feedback.setError("Please Enter Feedback");
            edt_feedback.requestFocus();
            return false;
        } else {
            myDialog.ShowProgressDialogue();
            new Webservice().FeedbackRequest(user_id, edt_feedback.getText().toString(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1")) {
                                String mesg = response.optString("statusmessage");
                                Log.e("RESPONSE", mesg);
                                edt_feedback.setText("");
                                MyToast.Lmsg(Feedback.this, mesg);

                                Intent intent = new Intent(Feedback.this, Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                Feedback.this.finish();
                            } else {
                                MyToast.Lmsg(Feedback.this, response.toString());
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
            return true;
        }
    }
}