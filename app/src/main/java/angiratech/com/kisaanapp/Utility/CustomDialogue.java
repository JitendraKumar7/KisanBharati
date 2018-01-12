package angiratech.com.kisaanapp.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.angiratech.kisaanapp.R;

/**
 * Created by NZT-59 on 5/23/2017.
 */

public class CustomDialogue extends Dialog implements View.OnClickListener {

    public Activity activity;
    public Dialog d;
    public Button yes, no;
    public CustomDialogue(Activity act) {

        super(act);
        this.activity=act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

    }

    @Override
    public void onClick(View v) {

    }
}
