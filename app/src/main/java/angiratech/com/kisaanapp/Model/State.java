package angiratech.com.kisaanapp.Model;

/**
 * Created by SONY on 30-04-2017.
 */

public class State {
    private String stateId;
    private String stateName;

    @Override
    public String toString() {
        return stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
