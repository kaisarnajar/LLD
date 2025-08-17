package LoggingSystem;

import LoggingSystem.LogAppenders.LogAppender;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();
    private LoggerConfig loggerConfig;

    private Logger(LogLevel logLevel, LogAppender logAppender) {
        loggerConfig = new LoggerConfig(logLevel, logAppender);
    }

    public static Logger getInstance(LogLevel logLevel, LogAppender logAppender) {
        String key = logLevel.name() + "_" + logAppender.getClass().getName();

        return instances.computeIfAbsent(key, k -> new Logger(logLevel, logAppender));
    }

    public void setConfig(LoggerConfig loggerConfig) {
        synchronized (Logger.class) {
            this.loggerConfig = loggerConfig;
        }
    }

    public void log(LogLevel logLevel, String message) {
        if (logLevel.getValue() >= loggerConfig.getLogLevel().getValue()) {
            LogMessage logMessage = new LogMessage(logLevel, message);
            loggerConfig.getLogAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
