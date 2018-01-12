package angiratech.com.kisaanapp.Model;

/**
 * Created by NZT-59 on 5/25/2017.
 */

public class MarketModel  {
    public String id="";
    public String heading="";
    public String descrption="";

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String Image="";

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
}
