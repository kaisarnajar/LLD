interface Vehicle {
    void average();
}

class Mercedes implements Vehicle {
    @Override
    public void average() {
        System.out.println("Mercedes average is 100km");
    }
}

class BMW implements Vehicle {
    @Override
    public void average() {
        System.out.println("BMW average is 200km");
    }
}

class Swift implements Vehicle {
    @Override
    public void average() {
        System.out.println("Swift average is 100 km");
    }
}

class Hyundai implements Vehicle {
    @Override
    public void average() {
        System.out.println("Hyundai average is 120 km");
    }
}

interface VehicleFactory {
    Vehicle getVehicle(String vehicleType);
}

class LuxuryFactory implements VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType.equals("Mercedes")) {
            return new Mercedes();
        } else if (vehicleType.equals("BMW")) {
            return new BMW();
        } else {
            throw new IllegalArgumentException("Unknown Luxury Vehicle");
        }
    }
}

class OrdinaryFactory implements VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType.equals("Swift")) {
            return new Swift();
        } else if (vehicleType.equals("Hyundai")) {
            return new Hyundai();
        } else {
            throw new IllegalArgumentException("unknown Ordinary Type vehicle");
        }
    }
}

public class AbstractFactoryDesignPattern {
    public static void main(String[] args) {
        LuxuryFactory luxuryFactory = new LuxuryFactory();
        OrdinaryFactory ordinaryFactory = new OrdinaryFactory();

        luxuryFactory.getVehicle("BMW").average();
        ordinaryFactory.getVehicle("Hyundai").average();
    }
}
