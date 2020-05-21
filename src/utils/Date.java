package utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Date {
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat fmtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(GregorianCalendar date){
        return fmt.format(date.getTime());
    }
    public static String formatTime(GregorianCalendar date){
        return fmtTime.format(date.getTime());
    }
}
