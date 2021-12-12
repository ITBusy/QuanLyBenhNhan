package com.QLBN.Poly.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();
    static Calendar c = Calendar.getInstance();

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);// pattern :"dd-mm-yyyy", //"mm/dd/yyyy"
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static int getYear(Date date) {
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    public static int getHours(Date date) {
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutes(Date date) {
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSeconds(Date date) {
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }
}
