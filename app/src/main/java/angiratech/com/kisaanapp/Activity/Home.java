package angiratech.com.kisaanapp.Activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import angiratech.com.kisaanapp.Adapter.NewsListAdaptor;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.FirebBase.Config;
import angiratech.com.kisaanapp.Model.BannerModel;
import angiratech.com.kisaanapp.Model.BeanWeather;
import angiratech.com.kisaanapp.Model.CombindMyList;
import angiratech.com.kisaanapp.Model.NewsDetailsModel;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.AppConstants;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyToast;
import angiratech.com.kisaanapp.Youtube.YoutubeMain;
import angiratech.com.kisaanapp.colorDialogue.ColorDialog;


public class Home extends AppCompatActivity implements View.OnClickListener {
    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    private TextView toolbarImg;
    protected RelativeLayout contentHome;
    //  private LinearLayout lin_youtube;
    ImageView img_Notification, img_youtube;
    private ArrayList<BeanWeather> weather_list = new ArrayList<>();

    private LinearLayout rel_weather;
    protected ProgressBar progressBar;
    private RelativeLayout rel_1;
    private MyDialog myDialog;
    private MySession session;
    private TextView toolbarName, userNameTxt, userMobileTxt;
    private Button btn_news, btn_crop, btn_krishi, btn_help_Center, btn_dealer_us;
    private TextView tvMin, tvMax, tvWeatherLocation;
    private boolean isInFront;
    private RelativeLayout cat_container_fasal, cat_container_news, cat_container_bazar, cat_container_vikreta, cat_container_help, cat_container_yantra, cat_container_scheme,
            cat_container_seed, cat_container_feedback,cat_blog;
    List<CharSequence> list = new ArrayList<CharSequence>();
    protected ViewPager pager;
    private PagerAdapter adapter;

