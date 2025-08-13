interface PaymentStrategy {
    void pay();
}

class CreditCardStragegy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("CreditCard Strategy");
    }
}

class UPIStrategy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("UPI Strategy");
    }
}

class PaypalStrategy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Paypal Strategy");
    }
}

class Payment {
    PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay()
    {
        paymentStrategy.pay();
    }
}

public class StrategyDesignPattern {

    public static void main(String[] args) {

        UPIStrategy upiStrategy = new UPIStrategy();
        CreditCardStragegy creditCardStragegy = new CreditCardStragegy();
        PaypalStrategy paypalStrategy = new PaypalStrategy();

        Payment payment = new Payment(upiStrategy);
        payment.pay();

        payment.setPaymentStrategy(creditCardStragegy);
        payment.pay();

        payment.setPaymentStrategy(paypalStrategy);
        payment.pay();
    }
}
