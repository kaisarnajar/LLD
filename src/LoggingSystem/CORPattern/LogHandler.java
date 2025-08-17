package LoggingSystem.CORPattern;

import LoggingSystem.LogAppenders.LogAppender;
import LoggingSystem.LogLevel;
import LoggingSystem.LogMessage;

public abstract class LogHandler {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    protected int logLevel;
    protected LogHandler nextLogger;
    protected LogAppender appender;

    public LogHandler(int level, LogAppender logAppender) {
        this.logLevel = level;
        this.appender = logAppender;
    }

    public void setNextLogger(LogHandler nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.logLevel == level) {
            LogLevel logLevel = intToLogLevel(level);
            LogMessage logMessage = new LogMessage(logLevel, message);

            if (appender != null) {
                appender.append(logMessage);
            }
            write(message);
        } else if (nextLogger != null)
            nextLogger.logMessage(level, message);
    }

    private LogLevel intToLogLevel(int level) {
        switch (level) {
            case 1:
                return LogLevel.INFO;
            case 2:
                return LogLevel.DEBUG;
            case 3:
                return LogLevel.ERROR;
            default:
                return LogLevel.INFO;
        }
    }

    abstract protected void write(String message);
}
