package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.HelpCenterAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.HelpCenterModel;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by SONY on 06-05-2017.
 */

public class HelpCenter extends AppCompatActivity {

    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private ArrayList<HelpCenterModel> help_list_model=new ArrayList<>();
    private RecyclerView recyclerView;
    private HelpCenterAdapter adapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        toolbar();
        myDialog = new MyDialog(HelpCenter.this);
        recyclerView=(RecyclerView)findViewById(R.id.recylerviewhelp);

        mLayoutManager= new LinearLayoutManager(HelpCenter.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        getHelpInformation();


    }

    private void getHelpInformation() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            new Webservice().HelpCenterRequest(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray HelpCenterJsonArray = response.optJSONArray("helpcenter");
                                if (HelpCenterJsonArray.length() > 0) {
                                    for (int i = 0; i < HelpCenterJsonArray.length(); i++) {
                                        JSONObject HelpDataObject = HelpCenterJsonArray.optJSONObject(i);
                                        HelpCenterModel listModel = new HelpCenterModel();
                                        listModel.setId(HelpDataObject.optString("id"));
                                        listModel.setHeading(HelpDataObject.optString("heading"));
                                        listModel.setDescrption(HelpDataObject.optString("description"));
                                        help_list_model.add(listModel);
                                    }
                                    adapter = new HelpCenterAdapter(help_list_model, HelpCenter.this);
                                    recyclerView.setAdapter(adapter);
                                }
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

        }


}

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("किसान सहायता केंद्र");
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

            setSupportActionBar(toolbar);
        }
    }

}
