package ParkingLotSystem;

import ParkingLotSystem.Payment.PaymentStrategy;

public class PaymentClass {
    private double amount;
    private PaymentStrategy paymentStrategy;

    public PaymentClass(double amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        if (amount > 0) {
            paymentStrategy.processPayment(amount);
        } else {
            System.out.println("Invalid payment amount");
        }
    }
}
