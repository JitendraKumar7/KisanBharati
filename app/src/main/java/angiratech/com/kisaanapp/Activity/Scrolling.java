package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import angiratech.com.kisaanapp.Adapter.AdapterFragmentCategories;
import angiratech.com.kisaanapp.Model.BeanWeather;
import angiratech.com.kisaanapp.Model.Weather;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.service.GPSTracker;


public class Scrolling extends AppCompatActivity implements View.OnClickListener {
    public static String[] cloudyArray = {"sky is clear", "broken clouds", "few clouds", "scattered clouds"};
    public static String[] dayList2 = {"SUN", "MON", "TUES", "WED", "THURS", "FRI", "SAT"};
    public static String[] dayList = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    public static String[] monthsList = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER",
            "NOVEMBER", "DECEMBER"};
    private static final String TAG = "SPLASH";
    public FragmentTransaction fragmentTransaction;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String place_location = "";
    TextView tvMin, tvMax, tvLocation, tvDateTime;
    private ProgressBar spinner;
    private ArrayList<Weather> list_weather;
    private ArrayList<BeanWeather> listWeather = new ArrayList<>();
    ImageView imageView;
    public static boolean isFragWeatherVisible;
    Calendar cal;
    Button btnLeft, btnRight;
    private String cityName;
    private RecyclerView rv_weather;
    private FragmentManager manager;
    private AdapterFragmentCategories adapter;
    TextView weather_report, place, weather_icon, country, icon_text;
    List myList;
    String API_KEY = "37fee3e4b21b4eb76faf18820871c779"; //insert api key here
    private final static String PATH_TO_WEATHER_FONT = "fonts/weather.ttf";
    private ListView lv;
    private MySession session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.fragment_weather);


        tvMin = (TextView) findViewById(R.id.tvMin);
        tvMax = (TextView) findViewById(R.id.tvMax);
        tvLocation = (TextView) findViewById(R.id.tvWeatherLocation);
        tvDateTime = (TextView) findViewById(R.id.tvTodayDate);
        rv_weather = (RecyclerView) findViewById(R.id.rv_weather);
//        spinner = (ProgressBar) findViewById(R.id.progressBar1);
//        spinner.setVisibility(View.VISIBLE);
//        btnLeft = (Button) findViewById(R.id.btn1);
//        btnRight = (Button) findViewById(R.id.btn2);
//        btnLeft.setOnClickListener(this);
//        btnRight.setRotation(180);
//        btnRight.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.ivWeather);

        weather_icon = (TextView) findViewById(R.id.weather_icon);
        country = (TextView) findViewById(R.id.country);
//        weatherFont = Typeface.createFromAsset(getAssets(), PATH_TO_WEATHER_FONT);
//        weather_icon.setTypeface(weatherFont);
        mLayoutManager = new LinearLayoutManager(this);
        rv_weather.setLayoutManager(mLayoutManager);


        weather_report = (TextView) findViewById(R.id.weather_report);
        place = (TextView) findViewById(R.id.place);

        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation()) {
            String stringLatitude = String.valueOf(gpsTracker.getLatitude());
            String stringLongitude = String.valueOf(gpsTracker.getLongitude());


            new Webservice().getWeather(stringLatitude, stringLongitude, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    if (response != null) {
                        try {
                            JSONObject cityobject = response.getJSONObject("city");
                            tvLocation.setText(cityobject.optString("name"));
                            session.getActiveInstance(Scrolling.this).SaveStringPref(MySession.CITY, cityobject.optString("name"));


                            JSONArray objectArray = response.getJSONArray("list");
                            for (int i = 0; i < objectArray.length(); i++) {
                                BeanWeather bean = new BeanWeather();
                                JSONObject weatherDetails = objectArray.getJSONObject(i);
                                JSONObject jsonObject = weatherDetails.getJSONObject("temp");
                                JSONArray weather = weatherDetails.getJSONArray("weather");
                                String maxTemp = jsonObject.optString("max");
                                bean.setMaxTemp(jsonObject.optString("max"));
                                String minTemp = jsonObject.optString("min");
                                SimpleDateFormat f = new SimpleDateFormat("h:mm a E ");
                                System.out.println(f.format(new Date()));
                                String dd = f.format(new Date());

                                String dateTime = dd;

                                tvDateTime.setText(dateTime);
                                bean.setDate(dateTime);


                                // weatherDetails.optString("dt_txt");
                                JSONObject montiLala = weather.optJSONObject(0);
                                bean.setCloud(montiLala.optString("description"));
                                String cloudy = montiLala.optString("description");

                                maxTemp = ("" + (Math.round(Float.parseFloat(maxTemp)) - 273 + "\u2103"));
                                bean.setMaxTemp(maxTemp);


                                minTemp = "" + (Math.round(Float.parseFloat(minTemp)) - 273 + "\u2103");

                                session.getActiveInstance(Scrolling.this).SaveStringPref(MySession.MIN_TEMP, minTemp);
                                session.getActiveInstance(Scrolling.this).SaveStringPref(MySession.MAX_TEMP, maxTemp);
                                tvMin.setText(minTemp);
                                tvMax.setText(maxTemp + "/");


                                // if (!listWeather.contains(bean))
                                listWeather.add(bean);
                                adapter = new AdapterFragmentCategories(Scrolling.this, listWeather);
                                rv_weather.setAdapter(adapter);


                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("RESPONSE", e.toString());

                        }
                    }


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    // TODO Auto-generated method stub
                    super.onFailure(statusCode, headers, throwable, errorResponse);


                }

            });



            String postalCode = gpsTracker.getPostalCode(this);
            String addressLine = gpsTracker.getAddressLine(this);

            //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + stringLatitude + "\nLong: " + stringLongitude, Toast.LENGTH_LONG).show();
        } else {
            gpsTracker.showSettingsAlert();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.finishAfterTransition(Scrolling.this);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFragWeatherVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFragWeatherVisible = false;
    }

    @Override
    public void onClick(View v) {


    }



}
