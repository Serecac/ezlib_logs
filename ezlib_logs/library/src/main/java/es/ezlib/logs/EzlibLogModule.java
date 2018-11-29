package es.ezlib.logs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EzlibLogModule {

    @Provides
    @Singleton
    EzlibLogManager provideEzlibLogManager(@EzlibLogTag String tag, @EzlibLogLogLevel int logLevel) {
        return new EzlibLogManager(tag, logLevel);
    }
}
