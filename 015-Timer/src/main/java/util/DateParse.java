package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by STZHANGJK on 2016.9.14.
 */
public class DateParse {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String parseString(Date date){
        return format.format(date);
    }

    public static Date parseDate(String str) throws ParseException {
        return format.parse(str);
    }
}
