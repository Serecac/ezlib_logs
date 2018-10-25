package es.ezlib.log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EzlibLogModule {

    @Provides
    @Singleton
    EzlibLogManager provideEzlibManager(@EzlibLogTag String tag, @EzlibLogLogLevel int logLevel) {
        return new EzlibLogManager(tag, logLevel);
    }
}
