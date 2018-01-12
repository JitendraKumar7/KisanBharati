package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/22/2017.
 */

public class YantraModel implements Parcelable {

   public YantraModel(){

    }
    public String id="";
    public String Name="";
    public String Image="";
    public String Description="";

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static Creator<YantraModel> getCREATOR() {
        return CREATOR;
    }

    protected YantraModel(Parcel in) {
        id = in.readString();
        Name = in.readString();
        Image = in.readString();
        Description = in.readString();
    }

    public static final Creator<YantraModel> CREATOR = new Creator<YantraModel>() {
        @Override
        public YantraModel createFromParcel(Parcel in) {
            return new YantraModel(in);
        }

        @Override
        public YantraModel[] newArray(int size) {
            return new YantraModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Name);
        dest.writeString(Image);
        dest.writeString(Description);
    }
}
