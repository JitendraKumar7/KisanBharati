package angiratech.com.kisaanapp.Model;

/**
 * Created by Sony on 09-11-2017.
 */

public class CombindMyList {


    private BannerModel banner;
    private NewsDetailsModel news;

    public BannerModel getBanner() {
        return banner;
    }
    public NewsDetailsModel getNews() {
        return news;
    }

    public void setNews(NewsDetailsModel news) {
        this.news = news;
    }

    public void setBanner(BannerModel banner) {
        this.banner = banner;
    }
}
