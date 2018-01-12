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

import angiratech.com.kisaanapp.Model.SchemeListModel;

/**
 * Created by NZT-59 on 5/26/2017.
 */
public class SchemeListAdapter extends RecyclerView.Adapter<SchemeListAdapter.Holder> {
    private Context context;
    private final DisplayImageOptions imgOptions;
    private ArrayList<SchemeListModel> list_scheme = new ArrayList<>();
    private ImageLoader imageLoader;
    private Activity activity;

    public SchemeListAdapter(ArrayList<SchemeListModel> list, Activity activity) {
        this.list_scheme = list;
        this.activity = activity;
        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.logo_header)
                .build();

    }

    @Override
    public SchemeListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_scheme, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(SchemeListAdapter.Holder holder, int position) {
        //   AddressCheckoutModel addressCheckoutModel=addressCheckoutModelArrayList.get(position);
        holder.tv_krishi.setText(list_scheme.get(position).getName());
      imageLoader.displayImage(list_scheme.get(position).getImage(), holder.img_sarkari, imgOptions, null);
    }

    @Override
    public int getItemCount() {

        return list_scheme.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_krishi;
        public ImageView img_sarkari;
        // public ImageView iv_krishi;

        public Holder(View itemView) {

            super(itemView);
            tv_krishi = (TextView) itemView.findViewById(R.id.tv_scheme);
            img_sarkari=(ImageView) itemView.findViewById(R.id.img_sarkari);
            // iv_krishi=(ImageView)itemView.findViewById(R.id.iv_yantra);
        }
    }
}



