package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.DealerListModel;

/**
 * Created by NZT-59 on 5/24/2017.
 */

public class DealerListAdapter extends RecyclerView.Adapter<DealerListAdapter.MyViewHolder> {
    private ArrayList<DealerListModel> dealer_list_model=new ArrayList<>();
    private Activity activity;

    public DealerListAdapter( ArrayList<DealerListModel> model,Activity act) {
        this.dealer_list_model = model;
        this.activity = act;


    }

    @Override
    public DealerListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_dealer_accepted, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DealerListModel model = dealer_list_model.get(position);

        holder.tv_name.setText("Name : "  + model.getName());
        holder.tv_contact_name.setText("Contact Number : " + model.getMobile());
        holder.tv_firm_name.setText("Firm Name : " + model.getFirmName());
        holder.tv_address.setText("Address : " + model.getAddress());


    }

    @Override
    public int getItemCount() {
        return dealer_list_model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_contact_name, tv_firm_name, tv_address;

        public MyViewHolder(View itemView) {

            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_contact_name = (TextView) itemView.findViewById(R.id.tv_contact_name);
            tv_firm_name = (TextView) itemView.findViewById(R.id.tv_firm_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);



        }
    }
}
