package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class HelpCenterModel implements Parcelable {
    public HelpCenterModel(){

    }

    protected HelpCenterModel(Parcel in) {
        id = in.readString();
        heading = in.readString();
        descrption = in.readString();
    }

    public static final Creator<HelpCenterModel> CREATOR = new Creator<HelpCenterModel>() {
        @Override
        public HelpCenterModel createFromParcel(Parcel in) {
            return new HelpCenterModel(in);
        }

        @Override
        public HelpCenterModel[] newArray(int size) {
            return new HelpCenterModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String id="";
    public String heading="";
    public String descrption="";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(heading);
        dest.writeString(descrption);
    }
}
