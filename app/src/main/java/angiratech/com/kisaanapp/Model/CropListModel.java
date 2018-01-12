package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/16/2017.
 */

public class CropListModel implements Parcelable {
    public CropListModel() {
    }

    protected CropListModel(Parcel in) {
        Info1 = in.readString();
        Info2 = in.readString();
        Info3 = in.readString();
        Info4 = in.readString();
        Info5 = in.readString();
        id = in.readString();
        Name = in.readString();
        Image = in.readString();
    }

    public static final Creator<CropListModel> CREATOR = new Creator<CropListModel>() {
        @Override
        public CropListModel createFromParcel(Parcel in) {
            return new CropListModel(in);
        }

        @Override
        public CropListModel[] newArray(int size) {
            return new CropListModel[size];
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


    public String getInfo1() {
        return Info1;
    }

    public void setInfo1(String info1) {
        Info1 = info1;
    }

    public String getInfo2() {
        return Info2;
    }

    public void setInfo2(String info2) {
        Info2 = info2;
    }

    public String getInfo3() {
        return Info3;
    }

    public void setInfo3(String info3) {
        Info3 = info3;
    }

    public String getInfo4() {
        return Info4;
    }

    public void setInfo4(String info4) {
        Info4 = info4;
    }

    public String Info1 = "";
    public String Info2 = "";
    public String Info3 = "";
    public String Info4 = "";
    public String Info5 = "";

    public static Creator<CropListModel> getCREATOR() {
        return CREATOR;
    }

    public String getInfo5() {
        return Info5;
    }

    public void setInfo5(String info5) {
        Info5 = info5;
    }

    public String id = "";
    public String Name = "";
    public String Image = "";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Info1);
        dest.writeString(Info2);
        dest.writeString(Info3);
        dest.writeString(Info4);
        dest.writeString(Info5);
        dest.writeString(id);
        dest.writeString(Name);
        dest.writeString(Image);
    }
}
