package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;


/**
 * Created by NZT-59 on 4/8/2017.
 */

public class Aboutus extends AppCompatActivity {
    protected TextView toolbarName;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setupToolbar();


    }

    private void setupToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarName);
            toolbarTitle.setText("About Us");
            toolbarTitle.setVisibility(View.VISIBLE);
//            toolbarImg = (ImageView) findViewById(R.id.toolbarImg);
//            toolbarImg.setVisibility(View.GONE);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }
}
