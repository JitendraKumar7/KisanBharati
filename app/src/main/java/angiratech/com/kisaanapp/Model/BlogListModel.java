package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 6/2/2017.
 */

public class BlogListModel implements Parcelable {
   public BlogListModel(){
   }

   public BlogListModel(String id,String username, String title, String description,String createat,String Comment,String total_comment){
       this.id=id;
       this.user=username;
       this.title=title;
       this.description=description;
       this.createat=description;
       this.Comment=Comment;
       this.total_comment=total_comment;

   }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Creator<BlogListModel> getCREATOR() {
        return CREATOR;
    }

    private String id = "";
    private String user = "";
    private String title = "";
    private String description = "";
    private String createat = "";


    public String getTotal_comment() {
        return total_comment;
    }

    public void setTotal_comment(String total_comment) {
        this.total_comment = total_comment;
    }

    private String total_comment = "";

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    private String Comment = "";


    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    protected BlogListModel(Parcel in) {
    }

    public static final Creator<BlogListModel> CREATOR = new Creator<BlogListModel>() {
        @Override
        public BlogListModel createFromParcel(Parcel in) {
            return new BlogListModel(in);
        }

        @Override
        public BlogListModel[] newArray(int size) {
            return new BlogListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
