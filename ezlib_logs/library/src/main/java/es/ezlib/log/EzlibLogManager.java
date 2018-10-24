package es.ezlib.log;

import static es.ezlib.log.EzlibLogUtils.DEFAULT_TAG;
import static es.ezlib.log.EzlibLogUtils.LOG_ERROR;

public class EzlibLogManager {

    private String tag = DEFAULT_TAG;

    public void error(EzlibLog log){
        writeFullLog(log, LOG_ERROR);
    }

    private void writeFullLog(EzlibLog log, int tyoe){

    }
}
