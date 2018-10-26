package es.ezlib.log;

import com.google.gson.Gson;

public class EzlibLogBuilder {

    EzlibLog log;

    public EzlibLogBuilder() {
        log = new EzlibLog();
    }

    public EzlibLogBuilder addMessage(String message) {
        log.setMessage(message);
        return this;
    }

    public EzlibLogBuilder addHeader(String header) {
        log.setHeader(header);
        return this;
    }

    public EzlibLogBuilder addThrowable(Throwable throwable) {
        log.setThrowable(throwable);
        return this;
    }

    public EzlibLogBuilder addStackTraceDeep(int stackTraceDeep) {
        log.setStackTraceDeep(stackTraceDeep);
        return this;
    }

    public EzlibLogBuilder addJsonObject(Object jsonObject) {
        Gson gson = new Gson();
        log.setJson(gson.toJson(jsonObject));
        return this;
    }

    public EzlibLogBuilder addJsonString(String jsonString) {
        log.setJson(jsonString);
        return this;
    }

    public EzlibLogBuilder addXmlString(String xmlString) {
        log.setXml(xmlString);
        return this;
    }

    public EzlibLog create() { return log; }
}
