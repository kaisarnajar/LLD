class LoggerSystem {
    private static LoggerSystem loggerSystem = null;

    private LoggerSystem() {
        //Private constructor
    }

    public static LoggerSystem getInstance() {
        if (loggerSystem == null) {
            synchronized (LoggerSystem.class) {
                if (loggerSystem == null) {
                    loggerSystem = new LoggerSystem();
                }
            }
        }
        return loggerSystem;
    }

    public void log() {
        System.out.println("Logging with logger");
    }
}

public class SingletonDesignPattern {
    public static void main(String[] args) {
        LoggerSystem loggerSystem1 = LoggerSystem.getInstance();
        LoggerSystem loggerSystem2 = LoggerSystem.getInstance();

        System.out.println("is logger istance same? " + (loggerSystem1 == loggerSystem2));
    }
}
