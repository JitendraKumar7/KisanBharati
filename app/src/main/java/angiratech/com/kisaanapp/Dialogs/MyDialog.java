package angiratech.com.kisaanapp.Dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;

import com.angiratech.kisaanapp.R;

import angiratech.com.kisaanapp.colorDialogue.PromptDialog;

/**
 * Created by SONY on 29-04-2017.
 */

public class MyDialog {
    private ProgressDialog progressDialog;
    Context mContext;
    private ProgressBar mProgressbar;


    public MyDialog(Context context) {
        this.mContext = context;
        progressDialog = new ProgressDialog(context, 0);

    }

    public void ShowProgressDialogue() {
        try {
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.custom_progress_view);
            progressDialog.setCancelable(true);
            progressDialog.setIndeterminate(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProgress(int progress) {
        mProgressbar.setProgress(progress);
        if (progress == 100) {
            CancelProgressDialog();
        }
    }

    public void CancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void ShowNegativeDialog(Context context, String Title, String Message, String NegText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setNegativeButton(NegText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public static void ShowPositiveDialog(Context context, String Title, String Message, String PosText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(PosText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public static void ShowSimpleDialog(Context context, String Title, String Message, String PosText, String NegText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(PosText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton(NegText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    /*Color Dialog*/

    public void showSuccessPromptDlg() {
        new PromptDialog(mContext)
                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                .setAnimationEnable(true)
                .setTitleText("Success").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showInfoPromptDlg() {
        new PromptDialog(mContext)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setTitleText("Info").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showErrorPromptDlg() {
        new PromptDialog(mContext)
                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                .setAnimationEnable(true)
                .setTitleText("Subcrition Error").setContentText("We're Sorry! \nYou have left only 20 days. Please subscribe your account with the amount of selected Commodity * 1500.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showHelpPromptDlg() {
        new PromptDialog(mContext)
                .setDialogType(PromptDialog.DIALOG_TYPE_HELP)
                .setAnimationEnable(true)
                .setTitleText("Help").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showWarningPromptDlg() {
        new PromptDialog(mContext)
                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                .setAnimationEnable(true)
                .setTitleText("Warning").setContentText("अगर आप नई और कमोडिटी जोड़ना चाहते है तो कृपया इस नंबर 9350141815 पर संपर्क करे |" + "\n\nIf you want to add or remove Commodity please contact 9350141815.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

//    public void showTextDialog() {
//        ColorDialog dialog = new ColorDialog(mContext);
//        dialog.setColor("#8ECB54");
//        dialog.setAnimationEnable(true);
//        dialog.setTitle(mContext.getString(R.string.operation));
//        dialog.setContentText(mContext.getString(R.string.content_text));
//        dialog.setPositiveListener(mContext.getString(R.string.text_iknow), new ColorDialog.OnPositiveListener() {
//            @Override
//            public void onClick(ColorDialog dialog) {
//                //Toast.makeText(HomeActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        }).show();
//    }
//
//    public void showPicDialog() {
//        ColorDialog dialog = new ColorDialog(mContext);
//        dialog.setTitle(mContext.getString(R.string.operation));
//        dialog.setAnimationEnable(true);
//        dialog.setAnimationIn(getInAnimationTest(mContext));
//        dialog.setAnimationOut(getOutAnimationTest(mContext));
//        dialog.setContentImage(mContext.getResources().getDrawable(R.drawable.app_logo));
//        dialog.setPositiveListener(mContext.getString(R.string.delete), new ColorDialog.OnPositiveListener() {
//            @Override
//            public void onClick(ColorDialog dialog) {
//                //Toast.makeText(HomeActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        })
//                .setNegativeListener(mContext.getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
//                    @Override
//                    public void onClick(ColorDialog dialog) {
//                        //Toast.makeText(HomeActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                }).show();
//    }

    public static AnimationSet getInAnimationTest(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }

    public static AnimationSet getOutAnimationTest(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }
}


