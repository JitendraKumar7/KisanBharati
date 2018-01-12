package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import angiratech.com.kisaanapp.Model.SeedListModel;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class SeedListInformation extends AppCompatActivity {
    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg,iv_backend;
    private SeedListModel seedListModel;
    private WebView tv_seed_descrpition;
    private ImageLoader imageLoader;
    private DisplayImageOptions imgOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seed_information);
        toolbar();
        seedListModel = getIntent().getParcelableExtra("Seeddata");
        tv_seed_descrpition=(WebView)findViewById(R.id.tv_seed_descrpition);
        iv_backend=(ImageView) findViewById(R.id.iv_backend);

            tv_seed_descrpition.loadDataWithBaseURL("", seedListModel.getInfomation(), "text/html", "UTF-8", "");
            //iv_backend.setImageResource(seedListModel.getImage());


    }


    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("बीजों की जानकारी");
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

}
