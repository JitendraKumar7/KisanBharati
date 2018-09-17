package angiratech.com.kisaanapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angiratech.kisaanapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import angiratech.com.kisaanapp.Model.BannerModel;
import angiratech.com.kisaanapp.Model.CombindMyList;
import angiratech.com.kisaanapp.Model.NewsDetailsModel;

/**
 * Created by SONY on 30-04-2017.
 */

public class NewsListAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final DisplayImageOptions imgOptions;
    private List<NewsDetailsModel> list;
    private ArrayList<CombindMyList> combindlist = new ArrayList<>();
    private Activity activity;
    private Context context;
    private ImageLoader imageLoader;
    private boolean isLoading;
    private RecyclerView mRecyclerView;
    int bannersizes;
    int x = 0;
    private ArrayList<BannerModel> bannerModelList = new ArrayList<>();
    int bannersize;

    public NewsListAdaptor(ArrayList<CombindMyList> list, Activity activity, RecyclerView mRecyclerView, int bannersizes) {
        this.combindlist = list;
        this.activity = activity;
        imageLoader = ImageLoader.getInstance();
        imageLoader.handleSlowNetwork(true);
        this.bannersizes = bannersizes;


        imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.logo_header)
                .build();
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        EventListHolder dataObjectHolder = new EventListHolder(view);
        return dataObjectHolder;
}

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final EventListHolder eventListHolder = (EventListHolder) holder;

        CombindMyList combindMyList = (CombindMyList) combindlist.get(position);
        try {
            if ((position % 5 == 0) && (position != 0)) {
                if (combindMyList.getBanner() == null) {
                    imageLoader.displayImage("", eventListHolder.image_banner, imgOptions, null);
                } else {
                    imageLoader.displayImage(combindMyList.getBanner().getImage_banner(), eventListHolder.image_banner, imgOptions, null);
                }
                if (combindMyList.getBanner() == null) {
                    newsLayout(eventListHolder, combindMyList);
                } else {
                    bannerLayout(eventListHolder, combindMyList);
                }
            } else {
                newsLayout(eventListHolder, combindMyList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bannerLayout(EventListHolder eventListHolder, CombindMyList combindMyList) {
        eventListHolder.tv_title.setText(combindMyList.getBanner().getBanner_title());
        eventListHolder.tv_description.setText(combindMyList.getBanner().getBanner_description());
        eventListHolder.card_view.setVisibility(View.VISIBLE);
        eventListHolder.rel_1.setVisibility(View.GONE);
    }

    private void newsLayout(EventListHolder eventListHolder, CombindMyList combindMyList) {
        eventListHolder.categoryNameEn.setText(combindMyList.getNews().getCategoryNameEn());
        eventListHolder.categoryNameHi.setText(combindMyList.getNews().getCategoryNameHi());
        eventListHolder.categoryHeading.setText(combindMyList.getNews().getHeading());
        // detailsModel.getCreatedAt()
        String newdateformat = ChangeFormateDate(combindMyList.getNews().getCreatedAt());
        eventListHolder.newsCreatedAt.setText(newdateformat);
        eventListHolder.categoryDescription.setText(Html.fromHtml(combindMyList.getNews().getDescription()).toString());
        eventListHolder.newsDetailWebView.getSettings().setJavaScriptEnabled(true);
        eventListHolder.newsDetailWebView.setBackgroundColor(Color.TRANSPARENT);
        eventListHolder.newsDetailWebView.loadDataWithBaseURL("", combindMyList.getNews().getDescription(), "text/html", "UTF-8", "");


        eventListHolder.newsDetailWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        eventListHolder.newsDetailWebView.setLongClickable(false);


        if (combindMyList.getNews().getImage().equalsIgnoreCase("")) {
            eventListHolder.ImgPhoto.setImageResource(R.drawable.logo_header);
        } else {
            imageLoader.displayImage(combindMyList.getNews().getImage(), eventListHolder.ImgPhoto, imgOptions, null);
        }
        eventListHolder.card_view.setVisibility(View.GONE);
        eventListHolder.rel_1.setVisibility(View.VISIBLE);
    }

    private String ChangeFormateDate(String createdAt) {
        StringTokenizer tk = new StringTokenizer(createdAt);
        String date = tk.nextToken();  // <---  yyyy-mm-dd
        String time = tk.nextToken();

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String dateoutput = "dd-MMMM-yyyy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(dateoutput, new Locale("hi", "IN"));

        Date dates = null;
        String str = null;
        try {
            dates = inputFormat.parse(createdAt);
            str = outputFormat.format(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    String timeoutput;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(time);
            System.out.println("time==s"+dateObj);
            if(dateObj.getHours()>12){
              //    evening
                timeoutput=new SimpleDateFormat("K:mm").format(dateObj);
                timeoutput=timeoutput+" सायंकाल";
            }else{
                timeoutput=new SimpleDateFormat("K:mm").format(dateObj);
                timeoutput=timeoutput+" प्रातःकाल";
            }
            str=str+" "+timeoutput;
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        return str;
    }

    public void setItemList(ArrayList<CombindMyList> list) {
        this.combindlist = list;
        notifyDataSetChanged();
    }

    public List<NewsDetailsModel> getItems() {
        return list;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return combindlist.size();
    }

    public class EventListHolder extends RecyclerView.ViewHolder {
        private ImageView ImgPhoto, image_banner;
        private WebView newsDetailWebView;
        private RelativeLayout rel_1;
        private TextView categoryNameEn, categoryNameHi;
        private TextView categoryHeading, newsCreatedAt, categoryDescription, tv_title, tv_description;
        private CardView card_view;

        public EventListHolder(View view) {
            super(view);
            ImgPhoto = (ImageView) view.findViewById(R.id.ImgPhoto);
            image_banner = (ImageView) view.findViewById(R.id.image_banner);
            categoryNameEn = (TextView) view.findViewById(R.id.categoryNameEn);
            categoryNameHi = (TextView) view.findViewById(R.id.categoryNameHi);
            categoryHeading = (TextView) view.findViewById(R.id.categoryHeading);
            newsCreatedAt = (TextView) view.findViewById(R.id.newsCreatedAt);
            categoryDescription = (TextView) view.findViewById(R.id.categoryDescription);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            rel_1 = (RelativeLayout) view.findViewById(R.id.rel_1);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            newsDetailWebView = (WebView) view.findViewById(R.id.categoryDescriptionWebView);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
