<img src="https://github.com/Serecac/ezlib_logs/blob/master/ezlib.png" alt="Ezlib" width="128" height="128">

Ezlib Logs
=======

This library allows to write logs cleaner and with better visibility in the logcat.

How to use
-------
Put the .aar file (Ezlib_Log_x.y.z.aar) in the folder "Libs" inside the module.

In your project build.gradle. Add the following lines
```
flatDir {
	dirs 'libs'
}
```
```
dependencies {
	implementation(name: 'Ezlib_Log_x.y.z', ext: 'aar')
}
```

------------------------------------------------------

There are two ways to access EzlibLogManager:
- Accessing directly using EzlibLogInstance
- Use dagger to inject the dependencies using EzlibLogModule


**EzlibLogInstance**

First the instance must be initialized (it is not mandatory). It is recommended to do it at the beginning in the Application class.
```
EzlibLogInstance.init(<tag>, <logLevel>);
```

Then you can send your logs
```
EzlibLogInstance.error(new EzlibLogBuilder().addHeader(<header>));
EzlibLogInstance.warning(new EzlibLogBuilder().addMessage(<message));
```


**EzlibLogModule**

The library provides a module that can be injected directly by dagger into your project.
```
@Provides
@Singleton
EzlibLogManager provideEzlibManager(@EzlibLogTag String tag, @EzlibLogLogLevel int logLevel) {
	return new EzlibLogManager(tag, logLevel);
}
```

If you use this implementation it is necessary to give value to the annotations, as shown in the following example:
```
@Provides
@Singleton
@EzlibLogLogLevel
int provideEzlibLogLogLevel() {
	return <log_level>;
}

@Provides
@Singleton
@EzlibLogTag
String provideEzlibLogTag() {
	return <tag>;
}
```

Log values
-------
The log must be generated by the EzlibLogBuilder.
The log will write the different values in the following order:
* Header
* Message
* Throwable 
* JSON
* XML

The StackTraceDeep value is used to limit the depth of the Throwable trace

Contribution
=======
You are always welcome to contribute and help us mantain the library. 

<img src="https://github.com/Serecac/ezlib_logs/blob/master/ezlib.png" alt="Ezlib" width="128" height="128">