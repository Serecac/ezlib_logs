package es.ezlib.log;

import com.google.gson.Gson;

public class EzlibLog {

    private String header;
    private String message;
    private String json;
    private String xml;
    private int stackTraceDeep;
    private Throwable throwable;

    public EzlibLog() {
        this.header = null;
        this.message = null;
        this.json = null;
        this.xml = null;
        this.stackTraceDeep = -1;
        this.throwable = null;
    }

    public EzlibLog(String header, String message, String json, String xml, int stackTraceDeep, Throwable throwable) {
        this.header = header;
        this.message = message;
        this.json = json;
        this.xml = xml;
        this.stackTraceDeep = stackTraceDeep;
        this.throwable = throwable;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public int getStackTraceDeep() {
        return stackTraceDeep;
    }

    public void setStackTraceDeep(int stackTraceDeep) {
        this.stackTraceDeep = stackTraceDeep;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isHeader() {
        return header != null && !header.isEmpty();
    }

    public boolean isMessage() {
        return message != null && !message.isEmpty();
    }

    public boolean isThrowable() {
        return throwable != null;
    }

    public boolean isJson() {
        return json != null  && !json.isEmpty();
    }

    public boolean isXml() {
        return xml != null  && !xml.isEmpty();
    }

    public int getRealStackTraceDeep(){
        return isThrowable() ? (stackTraceDeep != -1 ? stackTraceDeep : throwable.getStackTrace().length) : 0;
    }
}
