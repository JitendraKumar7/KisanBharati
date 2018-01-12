package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class SeedListModel implements Parcelable {
    public SeedListModel(){

    }
    protected SeedListModel(Parcel in) {
        id = in.readString();
        Name = in.readString();
        Image = in.readString();
        infomation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Name);
        dest.writeString(Image);
        dest.writeString(infomation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SeedListModel> CREATOR = new Creator<SeedListModel>() {
        @Override
        public SeedListModel createFromParcel(Parcel in) {
            return new SeedListModel(in);
        }

        @Override
        public SeedListModel[] newArray(int size) {
            return new SeedListModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public String id="";
    public String Name="";
    public String Image="";
    public String infomation="";
}
