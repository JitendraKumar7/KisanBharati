package angiratech.com.kisaanapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.angiratech.kisaanapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import angiratech.com.kisaanapp.Adapter.BlogsAdapter;
import angiratech.com.kisaanapp.Adapter.CommentListAdapter;
import angiratech.com.kisaanapp.Dialogs.MyDialog;
import angiratech.com.kisaanapp.Model.BlogListModel;
import angiratech.com.kisaanapp.Retrofit.Webservice;
import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.CheckInternet;
import angiratech.com.kisaanapp.Utility.MyToast;

/**
 * Created by NZT-59 on 6/2/2017.
 */

public class Blogs extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;

    private LinearLayoutManager mLayoutManager;
    BlogsAdapter blogsAdapter;
    private ArrayList<BlogListModel> blog_list = new ArrayList<>();
    private ArrayList<BlogListModel> comment_list = new ArrayList<>();
    private TextView toolbarName;
    private Toolbar toolbar;
    private ImageView img_back;
    private String id;
    private ImageView toolbarImg;
    private MyDialog myDialog;
    private String Comment = "";
    private String user_id = "";
    private MySession mySession;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_blogs);
        mySession = new MySession(Blogs.this);
        init();
        toolbar();
        getBlogList();


    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {


            toolbarName = (TextView) toolbar.findViewById(R.id.toolbarame);
            toolbarName.setVisibility(View.VISIBLE);
            toolbarName.setText("Blogs");
            img_back = (ImageView) toolbar.findViewById(R.id.img_back);
            img_back.setVisibility(View.VISIBLE);
            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            toolbarImg = (ImageView) toolbar.findViewById(R.id.toolbarImg);
            toolbarImg.setVisibility(View.VISIBLE);
            toolbarImg.setOnClickListener(this);

            setSupportActionBar(toolbar);
        }

    }

    private void init() {
        myDialog = new MyDialog(Blogs.this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_blogs);
        mLayoutManager = new LinearLayoutManager(Blogs.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);


    }


    private void getBlogList() {
        if (CheckInternet.isNetwork(this)) {
            myDialog.ShowProgressDialogue();
            Webservice.GetBlogList(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    myDialog.CancelProgressDialog();
                    if (response != null) {
                        Log.e("BLOG LIST", response.toString());
                        try {
                            if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                                JSONArray blogsListJsonArray = response.optJSONArray("bloglist");
                                if (blogsListJsonArray.length() > 0) {
                                    for (int i = 0; i < blogsListJsonArray.length(); i++) {
                                        JSONObject blogsDataObject = blogsListJsonArray.optJSONObject(i);
                                        BlogListModel blogListModel = new BlogListModel();
                                        blogListModel.setId(blogsDataObject.optString("id"));
                                        blogListModel.setUser(blogsDataObject.optString("user"));
                                        blogListModel.setTitle(blogsDataObject.optString("title"));
                                        blogListModel.setDescription(blogsDataObject.optString("description"));
                                        blogListModel.setCreateat(blogsDataObject.optString("created_date"));
                                        blogListModel.setComment(blogsDataObject.optString("comment"));
                                        blogListModel.setTotal_comment(blogsDataObject.optString("total_comment"));
                                        blog_list.add(blogListModel);
                                        if (blogsDataObject.getJSONArray("comment") != null) {
                                            JSONArray commentArray = blogsDataObject.getJSONArray("comment");
                                            if (commentArray.length() > 0) {
                                                for (int j = 0; j < commentArray.length(); j++) {
                                                    JSONObject CommentDataObject = commentArray.optJSONObject(j);
                                                    blogListModel.setComment(CommentDataObject.optString("description"));
                                                    comment_list.add(blogListModel);
                                                }

                                            }

                                        }
                                        blogsAdapter = new BlogsAdapter(Blogs.this, blog_list);
                                        recyclerView.setAdapter(blogsAdapter);


                                    }


                                } else {
                                    MyToast.Lmsg(Blogs.this, response.toString());
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("RECYLER", e.toString());
                        }

                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });

        }


    }

    public void ShowCustomDialogue(final int position) {
        BlogListModel blogListModel = blog_list.get(position);
        id = blog_list.get(position).getId();
        user_id = mySession.getUserId();


        final Dialog dialog = new Dialog(Blogs.this);
        dialog.setContentView(R.layout.custom_dialog_blogs);
        final EditText editText = (EditText) dialog.findViewById(R.id.edt_comment);
        Button send = (Button) dialog.findViewById(R.id.btn_save);
        Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Comment", Toast.LENGTH_SHORT).show();
                } else {

                    Comment = editText.getText().toString();


                    getComment(user_id, id, Comment);
                }
                dialog.dismiss();

            }

        });
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


    public void ShowCommentDialogue(final int position) {
        final Dialog dialog = new Dialog(Blogs.this);
        dialog.setContentView(R.layout.custom_comment_blogs);
        RecyclerView rv_comments = (RecyclerView) dialog.findViewById(R.id.rv_comments);
        mLayoutManager = new LinearLayoutManager(Blogs.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_comments.setLayoutManager(mLayoutManager);
        CommentListAdapter adapter = new CommentListAdapter(Blogs.this, comment_list);
        rv_comments.setAdapter(adapter);


        Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.FILL_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);


        dialog.show();
    }

    private void getComment(String user_id, String id, String comment) {
        myDialog.ShowProgressDialogue();

        Webservice.GetComment(user_id, id, comment, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                myDialog.CancelProgressDialog();
                if (response != null) {
                    Log.e("BLOG LIST", response.toString());
                    try {
                        if (response.optString("statuscode").equalsIgnoreCase("1") && response.optString("statusmessage").equalsIgnoreCase("OK")) {
                            JSONArray blogsListJsonArray = response.optJSONArray("bloglist");
                            if (blogsListJsonArray.length() > 0) {
                                for (int i = 0; i < blogsListJsonArray.length(); i++) {
                                    JSONObject blogsDataObject = blogsListJsonArray.optJSONObject(i);
                                }
                            } else {
                                MyToast.Lmsg(Blogs.this, response.toString());
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbarImg:
                Intent notific = new Intent(Blogs.this, Notifications.class);
                startActivity(notific);
                break;
        }
    }
}
