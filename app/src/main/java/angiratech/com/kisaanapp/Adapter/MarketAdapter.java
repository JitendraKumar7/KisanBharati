package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.MarketModel;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MyViewHolder> {
    private ArrayList<MarketModel> market_model = new ArrayList<>();
    private Activity activity;
    private ImageLoader imageLoader;
    private  DisplayImageOptions imgOptions;

    public MarketAdapter(ArrayList<MarketModel> list, Activity act) {
        this.market_model = list;
        this.activity = act;
        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.logo_header)
                .build();

    }

    @Override
    public MarketAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.market_desription, parent, false);

        return new MarketAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarketAdapter.MyViewHolder holder, int position) {
        MarketModel model = market_model.get(position);
        holder.categoryHeading.setText(model.getHeading());
        holder.categoryDescriptionWebView.getSettings().setJavaScriptEnabled(true);
        holder.categoryDescriptionWebView.setBackgroundColor(Color.TRANSPARENT);
        holder.categoryDescriptionWebView.loadDataWithBaseURL("", model.getDescrption(), "text/html", "UTF-8", "");
        imageLoader.displayImage(model.getImage(), holder.Imgpic, imgOptions, null);


    }

    @Override
    public int getItemCount() {
        return market_model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryHeading;
        public WebView categoryDescriptionWebView;
        public ImageView Imgpic;


        public MyViewHolder(View itemView) {
            super(itemView);
            categoryHeading=(TextView)itemView.findViewById(R.id.categoryHeading);
            Imgpic=(ImageView) itemView.findViewById(R.id.ImgPhoto);
            categoryDescriptionWebView=(WebView) itemView.findViewById(R.id.categoryDescriptionWebView);

        }
    }
}
