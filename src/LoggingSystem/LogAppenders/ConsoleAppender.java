package LoggingSystem.LogAppenders;

import LoggingSystem.LogMessage;

public class ConsoleAppender implements LogAppender {
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logMessage); // Print log to console
    }
}
