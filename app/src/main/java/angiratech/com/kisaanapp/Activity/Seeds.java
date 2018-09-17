package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import angiratech.com.kisaanapp.Adapter.SeedListAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.SeedListModel;
import angiratech.com.kisaanapp.RecylerView.OnClickListener;
import angiratech.com.kisaanapp.RecylerView.RecyclerTouchListener;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by NZT-59 on 5/11/2017.
 */

public class Seeds extends AppCompatActivity implements View.OnClickListener{
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private ArrayList<SeedListModel> seed_list_model=new ArrayList<>();
    private RecyclerView recyclerView;
    private SeedListAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_seeds);
        intoolbar();
        intit();
    }

    private void intit() {
        myDialog = new MyDialog(Seeds.this);
        recyclerView = (RecyclerView) findViewById(R.id.recylerviewseeds);
        mLayoutManager = new LinearLayoutManager(Seeds.this);
        GetSeedListRequest();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        // mresources = getResources().getStringArray(R.array.crop);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(Seeds.this, recyclerView, new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        SeedListModel seedListModel=seed_list_model.get(position);
                        Intent cropTabIntent = new Intent(getApplicationContext(), SeedListInformation.class);
                        cropTabIntent.putExtra("Seeddata",seedListModel);
                        startActivity(cropTabIntent);
                    }


                    @Override
                    public void onLongClick(View view, int position) {
                    }
                })
        );



    }

    private void GetSeedListRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            new Webservice().GetseedList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray seedListJsonArray = response.optJSONArray("seedlist");
                                if (seedListJsonArray.length() > 0) {
                                    for (int i = 0; i < seedListJsonArray.length(); i++) {
                                        JSONObject SeedDataObject = seedListJsonArray.optJSONObject(i);
                                        SeedListModel listModel = new SeedListModel();
                                        listModel.setId(SeedDataObject.optString("id"));
                                        listModel.setName(SeedDataObject.optString("heading"));
                                        listModel.setImage(SeedDataObject.optString("image"));
                                        listModel.setInfomation(SeedDataObject.optString("description"));

                                        seed_list_model.add(listModel);
                                    }
                                    adapter = new SeedListAdapter(seed_list_model, Seeds.this);
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
                }
            });

        }

    }

    private void intoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("बीज");
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
            Intent notific = new Intent(Seeds.this, Notifications.class);
            startActivity(notific);
        }
    }
}
