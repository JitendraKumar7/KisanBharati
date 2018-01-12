package angiratech.com.kisaanapp.Utility;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amit on 10-Feb-16.
 */
public class PermissionHelper {
    public static final int READ_PHONE_STATE_PERMISSION = 100;

    private static final String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static Activity _activity;

    static List<String> getPermissionConstants(Context context) {
        return Arrays.asList(permissions);
    }

    private static List<PermissionInfo> getPermissions(Context context) {
        List<PermissionInfo> permissionInfoList = new ArrayList<>();

        PackageManager pm = context.getPackageManager();
        for (String permission : getPermissionConstants(context)) {
            PermissionInfo pinfo = null;
            try {
                pinfo = pm.getPermissionInfo(permission, PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            permissionInfoList.add(pinfo);
        }
        return permissionInfoList;
    }

    private static CharSequence[] getPermissionNames(Context context) {
        PackageManager pm = context.getPackageManager();
        CharSequence[] names = new CharSequence[getPermissions(context).size()];
        int i = 0;
        for (PermissionInfo permissionInfo : getPermissions(context)) {
            CharSequence label = permissionInfo.loadLabel(pm);
            Log.e("", "Get Permission Label -> " + label);
            names[i] = label;
            i++;
        }
        return names;
    }

    private static boolean[] getPermissionsState(Context context) {
        boolean[] states = new boolean[getPermissionConstants(context).size()];
        int i = 0;
        for (String permission : getPermissionConstants(context)) {
            states[i] = isPermissionGranted(context, permission);
            Log.e("", "Get Permission State -> " + states[i]);
            i++;
        }
        return states;
    }


  /*  public static void show(final Context context, String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMultiChoiceItems(getPermissionNames(context), getPermissionsState(context), new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                ActivityCompat.requestPermissions(scanForActivity(context),
                        new String[]{getPermissionConstants(context).get(which)}, 23);
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (isAlreadyAllPermissionsGranted(context)) {
                    dialog.cancel();
                } else {
                    Toast.makeText(context, "Please grant necessary permissions for BABS to proceed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

//        int permission = ContextCompat.checkSelfPermission(context,
//                String.valueOf(permissions));
//        if(permission!=PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(context, "Please grant  permissions for BabsApp successfully", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "Please grant necessary permissions for Babsapp to proceed", Toast.LENGTH_SHORT).show();
//        }

    }*/

    public static void DialogueShow(Activity context, String title) {
        for (String s : permissions) {
            if (ActivityCompat.checkSelfPermission(context, s) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(context, permissions, READ_PHONE_STATE_PERMISSION);
        }
    }

    private static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        return PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isAlreadyAllPermissionsGranted(Context context) {
        boolean permissionstate[] = getPermissionsState(context);
        Log.e("", "permissionstate length -> " + permissionstate.length);
        for (int i = 0; i < permissionstate.length; i++) {
            Log.e("", "Permission state -> " + permissionstate[i]);
            if (permissionstate[i] == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean areExplicitPermissionsRequired() {
        Log.e("", "SDK Version -> " + Build.VERSION.SDK_INT);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
