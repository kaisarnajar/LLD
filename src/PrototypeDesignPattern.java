interface Robot extends Cloneable {
    Robot clone();
}

class HumaniodRobot implements Robot {

    String name;
    String model;
    int version;

    public HumaniodRobot(String name, String model, int version)
    {
        this.name = name;
        this.model = model;
        this.version = version;
    }

    @Override
    public Robot clone() {
        return new HumaniodRobot(name, model, version);
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Robot{name='" + name + "', model='" + model + "', version=" + version + "}";
    }
}

public class PrototypeDesignPattern {
    public static void main(String[] args) {
        HumaniodRobot original = new HumaniodRobot("Kaisar", "100" , 1);
        System.out.println(original);

        HumaniodRobot cloned = (HumaniodRobot) original.clone();
        cloned.version = 2;
        System.out.println(cloned);
    }
}
