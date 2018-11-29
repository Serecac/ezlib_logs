package es.ezlib.logs_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.ezlib.logs.EzlibLogBuilder;
import es.ezlib.logs.EzlibLogInstance;
import es.ezlib.logs.EzlibLogManager;

import static es.ezlib.logs.EzlibLogManager.LOG_VERBOSE;
import static es.ezlib.logs.EzlibLogManager.LOG_WARNING;
import static es.ezlib.logs.EzlibLogManager.NO_LOG;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        EzlibLogManager ezlibLogManager = new EzlibLogManager("Ezlib_test", LOG_VERBOSE);

        ezlibLogManager.error(new EzlibLogBuilder().addHeader("Prueba de cabecera"));

        ezlibLogManager.warning(new EzlibLogBuilder().addHeader("Prueba de cabecera").addMessage("Prueba de mensaje"));

        ezlibLogManager.verbose(new EzlibLogBuilder().addMessage("Prueba de mensaje"));

        TestExample testExample = new TestExample();
        ezlibLogManager.warning(new EzlibLogBuilder().addHeader("Prueba de cabecera").addMessage("Prueba de mensaje").addJsonObject(testExample));

        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><test_array><element><test_item_int>1</test_item_int><test_item_string>2</test_item_string></element><element><test_item_int>1</test_item_int><test_item_string>2</test_item_string></element><element><test_item_int>1</test_item_int><test_item_string>2</test_item_string></element></test_array><test_int>1</test_int><test_string>2</test_string></root>";
        ezlibLogManager.warning(new EzlibLogBuilder().addXmlString(xmlString));

        String testString = null;
        try {
            testString.compareTo("");
        } catch (Exception e) {
            ezlibLogManager.error(new EzlibLogBuilder().addHeader("Prueba de cabecera").addMessage("Prueba de mensaje").addThrowable(e).addStackTraceDeep(3));
            ezlibLogManager.error(new EzlibLogBuilder().addThrowable(e).addStackTraceDeep(3));
            ezlibLogManager.error(new EzlibLogBuilder().addHeader("Prueba de cabecera").addMessage("Prueba de mensaje").addThrowable(e));
            ezlibLogManager.error(new EzlibLogBuilder().addHeader("Prueba de cabecera").addMessage("Prueba de mensaje").addThrowable(e).addStackTraceDeep(3).addJsonObject(new TestExample()).addXmlString(xmlString));
        }

        EzlibLogInstance.error(new EzlibLogBuilder().addHeader("Prueba de cabecera 1"));
        EzlibLogInstance.init("Ezlib_test2", NO_LOG);
        EzlibLogInstance.error(new EzlibLogBuilder().addHeader("Prueba de cabecera 2"));
        EzlibLogInstance.init("Ezlib_test2", LOG_WARNING);
        EzlibLogInstance.error(new EzlibLogBuilder().addHeader("Prueba de cabecera 3"));
    }
}
