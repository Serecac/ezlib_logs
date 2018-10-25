package es.ezlib.log;

import android.util.Log;

import static es.ezlib.log.EzlibLogUtils.DEFAULT_TAG;
import static es.ezlib.log.EzlibLogUtils.LOG_DEBUG;
import static es.ezlib.log.EzlibLogUtils.LOG_ERROR;
import static es.ezlib.log.EzlibLogUtils.LOG_INFO;
import static es.ezlib.log.EzlibLogUtils.LOG_VERBOSE;
import static es.ezlib.log.EzlibLogUtils.LOG_WARNING;
import static es.ezlib.log.EzlibLogUtils.getLineFirstSeparator;
import static es.ezlib.log.EzlibLogUtils.getLineNormal;

public class EzlibLogManager {

    private String tag = DEFAULT_TAG;

    public void error(EzlibLog log){
        writeFullLog(log, LOG_ERROR);
    }
    public void debug(EzlibLog log){
        writeFullLog(log, LOG_DEBUG);
    }
    public void warning(EzlibLog log){
        writeFullLog(log, LOG_WARNING);
    }
    public void verbose(EzlibLog log){
        writeFullLog(log, LOG_VERBOSE);
    }
    public void info(EzlibLog log){
        writeFullLog(log, LOG_INFO);
    }

    private void writeFullLog(EzlibLog log, int type){

        String logToPrint = "";

        logToPrint += writeHeader(log);

        sendLog(logToPrint, type);
    }

    private String writeHeader(EzlibLog log){
        if (log.isHeader())
            return getLineFirstSeparator() + "\n" + getLineNormal(log.getHeader());
        return "";
    }

    private void sendLog(String logToPrint, int type){
        switch (type){
            case LOG_DEBUG:
                Log.d(tag,logToPrint);
                break;
            case LOG_WARNING:
                Log.w(tag,logToPrint);
                break;
            case LOG_ERROR:
                Log.e(tag,logToPrint);
                break;
            case LOG_VERBOSE:
                Log.v(tag,logToPrint);
                break;
            default:
                Log.i(tag,logToPrint);
                break;
        }
    }
}
