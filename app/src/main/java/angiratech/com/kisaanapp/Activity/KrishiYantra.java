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
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.KrishiYantraListAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.YantraModel;
import angiratech.com.kisaanapp.RecylerView.OnClickListener;
import angiratech.com.kisaanapp.RecylerView.RecyclerTouchListener;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by SONY on 06-05-2017.
 */

public class KrishiYantra extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    KrishiYantraListAdapter recyclerViewAdapter;
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private ArrayList<YantraModel> yantra_list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krishi_yantra);
        init();
        toolbar();

    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("कृषि यन्त्र");
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
        myDialog = new MyDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);

        getYantralist();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(KrishiYantra.this, recyclerView, new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        YantraModel model = yantra_list.get(position);
                        Intent YantraIntent = new Intent(getApplicationContext(), KrishiYantraInformation.class);
                        YantraIntent.putExtra("YantraData", model);
                        startActivity(YantraIntent);
                        finish();
                    }


                    @Override
                    public void onLongClick(View view, int position) {
                    }
                })
        );


    }

    private void getYantralist() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            new Webservice().GetYantraList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray cropListJsonArray = response.optJSONArray("yantralist");
                                if (cropListJsonArray.length() > 0) {
                                    for (int i = 0; i < cropListJsonArray.length(); i++) {
                                        JSONObject CropDataObject = cropListJsonArray.optJSONObject(i);
                                        YantraModel listModel = new YantraModel();
                                        listModel.setId(CropDataObject.optString("news_id"));
                                        listModel.setName(CropDataObject.optString("heading"));
                                        listModel.setImage(CropDataObject.optString("image"));
                                        listModel.setDescription(CropDataObject.optString("description"));
                                        yantra_list.add(listModel);
                                    }
                                    recyclerViewAdapter = new KrishiYantraListAdapter(yantra_list, KrishiYantra.this);
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


}
