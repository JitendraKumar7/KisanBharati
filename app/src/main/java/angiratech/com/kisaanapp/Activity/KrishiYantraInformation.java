package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import angiratech.com.kisaanapp.Model.YantraModel;

/**
 * Created by SONY on 06-05-2017.
 */

public class KrishiYantraInformation extends AppCompatActivity {
    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back, iv_yantra;
    private ImageView toolbarImg;
    private YantraModel yantraListModel;
    private TextView tv_yantra_descrpition;
    private ImageLoader imageLoader;
    private DisplayImageOptions imgOptions;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_krishi_yantra_info);
        yantraListModel = getIntent().getParcelableExtra("YantraData");
        toolbar();
        tv_yantra_descrpition = (TextView) findViewById(R.id.tv_yantra_descrpition);
        tv_yantra_descrpition.setText(Html.fromHtml(yantraListModel.getDescription()));
        iv_yantra = (ImageView) findViewById(R.id.iv_yantra);
        imageLoader.displayImage(yantraListModel.getImage(), iv_yantra, imgOptions, null);

    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);
            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("कृषि यन्त्र की जानकारी");
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