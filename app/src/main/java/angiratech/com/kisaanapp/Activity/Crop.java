package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.CropListAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.CropListModel;
import angiratech.com.kisaanapp.RecylerView.OnClickListener;
import angiratech.com.kisaanapp.RecylerView.RecyclerTouchListener;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by NZT-59 on 5/5/2017.
 */

public class Crop extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private GridLayoutManager mLayoutManager;

    CropListAdapter recyclerViewAdapter;
    private ArrayList<CropListModel> crop_list = new ArrayList<>();
    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        init();
        toolbar();
        GetCropListRequest();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(Crop.this, recyclerView, new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CropListModel cropListModel = crop_list.get(position);
                        Intent cropTabIntent = new Intent(getApplicationContext(), cropTabHolder.class);
                        cropTabIntent.putExtra("data", cropListModel);
                        startActivity(cropTabIntent);
                        //finish();
                    }


                    @Override
                    public void onLongClick(View view, int position) {
                    }
                })
        );


    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText(getResources().getString(R.string.title_hn));
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

    private void init() {
        myDialog = new MyDialog(Crop.this);
        recyclerView = (RecyclerView) findViewById(R.id.lv_category);


    }

    private void GetCropListRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            new Webservice().GetcropList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray cropListJsonArray = response.optJSONArray("subcategory");
                                if (cropListJsonArray.length() > 0) {
                                    for (int i = 0; i < cropListJsonArray.length(); i++) {
                                        JSONObject CropDataObject = cropListJsonArray.optJSONObject(i);
                                        CropListModel listModel = new CropListModel();
                                        listModel.setId(CropDataObject.optString("id"));
                                        listModel.setName(CropDataObject.optString("name"));
                                        listModel.setImage(CropDataObject.optString("image"));
                                        listModel.setInfo1(CropDataObject.optString("info1"));
                                        listModel.setInfo2(CropDataObject.optString("info2"));
                                        listModel.setInfo3(CropDataObject.optString("info3"));
                                        listModel.setInfo4(CropDataObject.optString("info4"));
                                        listModel.setInfo5(CropDataObject.optString("info5"));

                                        crop_list.add(listModel);
                                    }
                                    recyclerViewAdapter = new CropListAdapter(Crop.this, crop_list);
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


    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),
                "You have clicked " + ((TextView) v).getText(),
                Toast.LENGTH_LONG).show();


    }
}
