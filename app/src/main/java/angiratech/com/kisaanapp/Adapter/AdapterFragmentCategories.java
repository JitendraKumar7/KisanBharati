package angiratech.com.kisaanapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.Calendar;

import angiratech.com.kisaanapp.Activity.Scrolling;
import angiratech.com.kisaanapp.Model.BeanWeather;

/**
 * Created by NZT-59 on 5/19/2017.
 */

public class AdapterFragmentCategories extends RecyclerView.Adapter<AdapterFragmentCategories.ViewHolder> {
    private Context mContext;
    ArrayList<BeanWeather> catList;
    DisplayImageOptions options;
    boolean isListView;

    public AdapterFragmentCategories(Context c, ArrayList<BeanWeather> Imageid) {
        mContext = c;
        this.catList = Imageid;
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.logo_header).showImageForEmptyUri(R.drawable.logo_header)
                .showImageOnFail(R.drawable.logo_header).cacheInMemory(true).cacheOnDisc(true).build();
    }


    @Override
    public AdapterFragmentCategories.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        view = layoutInflater.inflate(R.layout.inflator_weather, parent, false);
        return new AdapterFragmentCategories.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BeanWeather beanWeather = new BeanWeather();
        Calendar cal = Calendar.getInstance();
        //cal.setTime(bean.getDate());

        holder.tv1.setText(Scrolling.dayList2[cal.get(Calendar.DAY_OF_WEEK) - 1]);
        holder.tv2.setText(catList.get(position).getMaxTemp());

        switch (catList.get(position).getCloud()) {

            case "sky is clear":
                holder.imageView.setImageResource(R.drawable.weather3);
                break;

            case "moderate rain":
            case "broken clouds":
                holder.imageView.setImageResource(R.drawable.weather4);
                break;

            case "few clouds":
                holder.imageView.setImageResource(R.drawable.weather1);
                break;

            case "scattered clouds":
                holder.imageView.setImageResource(R.drawable.weather2);
                break;
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv1, tv2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            imageView = (ImageView) itemView.findViewById(R.id.iv1);


        }
    }


}
