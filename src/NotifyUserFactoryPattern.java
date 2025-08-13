interface Notification {
    void sendNotification();
}

class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("SMS Notification");
    }
}

class EmailNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Email Notification");
    }
}

class WhatsAppNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Whatsapp notification");
    }
}

class NotificationFactory {
    public static Notification createNotification(String notificationType) {
        if (notificationType.equals("SMS")) {
            return new SMSNotification();
        } else if (notificationType.equals("Email")) {
            return new EmailNotification();
        } else {
            return new WhatsAppNotification();
        }
    }
}

public class NotifyUserFactoryPattern {
    public static void main(String[] args) {
        Notification smsNotification = NotificationFactory.createNotification("SMS");
        smsNotification.sendNotification();

        Notification emailNotification = NotificationFactory.createNotification("Email");
        emailNotification.sendNotification();

        Notification whatsAppNotification = NotificationFactory.createNotification("Whatsapp");
        whatsAppNotification.sendNotification();
    }
}
