package es.ezlib.logs;

import static es.ezlib.logs.EzlibLogManager.LOG_VERBOSE;
import static es.ezlib.logs.EzlibLogUtils.DEFAULT_TAG;

public class EzlibLogInstance {

    private static EzlibLogManager ezlibLogManager = null;

    public static void init(String tag, int logLevel){
        ezlibLogManager = new EzlibLogManager(tag, logLevel);
    }

    public static void init(int logLevel){
        ezlibLogManager = new EzlibLogManager(DEFAULT_TAG, logLevel);
    }

    public static void init(String tag){
        ezlibLogManager = new EzlibLogManager(tag, LOG_VERBOSE);
    }

    public static void init(){
        ezlibLogManager = new EzlibLogManager();
    }

    public static void error(EzlibLogBuilder builder){
        if (ezlibLogManager == null)
            init();
        ezlibLogManager.error(builder);
    }

    public static void verbose(EzlibLogBuilder builder){
        if (ezlibLogManager == null)
            init();
        ezlibLogManager.verbose(builder);
    }

    public static void warning(EzlibLogBuilder builder){
        if (ezlibLogManager == null)
            init();
        ezlibLogManager.warning(builder);
    }

    public static void info(EzlibLogBuilder builder){
        if (ezlibLogManager == null)
            init();
        ezlibLogManager.info(builder);
    }

    public static void debug(EzlibLogBuilder builder){
        if (ezlibLogManager == null)
            init();
        ezlibLogManager.debug(builder);
    }
}
