package angiratech.com.kisaanapp.Youtube;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.angiratech.kisaanapp.R;

import angiratech.com.kisaanapp.Adapter.YouTubeAdapter;

/**
 * Created by NZT-59 on 6/30/2017.
 */

public class YouTube extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_list_item);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        YouTubeAdapter adapter=new YouTubeAdapter(YouTube.this);
        recyclerView.setAdapter(adapter);
    }
}
