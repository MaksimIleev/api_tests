package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static synchronized String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static synchronized String converToLocalDate(String date,String fromPattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fromPattern);
        LocalDate localDate = LocalDate.parse(date, dtf);
        return localDate.toString();
    }
}
