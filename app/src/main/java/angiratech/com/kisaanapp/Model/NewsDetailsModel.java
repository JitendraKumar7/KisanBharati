package angiratech.com.kisaanapp.Model;

/**
 * Created by SONY on 30-04-2017.
 */

public class NewsDetailsModel {
    public String userId;
    public String newsId;
    public String heading;
    public String image;
    public String description;
    public String createdAt;
    public String readStatus;
    public String actualNewsTime;
    public String categoryId;
    public String categoryNameEn;
    public String categoryNameHi;

    public NewsDetailsModel(String userId,String newsId, String heading,String image,
                            String description,String createdAt,String readStatus,
                            String actualNewsTime,String categoryId,String categoryNameEn,String categoryNameHi) {
        this.userId = userId;
        this.newsId = newsId;
        this.heading = heading;
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.readStatus = readStatus;
        this.actualNewsTime = actualNewsTime;
        this.categoryId = categoryId;
        this.categoryNameEn = categoryNameEn;
        this.categoryNameHi = categoryNameHi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getActualNewsTime() {
        return actualNewsTime;
    }

    public void setActualNewsTime(String actualNewsTime) {
        this.actualNewsTime = actualNewsTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNameEn() {
        return categoryNameEn==null?"":categoryNameEn;
    }

    public void setCategoryNameEn(String categoryNameEn) {
        this.categoryNameEn = categoryNameEn==null?"":categoryNameEn;
    }

    public String getCategoryNameHi() {
        return categoryNameHi;
    }

    public void setCategoryNameHi(String categoryNameHi) {
        this.categoryNameHi = categoryNameHi;
    }

    public NewsDetailsModel(){

    }
}
