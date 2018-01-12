package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.NewsListAdaptor;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.BannerModel;
import angiratech.com.kisaanapp.Model.CombindMyList;
import angiratech.com.kisaanapp.Model.NewsDetailsModel;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.AppConstants;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyToast;
import angiratech.com.kisaanapp.Volley.VollyRequests;

/**
 * Created by NZT-59 on 5/4/2017.
 */

public class News extends AppCompatActivity implements View.OnClickListener {
    private MyDialog myDialog;
    private MySession session;
    private TextView userNameTxt, userMobileTxt;
    private VollyRequests vollyRequests = new VollyRequests();
    private ArrayList<NewsDetailsModel> newsDetailsList = new ArrayList<>();
    private ArrayList<BannerModel> bannerDetailsList = new ArrayList<>();
    private LinearLayout contentVisibility;
    public NewsDetailsModel newsDetailsModel;
    private static final String MAX_ITEMS_PER_REQUEST = "10";
    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    protected RelativeLayout contentHome;
    protected Button retryBtn;
    protected ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsListAdaptor mAdapter;
    protected RecyclerView myNewsRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String lastNewsId = "0", selectedCategoryId = "";
    private int offset = 0, totalNewsCount = 0;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private boolean filterActive = false;
    int bannerlength;
    int bannercount = 0;
    int finalvalue;
    ArrayList<CombindMyList> combindlist = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_news);
        initView();
        setupToolbar();
        if (CheckInternet.isNetwork(News.this)) {
            GetNewsRequest();
        }
    }
    private void initView() {
        myDialog = new MyDialog(News.this);
        session = new MySession(News.this);
        toolbarName = (TextView) findViewById(R.id.toolbarName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        retryBtn = (Button) findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(News.this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        contentVisibility = (LinearLayout) findViewById(R.id.contentVisibility);
        myNewsRecyclerView = (RecyclerView) findViewById(R.id.myNewsRecyclerView);
        myNewsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(News.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myNewsRecyclerView.setLayoutManager(mLayoutManager);
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("कृषि समाचार");
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


    private void GetNewsRequest() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            new Webservice().GetNewsList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray newsListJsonArray = response.optJSONArray("news");
                                if (newsListJsonArray.length() > 0) {
                                    int totalsize = 0;
                                    int valincrement = 0;
                                    // First news data
                                   for (int i = 0; i < newsListJsonArray.length(); i++) {
                                        JSONObject newsDataObject = newsListJsonArray.optJSONObject(i);
                                        NewsDetailsModel newsModel = new NewsDetailsModel();
                                        lastNewsId = newsDataObject.optString("news_id");
                                        newsModel.setNewsId(newsDataObject.optString("news_id"));
                                        newsModel.setHeading(newsDataObject.optString("heading"));
                                        newsModel.setImage(newsDataObject.optString("image"));
                                        newsModel.setDescription(newsDataObject.optString("description"));
                                        newsModel.setReadStatus(newsDataObject.optString("read_status"));
                                        newsModel.setCreatedAt(newsDataObject.optString("created_at"));
                                        newsDetailsList.add(newsModel);
                                        Log.e("NEWSRESPONSE", newsDataObject.toString());
                                    }
                                   // Banner Data Listing
                                    JSONArray bannerListJsonArray = response.optJSONArray("banner");
                                    for (int j = 0; j < bannerListJsonArray.length(); j++) {
                                        JSONObject bannerDataObject = bannerListJsonArray.optJSONObject(j);
                                        BannerModel bannerModel = new BannerModel();
                                        bannerModel.setImage_banner(bannerDataObject.optString("banner_image"));
                                        bannerModel.setBanner_title(bannerDataObject.optString("title"));
                                        bannerModel.setBanner_description(bannerDataObject.optString("description"));
                                        bannerDetailsList.add(bannerModel);
                                    }
                                    // Combind Dataa
                                    totalsize = newsDetailsList.size() + bannerDetailsList.size();
                                    for (int d = 0; d < totalsize; d++) {
                                        bannerlength = bannerDetailsList.size();
                                        CombindMyList comblist = new CombindMyList();
                                        try {
                                            if ((d % 5 == 0) && (d != 0)) {
                                                if (bannercount >= bannerlength) {
                                                  //  comblist.setBanner(bannerDetailsList.get(d));
                                                    comblist.setNews(newsDetailsList.get(d - bannercount));
                                                }
                                                else {
                                                    if(bannerDetailsList.get(bannercount)!=null) {
                                                        comblist.setBanner(bannerDetailsList.get(bannercount));
                                                        bannercount++;
                                                    }else{
                                                        comblist.setNews(newsDetailsList.get(d - bannercount));
                                                    }
                                                }
                                            } else {
                                                comblist.setNews(newsDetailsList.get(d - bannercount));
                                            }
                                            combindlist.add(comblist);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    mAdapter = new NewsListAdaptor(combindlist, News.this, myNewsRecyclerView,bannercount);
                                    myNewsRecyclerView.setAdapter(mAdapter);
                                }

                            } else {
                                MyToast.Lmsg(News.this, response.toString());
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
            myDialog.ShowPositiveDialog(News.this, AppConstants.InternetTitle, AppConstants.InternetMsg, AppConstants.DialogPosTitle);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.retryBtn) {
            if (CheckInternet.isNetwork(this)) {
                GetNewsRequest();
            }

        }
    }
}
