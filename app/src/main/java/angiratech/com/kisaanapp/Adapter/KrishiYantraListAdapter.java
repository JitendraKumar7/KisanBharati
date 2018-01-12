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

import angiratech.com.kisaanapp.Model.YantraModel;

/**
 * Created by NZT-59 on 5/11/2017.
 */

public class KrishiYantraListAdapter extends RecyclerView.Adapter<KrishiYantraListAdapter.Holder> {
    private Context context;
    private final DisplayImageOptions imgOptions;
    private ArrayList<YantraModel> list_yantra = new ArrayList<>();
    // String[] mresources;
    private ImageLoader imageLoader;
    private Activity activity;

    public KrishiYantraListAdapter(ArrayList<YantraModel> list, Activity activity) {
        this.list_yantra=list;
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
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_krishi, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //   AddressCheckoutModel addressCheckoutModel=addressCheckoutModelArrayList.get(position);
        holder.tv_krishi.setText(list_yantra.get(position).getName());
        imageLoader.displayImage(list_yantra.get(position).getImage(), holder.iv_krishi, imgOptions, null);
    }

    @Override
    public int getItemCount() {

        return list_yantra.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_krishi;
        public ImageView iv_krishi;

        public Holder(View itemView) {

            super(itemView);
            tv_krishi = (TextView) itemView.findViewById(R.id.tv_krishi);
            iv_krishi=(ImageView)itemView.findViewById(R.id.iv_yantra);
        }
    }
}

