package angiratech.com.kisaanapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class BeanWeather implements Parcelable {

	String minTemp, maxTemp, dateTime, cloud;
	Date date;


	public BeanWeather(String minTemp, String maxTemp, String dateTime) {
		super();
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.dateTime = dateTime;
		this.date = date;
	}

	protected BeanWeather(Parcel in) {
		minTemp = in.readString();
		maxTemp = in.readString();
		dateTime = in.readString();
		cloud = in.readString();
	}

	public static final Creator<BeanWeather> CREATOR = new Creator<BeanWeather>() {
		@Override
		public BeanWeather createFromParcel(Parcel in) {
			return new BeanWeather(in);
		}

		@Override
		public BeanWeather[] newArray(int size) {
			return new BeanWeather[size];
		}
	};

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BeanWeather))
			return false;
		else {
			BeanWeather obj = (BeanWeather) o;
			if (obj.getDate().getDate() == (date.getDate()))
				return true;
			else
				return false;
		}

	}

	public String getCloud() {
		return cloud;
	}

	public void setCloud(String cloud) {
		this.cloud = cloud;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public BeanWeather() {
		super();
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	public void setDate(String date) {
		this.dateTime = date;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(minTemp);
		dest.writeString(maxTemp);
		dest.writeString(dateTime);
		dest.writeString(cloud);
	}
}
