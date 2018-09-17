package angiratech.com.kisaanapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;


public class ContactUs extends AppCompatActivity implements View.OnClickListener{
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_contact_us);


        setuptoolbar();


    }

    private void setuptoolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText(getResources().getString(R.string.contactUs));
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
    public void onClick(View v) {
        if (v.getId() == R.id.toolbarImg) {
            Intent notific = new Intent(ContactUs.this, Notifications.class);
            startActivity(notific);

        }
    }
}
