package com.andrestorres.oneup.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Eli on 25/2/16.
 */
public class DateUtils {

    public static Date parseDate(String stringDate, String stringFormat) {
        DateFormat format = new SimpleDateFormat(stringFormat);
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getStringIso8601FromDate(String date) {
        return getStringIso8601FromDate(parseDate(date, "dd/MM/yyyy"));
    }

    public static String getStringIso8601FromDate(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        String isoDate = df.format(date);
        return isoDate;
    }

    public static String getDateString(Date date) {
        return getDateString(date, "MMM dd, yyyy");
    }

    public static String getDateString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);

        String dateF = df.format(date);

        return dateF;
    }

    public static ArrayList<String> getWeekendDates() {
        ArrayList<String> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilNextFriday = Calendar.FRIDAY - dayOfWeek;

        // Saturday
        if (daysUntilNextFriday == -1) {
            Calendar saturday = (Calendar) calendar.clone();
            dates.add(getDateString(saturday.getTime()));
            saturday.add(Calendar.DAY_OF_WEEK, 1);
            dates.add(getDateString(saturday.getTime()));
        }
        // Sunday
        else if (daysUntilNextFriday == 5) {
            Calendar sunday = (Calendar) calendar.clone();
            dates.add(getDateString(sunday.getTime()));
        }
        else {
            Calendar friday = (Calendar) calendar.clone();
            friday.add(Calendar.DAY_OF_WEEK, daysUntilNextFriday);

            dates.add(getDateString(friday.getTime()));
            friday.add(Calendar.DAY_OF_WEEK, 1);
            dates.add(getDateString(friday.getTime()));
            friday.add(Calendar.DAY_OF_WEEK, 1);
            dates.add(getDateString(friday.getTime()));
        }


        return dates;
    }

    public static ArrayList<String> getNextWeekDates() {
        ArrayList<String> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilNextMonday = Calendar.MONDAY - dayOfWeek;

        if (daysUntilNextMonday <= 0) {
            daysUntilNextMonday += 7;
        }

        Calendar monday = (Calendar) calendar.clone();
        monday.add(Calendar.DAY_OF_WEEK, daysUntilNextMonday);

        dates.add(getDateString(monday.getTime()));

        for (int count = 0; count < 6; count++) {
            monday.add(Calendar.DAY_OF_WEEK, 1);
            dates.add(getDateString(monday.getTime()));
        }

        return dates;
    }

    public static String getDayFromDate(String dateString) {
        Date date = parseDate(dateString, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        return sdf.format(date);
    }
}
