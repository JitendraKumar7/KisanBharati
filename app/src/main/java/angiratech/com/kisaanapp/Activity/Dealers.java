package angiratech.com.kisaanapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.DealerDetailsAdapter;
import angiratech.com.kisaanapp.Adapter.DealerListAdapter;
import angiratech.com.kisaanapp.CustomSpinnner.MaterialSpinner;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.DealerDetailModel;
import angiratech.com.kisaanapp.Model.DealerListModel;
import angiratech.com.kisaanapp.Model.Districts;
import angiratech.com.kisaanapp.Model.State;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyToast;
import angiratech.com.kisaanapp.Volley.VollyRequests;

/**
 * Created by SONY on 06-05-2017.
 */

public class Dealers extends AppCompatActivity implements MaterialSpinner.OnItemSelectedListener, MaterialSpinner.OnNothingSelectedListener, View.OnClickListener {
    private MaterialSpinner spnr_distrct;
    private String[] state, district;
    private TextView toolbarName, spnr_state;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private ArrayList<State> array_list = new ArrayList<>();
    private ArrayList<Districts> list_districts = new ArrayList<>();
    private ArrayList<DealerListModel> dealer_list_model = new ArrayList<>();
    private ArrayList<DealerDetailModel> dealer_details_model = new ArrayList<>();
    private VollyRequests vollyRequests = new VollyRequests();
    private FloatingActionButton round_Fab;
    private RecyclerView rv_add_dealer, rv_list_dealer;
    private LinearLayoutManager mLayoutManager;
    private DealerListAdapter dealerListAdapter;
    private DealerDetailsAdapter dealerDetailsAdapter;
    private EditText edt_name, edt_mobile, edt_firm, edt_address, edt_scope;
    private Button btn_save, btn_cancel;
    private String spinner_item;
    private String districtSelectedID = "", stateSelectedID = "";
    private Districts selecteddistrict;
    private EditText name, address, tahsil, District, State, scope;
    private Button btnsave, btncancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_dealers);
        intit();
        toolbar();
        getDealerList();
    }


    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("बीज,खाद,कीटनाशक विक्रेता");
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


    private void intit() {
        spnr_state = (TextView) findViewById(R.id.spnr_state);
        spnr_distrct = (MaterialSpinner) findViewById(R.id.spnr_district);
        mLayoutManager = new LinearLayoutManager(Dealers.this);
        myDialog = new MyDialog(Dealers.this);
        round_Fab = (FloatingActionButton) findViewById(R.id.round_Fab);
        round_Fab.setOnClickListener(Dealers.this);
        //   district = getResources().getStringArray(R.array.district);
        rv_add_dealer = (RecyclerView) findViewById(R.id.rv_add_dealer);
        rv_list_dealer = (RecyclerView) findViewById(R.id.rv_list_dealer);

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetDistrictList();
        getDealerList();

    }

    private void GetDistrictList() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            Webservice.GetStateList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("DISTRICT LIST", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray districtListJsonArray = response.optJSONArray("districtlist");
                                if (districtListJsonArray.length() > 0) {
                                    for (int i = 0; i < districtListJsonArray.length(); i++) {
                                        JSONObject districtDataObject = districtListJsonArray.optJSONObject(i);
                                        selecteddistrict = new Districts();
                                        selecteddistrict.setDistrictsId(districtDataObject.optString("id"));
                                        selecteddistrict.setDistrictsName(districtDataObject.optString("district_name"));
                                        selecteddistrict.setState(districtDataObject.optString("state_name"));
                                        list_districts.add(selecteddistrict);
                                        spnr_distrct.setItems(list_districts);
                                        spnr_distrct.setOnItemSelectedListener(Dealers.this);
                                        spnr_distrct.setOnNothingSelectedListener(Dealers.this);
                                    }
                                } else {
                                    MyToast.Lmsg(Dealers.this, response.toString());
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
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        switch (view.getId()) {
            case R.id.spnr_district:
                selecteddistrict = list_districts.get(position);
                Districts district = list_districts.get(position);

                spnr_state.setText(list_districts.get(position).getState());
                //  districtSelectedID = list_districts.get(position).getDistrictsId();


        }
    }


    @Override
    public void onNothingSelected(MaterialSpinner spinner) {

    }

    public void showCustomDialog(Context context) {
        final Dialog dialog = new Dialog(Dealers.this);

        dialog.setContentView(R.layout.custom_dialog);

        edt_name = (EditText) dialog.findViewById(R.id.edt_name);
        edt_address = (EditText) dialog.findViewById(R.id.edt_address);
        edt_mobile = (EditText) dialog.findViewById(R.id.edt_mobile);
        edt_firm = (EditText) dialog.findViewById(R.id.edt_firm);
        //  edt_scope = (EditText) dialog.findViewById(R.id.edt_scope);
        btn_save = (Button) dialog.findViewById(R.id.btn_save);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final String[] spinnerList ={"बीज", "खाद", "कीटनाशक", "कृषि यन्त्र","कारोबारी","अन्य"};
         MaterialSpinner custom_scope = (MaterialSpinner) dialog.findViewById(R.id.custom_scope);
        custom_scope.setItems(spinnerList);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.FILL_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);

        dialog.show();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {
                    //showPicDialog();
                    userDetailsRequest();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill requirement", Toast.LENGTH_SHORT).show();

                }


            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void userDetailsRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            Webservice.GetDealerList(edt_name.getText().toString(), edt_mobile.getText().toString(), edt_firm.getText().toString(), edt_address.getText().toString(), edt_scope.getText().toString(), districtSelectedID,
                    new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            myDialog.CancelProgressDialog();

                            if (response != null) {
                                Log.e("News List Response", response.toString());
                                try {
                                    if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("ok")) {

                                        JSONObject DealerDataObject = response.optJSONObject("dealer");
                                        DealerListModel dealerListModel = new DealerListModel();
                                        dealerListModel.setId(DealerDataObject.optString("dealer_id"));
                                        dealerListModel.setName(DealerDataObject.optString("name"));
                                        dealerListModel.setMobile(DealerDataObject.optString("mobile"));
                                        dealerListModel.setFirmName(DealerDataObject.optString("firm_name"));
                                        dealerListModel.setAddress(DealerDataObject.optString("address"));
                                        dealer_list_model.add(dealerListModel);

                                    }
                                    dealerListAdapter = new DealerListAdapter(dealer_list_model, Dealers.this);
                                    rv_add_dealer.setVisibility(View.VISIBLE);
                                    rv_list_dealer.setVisibility(View.GONE);
                                    mLayoutManager = new LinearLayoutManager(Dealers.this);
                                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    rv_add_dealer.setLayoutManager(mLayoutManager);
                                    rv_add_dealer.setAdapter(dealerListAdapter);
                                    dealerListAdapter.notifyDataSetChanged();
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

    private void getDealerList() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();

            Webservice.GetDetailList(new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();

                    if (response != null) {
                        Log.e("Dealer List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("ok")) {
                                JSONArray dealerListJsonArray = response.optJSONArray("dealerlist");
                                if (dealerListJsonArray.length() > 0) {
                                    for (int i = 0; i < dealerListJsonArray.length(); i++) {
                                        JSONObject DealerDataObject = dealerListJsonArray.optJSONObject(i);
                                        DealerDetailModel dealerdetailModel = new DealerDetailModel();
                                        dealerdetailModel.setId(DealerDataObject.optString("id"));
                                        dealerdetailModel.setName(DealerDataObject.optString("name"));
                                        dealerdetailModel.setMobile(DealerDataObject.optString("contact_name"));
                                        dealerdetailModel.setFirmName(DealerDataObject.optString("firm_name"));
                                        dealerdetailModel.setAddress(DealerDataObject.optString("address"));
                                        dealer_details_model.add(dealerdetailModel);
                                    }
                                    dealerDetailsAdapter = new DealerDetailsAdapter(dealer_details_model, Dealers.this);
                                    rv_list_dealer.setVisibility(View.VISIBLE);
                                    rv_add_dealer.setVisibility(View.GONE);
                                    rv_list_dealer.setHasFixedSize(true);
                                    mLayoutManager = new LinearLayoutManager(Dealers.this);
                                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    rv_list_dealer.setLayoutManager(mLayoutManager);
                                    rv_list_dealer.setAdapter(dealerDetailsAdapter);
                                    dealerDetailsAdapter.notifyDataSetChanged();
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

    private boolean validation() {
        if (edt_name.getText().toString().trim().equals("")) {
            edt_name.setError("Please Enter your Name");
            edt_name.requestFocus();
            return false;
        } else if (edt_address.getText().toString().trim().equals("")) {
            edt_address.setError("Please Enter Valid address");
            edt_address.requestFocus();
            return false;
        } else if (edt_mobile.getText().toString().trim().equals("")) {
            edt_mobile.setError("Please Enter Valid Mobile No.");
            edt_mobile.requestFocus();
            return false;
        } else if (edt_firm.getText().toString().trim().equals("")) {
            edt_firm.setError("Please Enter Valid Firm");
            edt_firm.requestFocus();
            return false;
        } else if (edt_scope.getText().toString().trim().equals("")) {
            edt_scope.setError("Please Enter Valid Scope");
            edt_scope.requestFocus();
            return false;
        }

        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.round_Fab) {
            showCustomDialog(Dealers.this);

        }else if(v.getId()==R.id.toolbarImg){

            Intent notific = new Intent(Dealers.this, Notifications.class);
            startActivity(notific);
        }

    }


}

