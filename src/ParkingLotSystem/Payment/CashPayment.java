package ParkingLotSystem.Payment;

public class CashPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("processing payment using : " + amount);
    }

}
