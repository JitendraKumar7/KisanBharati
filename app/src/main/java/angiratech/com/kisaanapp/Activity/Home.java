package angiratech.com.kisaanapp.Activity;


import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;
import java.util.List;

import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.BeanWeather;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Youtube.YoutubeMain;
import angiratech.com.kisaanapp.colorDialogue.ColorDialog;


public class Home extends AppCompatActivity implements View.OnClickListener {
    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    private ImageView toolbarImg;
    protected RelativeLayout contentHome;
    private LinearLayout lin_youtube;
    private ArrayList<BeanWeather> weather_list = new ArrayList<>();

    private RelativeLayout rel_weather;
    protected ProgressBar progressBar;
    private RelativeLayout rel_1;
    private MyDialog myDialog;
    private MySession session;
    private TextView toolbarName, userNameTxt, userMobileTxt;
    private Button btn_news, btn_crop, btn_krishi, btn_help_Center, btn_dealer_us;
    private TextView tvMin, tvWeatherLocation;
    private boolean isInFront;
    private RelativeLayout cat_container_fasal, cat_container_news, cat_container_bazar, cat_container_vikreta, cat_container_help, cat_container_yantra, cat_container_scheme,
            cat_container_seed,cat_container_feedback;
    List<CharSequence> list = new ArrayList<CharSequence>();
    protected ViewPager pager;
    private PagerAdapter adapter;

    protected RelativeLayout bannerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupToolbar();
        setupDrawer();

    }

    private void initView() {
        myDialog = new MyDialog(Home.this);
        session = new MySession(Home.this);
        lin_youtube = (LinearLayout) findViewById(R.id.lin_youtube);
        lin_youtube.setOnClickListener(this);
        rel_weather = (RelativeLayout) findViewById(R.id.rel_weather);
        rel_weather.setOnClickListener(this);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbarName = (TextView) findViewById(R.id.toolbarName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvMin = (TextView) findViewById(R.id.tvMin);

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


        cat_container_feedback=(RelativeLayout)findViewById(R.id.cat_container_feedback);
        cat_container_feedback.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvMin.setText(session.getMinTemp());
        tvWeatherLocation.setText(session.getCity());

    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarImg = (ImageView) toolbar.findViewById(R.id.toolbarImg);
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
            case R.id.lin_youtube:
//                String url="http://www.youtube.com/user/JustinBieberVEVO";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
                Intent YouTubeIntent = new Intent(Home.this, YoutubeMain.class);
                startActivity(YouTubeIntent);
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
        dialog.setContentText("Are you sure want to Logout?");
        dialog.setAnimationIn(myDialog.getInAnimationTest(Home.this));
        dialog.setAnimationOut(myDialog.getOutAnimationTest(Home.this));
        dialog.setPositiveListener(getString(R.string.logout), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                session.getActiveInstance(Home.this).logOutUser();
                Intent intent = new Intent(Home.this, SplashScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dialog.dismiss();
            }
        })
                .setNegativeListener("cancel", new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }


}
