package angiratech.com.kisaanapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.PageAdapter;
import angiratech.com.kisaanapp.Fragment.Tab1Fragment;
import angiratech.com.kisaanapp.Fragment.Tab2Fragment;
import angiratech.com.kisaanapp.Fragment.Tab3Fragment;
import angiratech.com.kisaanapp.Fragment.Tab4Fragment;
import angiratech.com.kisaanapp.Fragment.Tab5Fragment;
import angiratech.com.kisaanapp.Model.CropListModel;

/**
 * Created by NZT-59 on 5/5/2017.
 */

public class cropTabHolder extends FragmentActivity {
    private ViewPager viewPager;
    private PageAdapter mAdapter;
    private TextView toolbarName;
    protected Toolbar toolbar;
    private ImageView img_back;
    private ImageView toolbarImg;
    private TabLayout tab_layout;
    private CropListModel cropListModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_suggestion_tab_holder);
        if (savedInstanceState == null)
            cropListModel = getIntent().getParcelableExtra("data");
        viewPager = (ViewPager) findViewById(R.id.pager_events);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        toolbar();

        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_layout.setTabTextColors(ContextCompat.getColor(this, R.color.white),
                (ContextCompat.getColor(this, R.color.colorPrimary)));

        viewPager.setAdapter(createAdapter());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.e("tab title", "TAB TITLE" + tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private PagerAdapter createAdapter() {
        mAdapter = new PageAdapter(getSupportFragmentManager());
        ArrayList<Fragment> pagerItem = new ArrayList<>();
        pagerItem.add(Tab1Fragment.getInstance(cropListModel));
        pagerItem.add(Tab2Fragment.getInstance(cropListModel));
        pagerItem.add(Tab3Fragment.getInstance(cropListModel));
        pagerItem.add(Tab4Fragment.getInstance(cropListModel));
        pagerItem.add(Tab5Fragment.getInstance(cropListModel));
        mAdapter.setPagerItem(pagerItem);
        return mAdapter;
    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarName);
//            toolbarName.setText("");
//            toolbarName.setVisibility(View.VISIBLE);

            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText(cropListModel.getName() + " " + getResources().getString(R.string.ki_jankari));
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

            //setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
