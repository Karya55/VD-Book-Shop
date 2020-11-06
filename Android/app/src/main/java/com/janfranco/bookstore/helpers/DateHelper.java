package com.janfranco.bookstore.helpers;

import java.util.Date;

public class DateHelper {

    public Date getCurrentDate() {
        return new Date();
    }

    public long convertToMilliseconds(Date date) {
        return date.getTime();
    }

    public Date convertFromMilliseconds(long milliseconds) {
        return new Date(milliseconds);
    }

    public int compareDates(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    public Date addMinutesToDate(Date date, int minutes) {
        return new Date(date.getTime() + minutes * 60000); // 60000 -> one minute in ms
    }

}
