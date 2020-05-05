package utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Date {
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(GregorianCalendar date){
        return fmt.format(date.getTime());
    }
}