    protected RelativeLayout bannerLayout;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        initView();
        setupToolbar();
        setupDrawer();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();


                }
            }
        };

        GetValidUser();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);


        if (!TextUtils.isEmpty(regId)) {
            // txtRegId.setText("Firebase Reg Id: " + regId);
            Toast.makeText(this, "regId==" + regId, Toast.LENGTH_SHORT).show();


        } else {
            //txtRegId.setText("Firebase Reg Id is not received yet!");
            Toast.makeText(this, "Firebase Reg Id is not received yet!", Toast.LENGTH_SHORT).show();
        }
    }


    private void initView() {
        myDialog = new MyDialog(Home.this);
        session = new MySession(Home.this);
        //  lin_youtube = (LinearLayout) findViewById(R.id.lin_youtube);
        //  lin_youtube.setOnClickListener(this);
        rel_weather = (LinearLayout) findViewById(R.id.rel_weather);
        rel_weather.setOnClickListener(this);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbarName = (TextView) findViewById(R.id.toolbarName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvMin = (TextView) findViewById(R.id.tvMin);
        tvMax = (TextView) findViewById(R.id.tvMax);
        tvWeatherLocation = (TextView) findViewById(R.id.tvWeatherLocation);
        cat_container_fasal = (RelativeLayout) findViewById(R.id.cat_container_fasal);
        cat_container_fasal.setOnClickListener(this);
        cat_container_news = (RelativeLayout) findViewById(R.id.cat_container_news);
        cat_container_news.setOnClickListener(this);
        cat_container_bazar = (RelativeLayout) findViewById(R.id.cat_container_bazar);
        cat_container_bazar.setOnClickListener(this);
        cat_container_vikreta = (RelativeLayout) findViewById(R.id.cat_container_vikreta);
        cat_container_vikreta.setOnClickListener(this);
        cat_container_help = (RelativeLayout) findViewById(R.id.cat_container_help);
        cat_container_help.setOnClickListener(this);
        cat_container_yantra = (RelativeLayout) findViewById(R.id.cat_container_yantra);
        cat_container_yantra.setOnClickListener(this);
        cat_container_scheme = (RelativeLayout) findViewById(R.id.cat_container_scheme);
        cat_container_scheme.setOnClickListener(this);
        cat_container_bazar = (RelativeLayout) findViewById(R.id.cat_container_bazar);
        cat_container_bazar.setOnClickListener(this);
        cat_container_scheme = (RelativeLayout) findViewById(R.id.cat_container_scheme);
        cat_container_scheme.setOnClickListener(this);
        cat_container_seed = (RelativeLayout) findViewById(R.id.cat_container_seed);
        cat_container_seed.setOnClickListener(this);


        img_Notification = (ImageView) findViewById(R.id.img_Notification);
        img_youtube = (ImageView) findViewById(R.id.img_youtube);
        img_Notification.setOnClickListener(this);
        img_youtube.setOnClickListener(this);

        cat_container_feedback = (RelativeLayout) findViewById(R.id.cat_container_feedback);
        cat_container_feedback.setOnClickListener(this);


        cat_blog=(RelativeLayout) findViewById(R.id.cat_blog);
        cat_blog.setOnClickListener(this);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvMin.setText("Min Temp  " + session.getMinTemp());
        tvWeatherLocation.setText("City  " + session.getCity());
        tvMax.setText("Max Temp  " + session.getMaxTemp());
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarImg = (TextView) toolbar.findViewById(R.id.toolbarImg);
            toolbarImg.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_weather:
                Intent WeatherActivity = new Intent(Home.this, Scrolling.class);
                startActivity(WeatherActivity);

                break;

            case R.id.cat_container_news:
                Intent homeIntent = new Intent(Home.this, News.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
//
                break;
            case R.id.cat_container_fasal:
                Intent cropIntent = new Intent(Home.this, Crop.class);
                startActivity(cropIntent);

                break;
//
            case R.id.cat_container_yantra:
                Intent KrishiIntent = new Intent(Home.this, KrishiYantra.class);
                startActivity(KrishiIntent);

                break;
            case R.id.cat_container_help:
                Intent HelpIntent = new Intent(Home.this, HelpCenter.class);
                startActivity(HelpIntent);

                break;
            case R.id.cat_container_vikreta:
                Intent DealerIntent = new Intent(Home.this, Dealers.class);
                startActivity(DealerIntent);
                break;
            case R.id.cat_container_bazar:
                Intent MarketIntent = new Intent(Home.this, Market.class);
                startActivity(MarketIntent);
                break;
            case R.id.cat_container_scheme:
                Intent SchemeIntent = new Intent(Home.this, Scheme.class);
                startActivity(SchemeIntent);
                break;
            case R.id.cat_container_seed:
                Intent SeedsIntent = new Intent(Home.this, Seeds.class);
                startActivity(SeedsIntent);
                break;


            case R.id.cat_container_feedback:
                Intent FeedbackActivity = new Intent(Home.this, Feedback.class);
                startActivity(FeedbackActivity);

                break;
            //   case R.id.lin_youtube:
//                String url="http://www.youtube.com/user/JustinBieberVEVO";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
            //          Intent YouTubeIntent = new Intent(Home.this, YoutubeMain.class);
            //          startActivity(YouTubeIntent);
            //         break;



            case R.id.cat_blog:
                Intent BlogsActivity = new Intent(Home.this, Blogs.class);
                startActivity(BlogsActivity);
                break;


            case R.id.img_youtube:
                Intent YouTubeIntent = new Intent(Home.this, YoutubeMain.class);
                startActivity(YouTubeIntent);
                break;


            case R.id.img_Notification:
                Intent notific = new Intent(Home.this, Notifications.class);
                startActivity(notific);

                break;


        }

    }

    private void setupDrawer() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_HomePage:
                        Intent homeIntent = new Intent(Home.this, Home.class);
                        startActivity(homeIntent);
                        finish();
                        return true;
                    case R.id.nav_news:
                        Intent newsIntent = new Intent(Home.this, News.class);
                        startActivity(newsIntent);
                        //finish();
                        return true;

                    case R.id.nav_contactUs:
                        Intent contactUsIntent = new Intent(Home.this, ContactUs.class);
                        startActivity(contactUsIntent);
                        // finish();
                        return true;

                    case R.id.nav_AboutUs:

                        Intent aboutUsIntent = new Intent(Home.this, Aboutus.class);
                        startActivity(aboutUsIntent);
                        // finish();
                        return true;

                    case R.id.nav_crops:
                        Intent WeatherActivity = new Intent(Home.this, Crop.class);
                        startActivity(WeatherActivity);
                        // finish();
                        return true;
                    case R.id.nav_mr:
                        Intent MrActivity = new Intent(Home.this, Market.class);
                        startActivity(MrActivity);
                        // finish();
                        return true;
                    case R.id.nav_yantra:
                        Intent yantraActivity = new Intent(Home.this, KrishiYantra.class);
                        startActivity(yantraActivity);
                        // finish();
                        return true;
                    case R.id.nav_dealers:
                        Intent DealersActivity = new Intent(Home.this, Dealers.class);
                        startActivity(DealersActivity);
                        // finish();
                        return true;
                    case R.id.nav_seeds:
                        Intent SeedsActivity = new Intent(Home.this, Seeds.class);
                        startActivity(SeedsActivity);
                        // finish();
                        return true;
                    case R.id.nav_blogs:
                        Intent BlogsActivity = new Intent(Home.this, Blogs.class);
                        startActivity(BlogsActivity);
                        // finish();
                        return true;
                    case R.id.nav_scheme:
                        Intent SchemeActivity = new Intent(Home.this, Scheme.class);
                        startActivity(SchemeActivity);
                        // finish();
                        return true;
                    case R.id.nav_rateApp:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="
                                + "angiratech.com.kisaanapp")));

                        return true;
                    case R.id.nav_share:
                        try {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "KishanBharatiApp");
                            String sAux = "\nDownload it from here \n\n";
                            sAux = sAux + "https://play.google.com/store/apps/details?id=angiratech.com.kisaanapp&hl=en \n\n";
                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(i, "choose one"));
                        } catch (Exception e) {
                            //e.toString();
                        }

                        return true;


                    case R.id.nav_logout:
                        showPicDialog();
                        return true;

                    case R.id.nav_feedback:
                        Intent FeedbackActivity = new Intent(Home.this, Feedback.class);
                        startActivity(FeedbackActivity);
                        // finish();
                        return true;


                    case R.id.nav_notification:

                        Intent notific = new Intent(Home.this, Notifications.class);
                        startActivity(notific);

                        return true;





                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userNameTxt = (TextView) headerLayout.findViewById(R.id.userNameTxt);
        userMobileTxt = (TextView) headerLayout.findViewById(R.id.userMobileTxt);
        String name = session.getName();
        userNameTxt.setText("Name : " + session.getName());
        userMobileTxt.setText("Mobile : " + session.getMobile());
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {

            ActivityCompat.finishAfterTransition(Home.this);
            finish();
        }
    }

    protected boolean isNavDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    public void showPicDialog() {
        ColorDialog dialog = new ColorDialog(Home.this);
        dialog.setTitle(getString(R.string.logout));
        dialog.setAnimationEnable(true);
        dialog.setContentText("क्या आप निश्चित रूप से रद्द करना चाहते हैं?");
        dialog.setAnimationIn(myDialog.getInAnimationTest(Home.this));
        dialog.setAnimationOut(myDialog.getOutAnimationTest(Home.this));
        dialog.setPositiveListener(getString(R.string.logouthan), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                session.getActiveInstance(Home.this).logOutUser();
                Intent intent = new Intent(Home.this, SplashScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dialog.dismiss();
            }
        })
                .setNegativeListener("नहीं", new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }





    private void GetValidUser() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            new Webservice().GetValidUser(session.getMobile(),new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("News List Response", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {

                                if(response.getString("status").equals("Active")){

                                } else {

                                    exitDialog();

                                }

                            } else {
                                MyToast.Lmsg(Home.this, response.toString());
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
            myDialog.ShowPositiveDialog(Home.this, AppConstants.InternetTitle, AppConstants.InternetMsg, AppConstants.DialogPosTitle);
        }
    }

    private void exitDialog() {

        ColorDialog dialog = new ColorDialog(Home.this);
        dialog.setTitle(getString(R.string.nonactive_user));
        dialog.setAnimationEnable(true);
        dialog.setCancelable(false);
        dialog.setContentText(R.string.contactusat);
        dialog.setContentText(R.string.phone);

        dialog.setAnimationIn(myDialog.getInAnimationTest(Home.this));
        dialog.setAnimationOut(myDialog.getOutAnimationTest(Home.this));
        dialog.setPositiveListener(getString(R.string.logout), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {



                session.getActiveInstance(Home.this).logOutUser();
                Intent intent = new Intent(Home.this, SplashScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();

    }


}
