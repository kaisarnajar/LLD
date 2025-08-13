import java.util.ArrayList;
import java.util.List;

interface HomeComponent {
    void turnOn();

    void turnOff();
}

class CompositeComponent implements HomeComponent {
    String name;
    List<HomeComponent> componentList;

    CompositeComponent(String name) {
        componentList = new ArrayList<>();
        this.name = name;
    }

    public void addComponent(HomeComponent compositeComponent) {
        componentList.add(compositeComponent);
    }

    public void removeComponent(CompositeComponent compositeComponent) {
        componentList.remove(compositeComponent);
    }

    @Override
    public void turnOff() {
        System.out.println("Turning Off components in : " + name);
        for (HomeComponent component : componentList) {
            component.turnOff();
        }
    }

    @Override
    public void turnOn() {
        System.out.println("Turning On Components in : " + name);
        for (HomeComponent component : componentList) {
            component.turnOn();
        }
    }
}

class Appliance implements HomeComponent {
    String name;

    Appliance(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println("Turning On the device : " + name);
    }

    @Override
    public void turnOff() {
        System.out.println("Turning Off the device : " + name);
    }
}

public class CompositeDesignPattern {
    public static void main(String[] args) {
        Appliance fan = new Appliance("Fan");
        Appliance washingMachine = new Appliance("Washing Machine");
        Appliance tv = new Appliance("TV");

        CompositeComponent roomOne = new CompositeComponent("RoomOne");
        roomOne.addComponent(fan);
        roomOne.addComponent(washingMachine);
        roomOne.addComponent(tv);

        CompositeComponent roomTwo = new CompositeComponent("RoomTwo");
        roomTwo.addComponent(fan);
        roomTwo.addComponent(washingMachine);
        roomTwo.addComponent(tv);

        CompositeComponent roomThree = new CompositeComponent("RoomThree");
        roomThree.addComponent(fan);
        roomThree.addComponent(washingMachine);
        roomThree.addComponent(tv);

        CompositeComponent floorOne = new CompositeComponent("FloorOne");
        floorOne.addComponent(roomOne);
        floorOne.addComponent(roomTwo);
        floorOne.addComponent(roomThree);

        CompositeComponent floorTwo = new CompositeComponent("FloorTwo");
        floorTwo.addComponent(roomOne);
        floorTwo.addComponent(roomTwo);
        floorTwo.addComponent(roomThree);

        CompositeComponent floorThree = new CompositeComponent("FloorThree");
        floorThree.addComponent(roomOne);
        floorThree.addComponent(roomTwo);
        floorThree.addComponent(roomThree);

        CompositeComponent myHouse = new CompositeComponent("House");
        myHouse.addComponent(floorOne);
        myHouse.addComponent(floorTwo);
        myHouse.addComponent(floorThree);

        myHouse.turnOn();
        myHouse.turnOff();
    }
}
