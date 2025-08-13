interface Coffee {
    int getCost();

    String getDescription();
}

abstract class CoffeeDecorator implements Coffee {
    Coffee decoratedCoffee;

    CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public int getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

class Espresso implements Coffee {
    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Espresso";
    }
}

class Cappuccino implements Coffee {
    @Override
    public int getCost() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Cappuccino";
    }
}

class SugarDecorator extends CoffeeDecorator {
    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return decoratedCoffee.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " Sugar";
    }
}

class MilkDecorator extends CoffeeDecorator {
    MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return decoratedCoffee.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " Milk";
    }
}

public class DecoratorDesignPattern {
    public static void main(String[] args) {
        Coffee coffee = new Espresso();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getCost());
        System.out.println(coffee.getDescription());

        Coffee newCoffee = new Cappuccino();
        newCoffee = new SugarDecorator(newCoffee);
        newCoffee = new MilkDecorator(newCoffee);

        System.out.println(newCoffee.getDescription());
        System.out.println(newCoffee.getCost());
    }
}
