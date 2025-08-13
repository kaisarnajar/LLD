interface BreatheProcess {
    void performBreathe();
}

class LandBreathe implements BreatheProcess {
    @Override
    public void performBreathe() {
        System.out.println("perform land breathe");
    }
}

class WaterBreathe implements BreatheProcess {
    @Override
    public void performBreathe() {
        System.out.println("Perform water breathe");
    }
}

class AirBreathe implements BreatheProcess {
    @Override
    public void performBreathe() {
        System.out.println("Perform Air Breathe");
    }
}

abstract class LivingBeings {
    BreatheProcess breatheProcess;

    LivingBeings(BreatheProcess breatheProcess) {
        this.breatheProcess = breatheProcess;
    }

    abstract void show();

    public void breathe() {
        breatheProcess.performBreathe();
    }
}

class HumanBeings extends LivingBeings {
    HumanBeings(BreatheProcess breatheProcess) {
        super(breatheProcess);
    }

    @Override
    public void show() {
        System.out.println("Printing Human Beings");
    }
}

class Fish extends LivingBeings {
    Fish(BreatheProcess breatheProcess) {
        super(breatheProcess);
    }

    @Override
    public void show() {
        System.out.println("Printing Fish");
    }
}

//class Tree extends LivingBeings {
//    Tree(BreatheProcess breatheProcess) {
//        super(breatheProcess);
//    }
//
//    @Override
//    public void show() {
//        System.out.println("Printing Tree");
//    }
//}

public class BridgeDesignPattern {
    public static void main(String[] args) {
        LandBreathe landBreathe = new LandBreathe();
        WaterBreathe waterBreathe = new WaterBreathe();
        AirBreathe airBreathe = new AirBreathe();

        HumanBeings humanBeings = new HumanBeings(airBreathe);
        Fish fish = new Fish(waterBreathe);

        humanBeings.breathe();
        humanBeings.show();
        System.out.println();

        fish.breathe();
        fish.show();
    }
}
