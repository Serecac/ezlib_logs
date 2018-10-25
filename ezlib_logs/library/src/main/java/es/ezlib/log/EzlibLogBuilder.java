package es.ezlib.log;

import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
