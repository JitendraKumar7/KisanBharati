package angiratech.com.kisaanapp.Utility;

public interface AppConstant {

    //Dialog Messages
    String InternetMsg = "Please turn on Internet";
    String InternetTitle = "Internet";
    String DialogPosTitle = "Ok";
    int SPLASHSCREEN_DELAY = 1500;
    int GOOGLE_SIGIN = 100;
    int RESULT_LOAD_IMAGE = 200;
    int DIALOG_INDEX_CLICK_IMAGE = 1;
    int DIALOG_INDEX_PICK_IMAGE = 0;
    String INTERNET_NOT_FOUND = "Internet Connection not found";
    String NATIVE_SIGNUPTYPE = "0";
    String SOCIAL_SIGNUPTYPE_GOOGLE = "1";
    String SOCIAL_SIGNUPTYPE_FACEBOOK = "2";
    // ALL ERROR CODE
    String ERROR_CODE_FAILURE = "0";
    String ERROR_CODE_SUCCESS = "1";
    String ERROR_CODE_OTPNOTVERIFIED = "2";
    String ERROR_CODE_TOKEN_MISMATCH = "3";
    String ERROR_CODE_LOGOUT = "4";
    public static String KEY_SUCCESS = "success";
    public static String KEY_MSG = "statusmessage";
    String KEY_CODE = "statuscode";
    String KEY_SUCCESS_1 = "1";
    String KEY_FAILURE = "0";
    String KEY_STATE = "state";
    String KEY_COMMODITIES = "commodities";
    String KEY_DISTRICT = "district";
    String KEY_CATEGORY = "category";

    String BASE_URL = "http://babsapp.com/babapp/api.php?";
    String BASE_URL_REGISTER = "http://www.angiratech.com/kisaan/api.php?";
    String BASE_URL_KISAN = "http://www.angiratech.com/kisaan/api/login?";
    String BASE_URL_CROP = "http://www.angiratech.com/kisaan/api?";

    String URL_ALLSTATES = "http://babsapp.com/babapp/api.php?" + "action=state";
    String URL_CROPLIST = "http://www.angiratech.com/kisaan/api/crops";
    String URL_ALLCOMMODITIES = BASE_URL + "action=get_category";
    String URL_DISTRICT = "http://babsapp.com/babapp/api.php?" + "action=district&state_id=";
//    String URL_LOGIN = BASE_URL + "action=login_kisaanapp";

    String KEY_USERNAME = "userName";
    String KEY_EMAILID = "emailId";
    String KEY_PASSWORD = "password";
    String KEY_PHONENUMBER = "phoneNumber";
    String KEY_IMAGEFILE = "file";
    String KEY_SOCIALACCESSTOKEN = "socialAccessToken";
    String KEY_GCMTOKEN = "gcmToken";
    String KEY_DEVICEID = "device_id";
    String KEY_SIGNUPTYPE = "signUpType";
    String KEY_YANTRA_LIST = "yantralist";
    String KEY_SCHEME_LIST = "yojana";
    String KEY_DISTRICT_LIST = "districtlist";
    String KEY_STATE_LIST = "statename";
    String KEY_HELP_LIST = "helpcenter";
    String KEY_MARKET = "marketing";
    String KEY_DEALER_LIST = "dealerlist";
    String KEY_BLOG_LIST = "bloglist";
    String KEYCOMMENT = "comment";

    String KEY_NAME = "name";
    String KEY_ADDRESS = "address";
    String KEY_CITY = "city";
    String KEY_EMAIL = "email";
    String KEY_MOBILE = "mobile";
    String KEY_DIVICEID = "device_id";
    String KEY_EMICODE = "emi_code";
    String KEY_DEVICETYPE = "device_type";
    String KEY_PUSHTOKEN = "push_token";
    String KEY_COMMODITITID = "commodity_ids";

    String KEY_ACTION = "action";
    String KEY_ACTIONREGISTER = "user_register";
    String KEY_ACTIONLOGIN = "login";
    String KEY_ACTIONNEWS = "newslist";
    String KEY_ACTIONCROPLIST = "croplist";
    String KEY_ACTIONSEEDLIST = "seedlist";
    String KEY_ACTIONUSERCATEGORY = "user_category";
    String KEY_ACTIONNEWSREAD = "news_read_update";
    String KEY_ACTIONSUBSCRIPTION = "get_user_plan";
    String KEY_DEALER_REGISTER = "register_dealer";
    String KEY_VALIDATE_USER = "current_status";

    String KEY_SENDOTP = "send_otp";
    String KEY_VERIFYOTP = "verify_otp";
    String KEY_USERID = "user_id";
    String KEY_OTPCODE = "otp_code";
    String KEY_NEWSID = "news_id";

    String KEY_LASTNEWSID = "last_news_id";
    String KEY_OFFSET = "offset";
    String KEY_LENGTH = "length";
    String KEY_CATEGORYID = "category_id";
    String TABLE_NEWS = "table_news";

    public static String PHONENUMBER = "9910963354";
    public static int TIMEOUT = 30 * 1;

//     SMS provider identification
//     It should match with your SMS gateway origin
//     You can use  MSGIND, TESTER and ALERTS as sender ID
//     If you want custom sender Id, approve MSG91 to get one

    String TAG = "Kisan";
    String SMS_ORIGIN = "MD-BHRTAG";

    // Tag
    String TAG_STATE = "statelist";
    String TAG_CROP_lIST = "croplist";
    String TAG_CITY = "statelist";
    String TAG_DISTRICT = "district";
    String TAG_COMMODITIES = "commodities";
    String TAG_Register = "register";
    String TAG_Login = "login";
    String TAG_News = "get_kisaanapp_news";
    String TAG_Subscription = "subscription";
    String TAG_USERCATEGORY = "UserCategory";
    String TAG_USERNEWSUPDATE = "UserNewsUpdate";
    String TAG_SENDOTP = "SendOtp";
    String TAG_VERIFYOTP = "verifyotp";
    String TAG_BANNER = "banner";
    String MAX_TEMP = "maxTemp";
    String MIN_TEMP = "minTemp";

    String KEY_FEEDBACK = "feedback";
    String KEY_UID = "uid";
    String KEY_FEED = "feed";

}
