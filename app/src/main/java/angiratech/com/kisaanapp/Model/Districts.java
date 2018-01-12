package angiratech.com.kisaanapp.Model;

/**
 * Created by SONY on 30-04-2017.
 */

public class Districts {
    private String districtsId;
    private String districtsName;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    private String State;

    public Districts() {
    }

    @Override
    public String toString() {
        return districtsName;
    }

    public String getDistrictsId() {
        return districtsId;
    }

    public void setDistrictsId(String districtsId) {
        this.districtsId = districtsId;
    }

    public String getDistrictsName() {
        return districtsName;
    }

    public void setDistrictsName(String districtsName) {
        this.districtsName = districtsName;
    }
}
