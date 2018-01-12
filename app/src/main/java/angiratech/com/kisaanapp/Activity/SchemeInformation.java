package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import angiratech.com.kisaanapp.Model.SchemeListModel;

/**
 * Created by NZT-59 on 5/26/2017.
 */

public class SchemeInformation extends AppCompatActivity {
    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private SchemeListModel schemeListModel;
    private TextView tv_scheme_descrpition;
    ImageView imgDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_info);
        schemeListModel = getIntent().getParcelableExtra("SchemeData");

        toolbar();

        imgDetail = (ImageView) findViewById(R.id.imgDetail);
        tv_scheme_descrpition = (TextView) findViewById(R.id.tv_scheme_descrpition);
        tv_scheme_descrpition.setText(Html.fromHtml(schemeListModel.getDescription()));


        Glide.with(SchemeInformation.this).load(schemeListModel.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgDetail);

    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("सरकारी योजनाओ की जानकारी");
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
