package angiratech.com.kisaanapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.CropListModel;

/**
 * Created by NZT-59 on 5/5/2017.
 */

public class Tab1Fragment extends Fragment {

    private ArrayList<CropListModel> crop_list;
    View v;
    private CropListModel cropListModel;
    private WebView tv_description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tab, container, false);
        tv_description=(WebView) v.findViewById(R.id.tv_description);
        tv_description.loadDataWithBaseURL("",cropListModel.getInfo1(),"text/html", "UTF-8", "");
        return v;


    }

    public static Fragment getInstance(CropListModel cropListModel) {
        Tab1Fragment tab1Fragment=new Tab1Fragment();
        tab1Fragment.cropListModel=cropListModel;
        return tab1Fragment;
    }
}
