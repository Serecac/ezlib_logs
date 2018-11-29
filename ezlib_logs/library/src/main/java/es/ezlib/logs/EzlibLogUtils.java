package es.ezlib.logs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

class EzlibLogUtils {

    static final int SEPARATOR_LINE_LENTGH = 90;
    static final String DEFAULT_TAG = "EzlibLogManager";
    static final String lineNormal = "║ %s";

    static String getLineFirstSeparator(){
        return "╔" + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'═');
    }

    static String getLineLastSeparator(){
        return "╚" + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'═');
    }

    static String getLineMidSeparator(){
        return "╟" + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'─');
    }

    static String getLineNormal(String message){
        return String.format(lineNormal, message);
    }

    static String stringFromCloningChar(int size, char ch){
        final char[] array = new char[size];
        Arrays.fill(array, ch);
        return new String(array);
    }

    static Object getJsonObjFromStr(Object json) {
        try {
            return new JSONObject(json.toString());
        } catch (JSONException ex) {
            try {
                return new JSONArray(json);
            } catch (JSONException ex1) {
                return null;
            }
        }
    }
}
