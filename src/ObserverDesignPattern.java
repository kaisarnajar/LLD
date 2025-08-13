import java.util.ArrayList;
import java.util.List;

interface Subscriber {
    void update();
}

class SubscriberClass implements Subscriber {
    public void update() {
        System.out.println("Video has been uploaded");
    }
}

interface YoutubeChannel {
    void addObserver(Subscriber subscriber);

    void removeObserver(Subscriber subscriber);

    void notifyObservers();
}

class YoutubeChannelImpl implements YoutubeChannel {
    List<Subscriber> subscribers;

    public YoutubeChannelImpl() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void addObserver(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeObserver(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyObservers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void addVideo() {
        notifyObservers();
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        YoutubeChannelImpl youtubeChannel = new YoutubeChannelImpl();

        SubscriberClass subscriberClassOne = new SubscriberClass();
        SubscriberClass subscriberClassTwo = new SubscriberClass();

        youtubeChannel.addObserver(subscriberClassOne);
        youtubeChannel.addObserver(subscriberClassTwo);

        youtubeChannel.addVideo();

        youtubeChannel.removeObserver(subscriberClassTwo);

        youtubeChannel.addVideo();
    }
}
