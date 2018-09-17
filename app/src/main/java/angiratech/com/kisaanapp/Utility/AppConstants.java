package angiratech.com.kisaanapp.Utility;

public class AppConstants {

    //Dialog Messages
    public static final String InternetMsg = "Please turn on Internet";
    public static final String InternetTitle = "Internet";
    public static final String DialogPosTitle = "Ok";
    public static final int SPLASHSCREEN_DELAY = 1500;
    public static final int GOOGLE_SIGIN = 100;
    public static final int RESULT_LOAD_IMAGE = 200;
    public static final int DIALOG_INDEX_CLICK_IMAGE = 1;
    public static final int DIALOG_INDEX_PICK_IMAGE = 0;
    public static final String INTERNET_NOT_FOUND = "Internet Connection not found";
    public static final String NATIVE_SIGNUPTYPE = "0";
    public static final String SOCIAL_SIGNUPTYPE_GOOGLE = "1";
    public static final String SOCIAL_SIGNUPTYPE_FACEBOOK = "2";
    // ALL ERROR CODE
    public static final String ERROR_CODE_FAILURE = "0";
    public static final String ERROR_CODE_SUCCESS = "1";
    public static final String ERROR_CODE_OTPNOTVERIFIED = "2";
    public static final String ERROR_CODE_TOKEN_MISMATCH = "3";
    public static final String ERROR_CODE_LOGOUT = "4";
    public static String KEY_SUCCESS = "success";
    public static String KEY_MSG = "statusmessage";
    public static final String KEY_CODE = "statuscode";
    public static final String KEY_SUCCESS_1 = "1";
    public static final String KEY_FAILURE = "0";
    public static final String KEY_STATE = "state";
    public static final String KEY_COMMODITIES = "commodities";
    public static final String KEY_DISTRICT = "district";
    public static final String KEY_CATEGORY = "category";

    public static final String BASE_URL = "http://babsapp.com/babapp/api.php?";
    public static final String BASE_URL_REGISTER = "http://www.angiratech.com/kisaan/api.php?";
    public static final String BASE_URL_KISAN = "http://www.angiratech.com/kisaan/api/login?";
    public static final String BASE_URL_CROP = "http://www.angiratech.com/kisaan/api?";

    public static final String URL_ALLSTATES = "http://babsapp.com/babapp/api.php?"+"action=state";
    public static final String URL_CROPLIST= "http://www.angiratech.com/kisaan/api/crops";
    public static final String URL_ALLCOMMODITIES = BASE_URL + "action=get_category";
    public static final String URL_DISTRICT = "http://babsapp.com/babapp/api.php?"+"action=district&state_id=";
//    public static final String URL_LOGIN = BASE_URL + "action=login_kisaanapp";

    public static final String KEY_USERNAME = "userName";
    public static final String KEY_EMAILID = "emailId";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PHONENUMBER = "phoneNumber";
    public static final String KEY_IMAGEFILE = "file";
    public static final String KEY_SOCIALACCESSTOKEN = "socialAccessToken";
    public static final String KEY_GCMTOKEN = "gcmToken";
    public static final String KEY_DEVICEID = "device_id";
    public static final String KEY_SIGNUPTYPE = "signUpType";
    public static final String KEY_YANTRA_LIST = "yantralist";
    public static final String KEY_SCHEME_LIST = "yojana";
    public static final String KEY_DISTRICT_LIST = "districtlist";
    public static final String KEY_STATE_LIST = "statename";
    public static final String KEY_HELP_LIST = "helpcenter";
    public static final String KEY_MARKET = "marketing";
    public static final String KEY_DEALER_LIST = "dealerlist";
    public static final String KEY_BLOG_LIST = "bloglist";
    public static final String KEYCOMMENT = "comment";

    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_DIVICEID = "device_id";
    public static final String KEY_EMICODE = "emi_code";
    public static final String KEY_DEVICETYPE = "device_type";
    public static final String KEY_PUSHTOKEN = "push_token";
    public static final String KEY_COMMODITITID = "commodity_ids";

    public static final String KEY_ACTION = "action";
    public static final String KEY_ACTIONREGISTER = "user_register";
    public static final String KEY_ACTIONLOGIN = "login";
    public static final String KEY_ACTIONNEWS = "newslist";
    public static final String KEY_ACTIONCROPLIST = "croplist";
    public static final String KEY_ACTIONSEEDLIST = "seedlist";
    public static final String KEY_ACTIONUSERCATEGORY = "user_category";
    public static final String KEY_ACTIONNEWSREAD = "news_read_update";
    public static final String KEY_ACTIONSUBSCRIPTION = "get_user_plan";
    public static final String KEY_DEALER_REGISTER = "register_dealer";
    public static final String KEY_VALIDATE_USER= "current_status";

    public static final String KEY_SENDOTP = "send_otp";
    public static final String KEY_VERIFYOTP = "verify_otp";
    public static final String KEY_USERID = "user_id";
    public static final String KEY_OTPCODE = "otp_code";
    public static final String KEY_NEWSID = "news_id";

    public static final String KEY_LASTNEWSID = "last_news_id";
    public static final String KEY_OFFSET = "offset";
    public static final String KEY_LENGTH = "length";
    public static final String KEY_CATEGORYID = "category_id";
    public static final String TABLE_NEWS = "table_news";

    public static String PHONENUMBER = "9910963354";
    public static int TIMEOUT = 30 * 1;

//     SMS provider identification
//     It should match with your SMS gateway origin
//     You can use  MSGIND, TESTER and ALERTS as sender ID
//     If you want custom sender Id, approve MSG91 to get one

    public static final String SMS_ORIGIN = "MD-BHRTAG";

    // Tag
    public static final String TAG_STATE = "statelist";
    public static final String TAG_CROP_lIST = "croplist";
    public static final String TAG_CITY = "statelist";
    public static final String TAG_DISTRICT = "district";
    public static final String TAG_COMMODITIES = "commodities";
    public static final String TAG_Register = "register";
    public static final String TAG_Login = "login";
    public static final String TAG_News = "get_kisaanapp_news";
    public static final String TAG_Subscription = "subscription";
    public static final String TAG_USERCATEGORY = "UserCategory";
    public static final String TAG_USERNEWSUPDATE = "UserNewsUpdate";
    public static final String TAG_SENDOTP = "SendOtp";
    public static final String TAG_VERIFYOTP = "verifyotp";
    public static final String TAG_BANNER = "banner";
    public static final String MAX_TEMP = "maxTemp";
    public static final String MIN_TEMP = "minTemp";

    public static final String KEY_FEEDBACK = "feedback";
    public static final String KEY_UID = "uid";
    public static final String KEY_FEED = "feed";

}
