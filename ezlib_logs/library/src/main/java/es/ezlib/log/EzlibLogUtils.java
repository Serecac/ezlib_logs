package es.ezlib.log;

import java.util.Arrays;

class EzlibLogUtils {

    static final int SEPARATOR_LINE_LENTGH = 90;
    static final String DEFAULT_TAG = "EzlibLogManager";
    static final String lineNormal = "║ %s";

    static final int LOG_DEBUG = 1;
    static final int LOG_VERBOSE = 2;
    static final int LOG_INFO = 3;
    static final int LOG_ERROR = 4;
    static final int LOG_WARNING = 5;

    static String getLineFirstSeparator(){
        String returnLine = "╔";
        return returnLine + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'═');
    }

    static String getLineLastSeparator(){
        String returnLine = "╚";
        return returnLine + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'═');
    }

    static String getLineMidSeparator(){
        String returnLine = "╟";
        return returnLine + stringFromCloningChar(SEPARATOR_LINE_LENTGH,'─');
    }

    static String getLineNormal(String message){
        return String.format(lineNormal, message);
    }

    static String stringFromCloningChar(int size, char ch){
        final char[] array = new char[size];
        Arrays.fill(array, ch);
        return new String(array);
    }
}
