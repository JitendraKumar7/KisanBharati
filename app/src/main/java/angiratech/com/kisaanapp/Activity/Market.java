package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.MarketAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.MarketModel;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;

/**
 * Created by NZT-59 on 5/9/2017.
 */

public class Market extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private ArrayList<MarketModel> market_list_model = new ArrayList<>();
    private RecyclerView recyclerView;
    private MarketAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);///
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_market);
        toolbar();
        init();
    }

    private void init() {
        myDialog = new MyDialog(Market.this);
        recyclerView = (RecyclerView) findViewById(R.id.recylerviewhelp);

        mLayoutManager = new LinearLayoutManager(Market.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        getmarketInformation();


    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("बाज़ार भाव");
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

    public void getmarketInformation() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            new Webservice().MarketRequest(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                    super.onSuccess(statusCode, headers, jsonObject);
                    myDialog.CancelProgressDialog();
                    if (jsonObject != null) {
                        try {
                            Spanned html = Html.fromHtml(String.valueOf(jsonObject));
                            JSONObject response = new JSONObject(String.valueOf(html));

                            Log.e("News List Response", response.toString());

                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray marketJsonArray = response.optJSONArray("marketing");
                                if (marketJsonArray.length() > 0) {
                                    for (int i = 0; i < marketJsonArray.length(); i++) {
                                        JSONObject MarketDataObject = marketJsonArray.optJSONObject(i);
                                        MarketModel listModel = new MarketModel();
                                        listModel.setId(MarketDataObject.optString("id"));
                                        listModel.setHeading(MarketDataObject.optString("heading"));
                                        listModel.setDescrption(MarketDataObject.optString("description"));
                                        listModel.setImage(MarketDataObject.optString("image"));
                                        market_list_model.add(listModel);
                                    }
                                    adapter = new MarketAdapter(market_list_model, Market.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            }

                        } catch (NullPointerException | JSONException e) {
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbarImg) {
            Intent notific = new Intent(Market.this, Notifications.class);
            startActivity(notific);
        }

    }

}
