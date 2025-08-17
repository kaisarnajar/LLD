package LoggingSystem.CORPattern;

import LoggingSystem.LogAppenders.LogAppender;

public class InfoLogger extends LogHandler {

    public InfoLogger(int level, LogAppender appender) {
        super(level, appender);
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}
