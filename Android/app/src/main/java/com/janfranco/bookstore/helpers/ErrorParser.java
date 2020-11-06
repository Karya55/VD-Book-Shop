package com.janfranco.bookstore.helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorParser {

    public String getMessage(String errorBody) {
        try {
            JSONObject jsonError = new JSONObject(errorBody);
            return jsonError.getString("message");
        } catch (JSONException jsonException) {
            return jsonException.getMessage();
        }
    }

}
