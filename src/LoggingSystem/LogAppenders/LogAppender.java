package LoggingSystem.LogAppenders;

import LoggingSystem.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
