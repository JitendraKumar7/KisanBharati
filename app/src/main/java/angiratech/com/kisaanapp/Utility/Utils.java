package angiratech.com.kisaanapp.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static String getTodayDate() {
        return new SimpleDateFormat("dd").format(new Date()).toString();
    }

    public static String getTodayMonth() {
        return new SimpleDateFormat("MM").format(new Date()).toString();
    }

    public static String getTodayYear() {
        return new SimpleDateFormat("yyyy").format(new Date()).toString();
    }

    public static String getCurrentMonthYear() {
        return new SimpleDateFormat("-MM-yyyy").format(new Date()).toString();
    }

    public static int getMaxDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd").format(new Date()).toString();
    }

    public static String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String monthName = month_date.format(cal.getTime());
        return monthName;
    }

    public static String getDateCurrentTimeZone(long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        } catch (Exception e) {
        }
        return "";
    }
}
