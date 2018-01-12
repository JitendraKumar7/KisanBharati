package angiratech.com.kisaanapp.Utility;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    public static void Lmsg(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_LONG).show();
    }

    public static void Smsg(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }
}
