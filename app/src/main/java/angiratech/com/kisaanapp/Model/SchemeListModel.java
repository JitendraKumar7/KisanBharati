package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/26/2017.
 */

public class SchemeListModel implements Parcelable {
    public SchemeListModel(){

    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

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

    public static Creator<SchemeListModel> getCREATOR() {
        return CREATOR;
    }

    public String Description="";
    public String id="";
    public String Name="";

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String Image="";


    protected SchemeListModel(Parcel in) {
        Description = in.readString();
        id = in.readString();
        Name = in.readString();
        Image = in.readString();
    }

    public static final Creator<SchemeListModel> CREATOR = new Creator<SchemeListModel>() {
        @Override
        public SchemeListModel createFromParcel(Parcel in) {
            return new SchemeListModel(in);
        }

        @Override
        public SchemeListModel[] newArray(int size) {
            return new SchemeListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Description);
        dest.writeString(id);
        dest.writeString(Name);
        dest.writeString(Image);
    }
}
