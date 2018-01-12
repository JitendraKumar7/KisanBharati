package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.HelpCenterModel;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class HelpCenterAdapter extends RecyclerView.Adapter<HelpCenterAdapter.MyViewHolder> {
    private ArrayList<HelpCenterModel> list_model = new ArrayList<>();
    private Activity activity;

    public HelpCenterAdapter(ArrayList<HelpCenterModel> list, Activity act) {
        this.list_model = list;
        this.activity = act;

    }

    @Override
    public HelpCenterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.help_desription, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HelpCenterModel model = list_model.get(position);
        holder.categoryHeading.setText(model.getHeading());
        holder.categoryDescriptionWebView.getSettings().setJavaScriptEnabled(true);
        holder.categoryDescriptionWebView.setBackgroundColor(Color.TRANSPARENT);
        holder.categoryDescriptionWebView.loadDataWithBaseURL("", model.getDescrption(), "text/html", "UTF-8", "");

    }

    @Override
    public int getItemCount() {
        return list_model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryHeading;
        public WebView categoryDescriptionWebView;


        public MyViewHolder(View itemView) {
            super(itemView);
            categoryHeading=(TextView)itemView.findViewById(R.id.categoryHeading);
            categoryDescriptionWebView=(WebView) itemView.findViewById(R.id.categoryDescriptionWebView);

        }
    }
}
