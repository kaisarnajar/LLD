package LoggingSystem;

import LoggingSystem.CORPattern.DebugLogger;
import LoggingSystem.CORPattern.ErrorLogger;
import LoggingSystem.CORPattern.InfoLogger;
import LoggingSystem.CORPattern.LogHandler;
import LoggingSystem.LogAppenders.ConsoleAppender;
import LoggingSystem.LogAppenders.FileAppender;
import LoggingSystem.LogAppenders.LogAppender;

public class Main {

    private static LogHandler getChainOfLoggers(LogAppender appender) {
        LogHandler errorLogger = new ErrorLogger(LogHandler.ERROR, appender);
        LogHandler debugLogger = new DebugLogger(LogHandler.DEBUG, appender);
        LogHandler infoLogger = new InfoLogger(LogHandler.INFO, appender);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }

    public static void main(String[] args) {
        LogAppender consoleAppender = new ConsoleAppender();
        FileAppender fileAppender = new FileAppender("file.txt");
        LogHandler loggerChain = getChainOfLoggers(consoleAppender);

        System.out.println("Logging INFO level message:");
        loggerChain.logMessage(LogHandler.INFO, "This is an information.");
        System.out.println("nLogging DEBUG level message:");
        loggerChain.logMessage(LogHandler.DEBUG, "This is a debug level information.");
        System.out.println("nLogging ERROR level message:");
        loggerChain.logMessage(LogHandler.ERROR, "This is an error information.");

        //Using Singleton Design Pattern
        System.out.println("Using Singleton Logger:");
        Logger logger = Logger.getInstance(LogLevel.INFO, consoleAppender);
        logger.setConfig(new LoggerConfig(LogLevel.INFO, fileAppender));
        logger.error("Using singleton Logger - Error message");
    }
}
