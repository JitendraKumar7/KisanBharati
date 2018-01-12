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

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.SeedListModel;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class SeedListAdapter extends RecyclerView.Adapter<SeedListAdapter.MyViewHolder> {

    private Context context;
    private final DisplayImageOptions imgOptions;
    private ArrayList<SeedListModel> list_seed = new ArrayList<>();
    // String[] mresources;
    private ImageLoader imageLoader;
    private Activity activity;


    public SeedListAdapter(ArrayList<SeedListModel> list, Activity activity) {
        this.list_seed=list;
        this.activity=activity;
        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.logo_header)
                .build();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_seed, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SeedListModel seedListModel = list_seed.get(position);
        holder.tv_wheat.setText(seedListModel.getName());
        imageLoader.displayImage(seedListModel.getImage(), holder.img_wheat, imgOptions, null);


    }

    @Override
    public int getItemCount() {
        return list_seed.size();
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
