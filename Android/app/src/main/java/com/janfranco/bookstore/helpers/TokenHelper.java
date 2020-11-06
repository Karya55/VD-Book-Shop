package com.janfranco.bookstore.helpers;

import java.util.Date;

public class TokenHelper {

    public long getExpirationDateInMilliseconds(int minutes) {
        DateHelper dateHelper = new DateHelper();
        Date currentDate = dateHelper.getCurrentDate();
        Date expirationDate = dateHelper.addMinutesToDate(currentDate, minutes);
        return dateHelper.convertToMilliseconds(expirationDate);
    }

    public boolean checkTokenExpiration(long dateInMilliseconds) {
        DateHelper dateHelper = new DateHelper();
        Date currentDate = dateHelper.getCurrentDate();
        Date expirationDate = dateHelper.convertFromMilliseconds(dateInMilliseconds);
        return dateHelper.compareDates(currentDate, expirationDate) > 0;
    }

}
