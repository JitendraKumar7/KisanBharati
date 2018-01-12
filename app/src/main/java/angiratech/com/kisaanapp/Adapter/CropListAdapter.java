package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.CropListModel;

/**
 * Created by NZT-59 on 5/11/2017.
 */

public class CropListAdapter extends RecyclerView.Adapter<CropListAdapter.MyViewHolder> {
    private Context context;
    ImageLoaderConfiguration config;
    File cacheDir;

    private final DisplayImageOptions imgOptions;
    private ArrayList<CropListModel> list_crop = new ArrayList<>();

    // String[] mresources;
    private ImageLoader imageLoader;
    private Activity act;

    public CropListAdapter(Activity activity, ArrayList<CropListModel> data) {
        this.act = activity;
        this.list_crop = data;

        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.logo_header)
                .build();
    }

    @Override
    public CropListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_crop, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CropListAdapter.MyViewHolder holder, int position) {
        CropListModel cropListModel = list_crop.get(position);
        holder.tv_wheat.setText(cropListModel.getName());
        imageLoader.displayImage(cropListModel.getImage(), holder.img_wheat, imgOptions, null);}

    @Override
    public int getItemCount() {
        return list_crop != null ? list_crop.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_wheat;
        public ImageView img_wheat;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_wheat = (TextView) itemView.findViewById(R.id.tv_wheat);
            img_wheat = (ImageView) itemView.findViewById(R.id.img_wheat);

        }
    }


}


