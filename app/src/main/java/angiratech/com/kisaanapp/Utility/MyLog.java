package angiratech.com.kisaanapp.Utility;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MyLog {

    public static void ShowLog(String Tag, String Msg) {
        Log.d(Tag, Msg);
    }

    public static void ShowELog(String Tag, String Msg) {
        Log.e(Tag, Msg);
    }

    public static void ShowILog(String Tag, String Msg) {
        Log.i(Tag, Msg);
    }

    public static void setSnackBar(View coordinatorLayout, String snackTitle) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, snackTitle, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
