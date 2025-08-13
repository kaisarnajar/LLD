import java.util.ArrayList;
import java.util.List;

interface IAuctionMediater {
    void placeBid(IBidder bidder, int amount);

    void addBidder(IBidder bidder);

    void removeBidder(IBidder bidder);
}

interface IBidder {
    void placeBid(int amount);

    void receiveBid(IBidder bidder, int amount);

    String getName();
}

class AuctionMediator implements IAuctionMediater {

    List<IBidder> bidders = new ArrayList<>();

    @Override
    public void placeBid(IBidder bidder, int amount) {
        for (IBidder b : bidders) {
            if (b != bidder) {
                b.receiveBid(bidder, amount);
            }
        }
    }

    @Override
    public void addBidder(IBidder bidder) {
        bidders.add(bidder);
    }

    @Override
    public void removeBidder(IBidder bidder) {
        bidders.remove(bidder);
    }
}

class Bidder implements IBidder {
    AuctionMediator mediator;
    String name;

    Bidder(String name, AuctionMediator mediator) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void placeBid(int amount) {
        mediator.placeBid(this, amount);
    }

    @Override
    public void receiveBid(IBidder bidder, int amount) {
        System.out.println("Bidder " + bidder.getName() + " has placed bid of " + amount + " amount");
    }

    @Override
    public String getName() {
        return name;
    }
}

public class MediaterDesignPattern {
    public static void main(String[] args) {
        AuctionMediator mediator = new AuctionMediator();

        Bidder bidderOne = new Bidder("Kaisar", mediator);
        Bidder bidderTwo = new Bidder("Sajad", mediator);
        Bidder bidderThree = new Bidder("Tahir", mediator);
        Bidder bidderFour = new Bidder("Akil", mediator);

        mediator.addBidder(bidderOne);
        mediator.addBidder(bidderTwo);
        mediator.addBidder(bidderThree);
        mediator.addBidder(bidderFour);

        bidderOne.placeBid(1000);
        bidderTwo.placeBid(2000);
        bidderThree.placeBid(3000);
        bidderFour.placeBid(4000);

        mediator.removeBidder(bidderThree);

        bidderOne.placeBid(5000);
    }
}
