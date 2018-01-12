package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angiratech.kisaanapp.R;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Model.BlogListModel;

/**
 * Created by NZT-59 on 6/19/2017.
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    private ArrayList<BlogListModel> list_comments = new ArrayList<>();
    private Activity activity;

    public CommentListAdapter(Activity activity, ArrayList<BlogListModel> model_list) {
        this.list_comments = model_list;
        this.activity = activity;

    }

    @Override
    public CommentListAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_comments, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.CommentViewHolder holder, int position) {
        BlogListModel blogmodel = list_comments.get(position);

        if (blogmodel != null) {
            holder.tv_comment.setText(Html.fromHtml(blogmodel.getComment()));
            // holder.tv_comment.setText("", blogmodel.getComment(), "text/html", "UTF-8", "");
        }
    }

    @Override
    public int getItemCount() {
        return list_comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_comment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);

        }
    }
}



