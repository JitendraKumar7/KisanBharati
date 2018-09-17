package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.SchemeListAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.SchemeListModel;
import angiratech.com.kisaanapp.RecylerView.DividerItemDecoration;
import angiratech.com.kisaanapp.RecylerView.OnClickListener;
import angiratech.com.kisaanapp.RecylerView.RecyclerTouchListener;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by NZT-59 on 5/11/2017.
 */

public class Scheme extends AppCompatActivity implements View.OnClickListener {
    private MyDialog myDialog;
    private LinearLayoutManager mLayoutManager;

    SchemeListAdapter recyclerViewAdapter;
    private ArrayList<SchemeListModel> scheme_list = new ArrayList<>();
    private RecyclerView recyclerView;


    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_scheme);
        toolbar();
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recylerviewseed);
        myDialog = new MyDialog(Scheme.this);

        mLayoutManager = new LinearLayoutManager(Scheme.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Scheme.this, DividerItemDecoration.VERTICAL_LIST));
        GetschemeListRequest();

        // mresources = getResources().getStringArray(R.array.crop);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(Scheme.this, recyclerView, new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        SchemeListModel schemeListModel = scheme_list.get(position);
                        Intent cropTabIntent = new Intent(getApplicationContext(), SchemeInformation.class);
                        cropTabIntent.putExtra("SchemeData", schemeListModel);


                        startActivity(cropTabIntent);


                        //finish();
                    }


                    @Override
                    public void onLongClick(View view, int position) {
                    }
                })
        );

    }

    private void GetschemeListRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            new Webservice().GetSchemeList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("Scheme List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray SchemeListJsonArray = response.optJSONArray("yojana");
                                if (SchemeListJsonArray.length() > 0) {
                                    for (int i = 0; i < SchemeListJsonArray.length(); i++) {
                                        JSONObject CropDataObject = SchemeListJsonArray.optJSONObject(i);
                                        SchemeListModel listModel = new SchemeListModel();
                                        listModel.setId(CropDataObject.optString("id"));
                                        listModel.setName(CropDataObject.optString("heading"));
                                        listModel.setDescription(CropDataObject.optString("description"));
                                        listModel.setImage(CropDataObject.optString("image"));
                                        scheme_list.add(listModel);
                                    }
                                    recyclerViewAdapter = new SchemeListAdapter(scheme_list, Scheme.this);
                                    recyclerView.setAdapter(recyclerViewAdapter);
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
            toolbarName.setText("सरकारी योजनाए");
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
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbarImg) {
            Intent notific = new Intent(Scheme.this, Notifications.class);
            startActivity(notific);
        }
    }
}
