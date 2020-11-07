package com.janfranco.bookstore.helpers;

import java.util.regex.Pattern;

public class RegularExpressionHelper {

    private final Pattern mPattern;

    public RegularExpressionHelper(String pattern) {
        mPattern = Pattern.compile(pattern);
    }

    public boolean isValid(String value) {
        return mPattern.matcher(value).matches();
    }

}
