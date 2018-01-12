package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Activity.Blogs;
import angiratech.com.kisaanapp.Model.BlogListModel;

/**
 * Created by NZT-59 on 6/2/2017.
 */

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.MyViewHolder> {
    private ArrayList<BlogListModel> list_blogs = new ArrayList<>();

    private Activity activity;
    private RecyclerView rv_comment;


    public BlogsAdapter(Activity activity, ArrayList<BlogListModel> model_list) {
        this.list_blogs = model_list;
        this.activity = activity;

    }


    @Override
    public BlogsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_blogs, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        BlogListModel blogmodel = list_blogs.get(position);
        if (blogmodel != null) {
            holder.tv_title.setText(blogmodel.getTitle());
            holder.tv_description.setText(blogmodel.getDescription());
            if (blogmodel.getUser() != null) {
                holder.tv_name.setText(blogmodel.getUser());
                holder.tv_name.setVisibility(View.VISIBLE);
            } else {
                holder.tv_name.setVisibility(View.GONE);

            }

            holder.tv_created.setText(blogmodel.getCreateat());
            holder.total_comment.setText(blogmodel.getTotal_comment() + " comments ");

            holder.total_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Blogs) activity).ShowCommentDialogue(holder.getAdapterPosition());
                }
            });


        }


        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            //  isme particular position kaise nikalo
            @Override
            public void onClick(View v) {
                ((Blogs) activity).ShowCustomDialogue(holder.getAdapterPosition());
            }
        });

    }


    @Override
    public int getItemCount() {
        return list_blogs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_title, tv_description, btn_comment, tv_created, total_comment;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_descrition);

            btn_comment = (TextView) itemView.findViewById(R.id.btn_comment);
            tv_created = (TextView) itemView.findViewById(R.id.tv_created);
            total_comment = (TextView) itemView.findViewById(R.id.total_comment);

        }


    }
}
