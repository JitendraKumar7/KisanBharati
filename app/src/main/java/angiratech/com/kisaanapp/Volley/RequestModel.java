package angiratech.com.kisaanapp.Volley;

public class RequestModel {

    String Key;
    String Value;

    public RequestModel(String key , String value) {
        this.Key = key;
        this.Value = value;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

}
