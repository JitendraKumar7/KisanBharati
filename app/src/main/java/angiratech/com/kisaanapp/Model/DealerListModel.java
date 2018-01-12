package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NZT-59 on 5/24/2017.
 */

public class DealerListModel implements Parcelable {

    public DealerListModel() {

    }

    protected DealerListModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        mobile = in.readString();
        address = in.readString();
        firmName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeString(address);
        dest.writeString(firmName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DealerListModel> CREATOR = new Creator<DealerListModel>() {
        @Override
        public DealerListModel createFromParcel(Parcel in) {
            return new DealerListModel(in);
        }

        @Override
        public DealerListModel[] newArray(int size) {
            return new DealerListModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String id = "";
    public String name = "";
    public String mobile = "";
    public String address = "";
    public String firmName = "";


    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }


}
