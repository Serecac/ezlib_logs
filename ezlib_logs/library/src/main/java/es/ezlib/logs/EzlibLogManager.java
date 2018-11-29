package es.ezlib.logs;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static es.ezlib.logs.EzlibLogUtils.DEFAULT_TAG;
import static es.ezlib.logs.EzlibLogUtils.getJsonObjFromStr;
import static es.ezlib.logs.EzlibLogUtils.getLineFirstSeparator;
import static es.ezlib.logs.EzlibLogUtils.getLineLastSeparator;
import static es.ezlib.logs.EzlibLogUtils.getLineMidSeparator;
import static es.ezlib.logs.EzlibLogUtils.getLineNormal;

public class EzlibLogManager {

    public static final int LOG_VERBOSE = 1;
    public static final int LOG_DEBUG = 2;
    public static final int LOG_INFO = 3;
    public static final int LOG_WARNING = 4;
    public static final int LOG_ERROR = 5;
    public static final int NO_LOG = 6;

    private String tag = DEFAULT_TAG;
    private int log_level = LOG_VERBOSE;

    private final String INI_LOG = " \n";

    public EzlibLogManager() {
    }

    public EzlibLogManager(String tag, int log_level) {
        this.tag = tag;
        this.log_level = log_level;
    }

    public void error(EzlibLogBuilder builder) {
        writeFullLog(builder.create(), LOG_ERROR);
    }

    public void debug(EzlibLogBuilder builder) {
        writeFullLog(builder.create(), LOG_DEBUG);
    }

    public void warning(EzlibLogBuilder builder) {
        writeFullLog(builder.create(), LOG_WARNING);
    }

    public void verbose(EzlibLogBuilder builder) {
        writeFullLog(builder.create(), LOG_VERBOSE);
    }

    public void info(EzlibLogBuilder builder) {
        writeFullLog(builder.create(), LOG_INFO);
    }

    private synchronized void writeFullLog(EzlibLog log, int type) {

        if (log_level <= type) {

            String logToPrint = "";
            logToPrint += writeHeader(log);
            logToPrint += writeMessage(log, logHasLines(logToPrint));
            logToPrint += writeThrowable(log, logHasLines(logToPrint));
            logToPrint += writeJson(log, logHasLines(logToPrint));
            logToPrint += writeXml(log, logHasLines(logToPrint));
            sendLog(logToPrint, type);
        }
    }

    private String writeHeader(EzlibLog log) {
        return log.hasHeader() ? getLineNormal(log.getHeader()) + "\n" : "";
    }

    private String writeMessage(EzlibLog log, boolean hasLines) {

        String message = "";
        if (log.hasMessage()) {
            message += hasLines ? getLineMidSeparator() + "\n" : "";
            message += getLineNormal(log.getMessage()) + "\n";
        }
        return message;
    }

    private String writeThrowable(EzlibLog log, boolean hasLines) {
        String throwable = "";
        if (log.hasThrowable()) {
            throwable += hasLines ? getLineMidSeparator() + "\n" : "";
            throwable += getLineNormal("Cause: " + log.getThrowable().toString()) + "\n";
            throwable += getLineNormal("Throwable: " + log.getThrowable().getStackTrace()[0].toString()) + "\n";
            for (int i = 1; i < log.getRealStackTraceDeep(); i++)
                throwable += getLineNormal("\t\t" + log.getThrowable().getStackTrace()[i].toString()) + "\n";
        }
        return throwable;
    }

    private String writeJson(EzlibLog log, boolean hasLines) {
        String json = "";
        if (log.hasJson()) {
            Object jsonObject = getJsonObjFromStr(log.getJson());
            if (jsonObject != null) {
                json += hasLines ? getLineMidSeparator() + "\n" : "";
                try {
                    if (jsonObject instanceof JSONObject)
                        json += getLineNormal(((JSONObject) jsonObject).toString(2).replace("\n", "\n║ ")) + "\n";
                    else if (jsonObject instanceof JSONArray)
                        json += getLineNormal(((JSONArray) jsonObject).toString(2).replace("\n", "\n║ ")) + "\n";
                    else
                        json = "";
                } catch (JSONException e) {
                    json += getLineNormal("Error parsing JSON") + "\n";
                }
            }
        }
        return json;
    }

    private String writeXml(EzlibLog log, boolean hasLines) {
        String xml = "";
        if (log.hasXml()) {
            xml += hasLines ? getLineMidSeparator() + "\n" : "";
            StreamSource xmlInput = new StreamSource(new StringReader(log.getXml()));
            StringWriter stringWriter = new StringWriter();
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
                transformer.transform(xmlInput, new StreamResult(stringWriter));
                String result = stringWriter.toString().trim();
                xml += getLineNormal(result.replace("\n", "\n║ ")) + "\n";

            } catch (Exception e) {
                xml += getLineNormal("Error parsing XML") + "\n";
            }
        }
        return xml;
    }

    private void sendLog(String logToPrint, int type) {

        if (!logToPrint.equals("")) {

            String finalLog = INI_LOG + getLineFirstSeparator() + "\n" + logToPrint + getLineLastSeparator();

            switch (type) {
                case LOG_DEBUG:
                    Log.d(tag, finalLog);
                    break;
                case LOG_WARNING:
                    Log.w(tag, finalLog);
                    break;
                case LOG_ERROR:
                    Log.e(tag, finalLog);
                    break;
                case LOG_VERBOSE:
                    Log.v(tag, finalLog);
                    break;
                default:
                    Log.i(tag, finalLog);
                    break;
            }
        }
    }

    private boolean logHasLines(String log) {
        return !log.equals("");
    }
}
