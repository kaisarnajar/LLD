package LoggingSystem;

public class LogMessage {
    private final LogLevel logLevel;
    private final String message;
    private final long timestamp;

    public LogMessage(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLevel() {
        return logLevel;
    }

    // Returns the log message content
    public String getMessage() {
        return message;
    }

    // Returns the timestamp of the log creation
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + logLevel + "] " + timestamp + " - " + message;
    }
}
