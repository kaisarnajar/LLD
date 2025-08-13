interface SmartDeviceAdapter {
    void turnOn();
    void turnOff();
}

class AirConditioner {
    public void turnOnAirConditioner()
    {
        System.out.println("Turning ON AC");
    }

    public void turnOffAirConditioner()
    {
        System.out.println("Turning OFF AC");
    }
}

class CoffeeMachine
{
    public void startBrewing()
    {
        System.out.println("Coffee machine is starting brewing");
    }

    public void stopBrewing()
    {
        System.out.println("Coffee machine is stopping brewing");
    }
}

class SmartLight
{
    public void turnOnSmartLight()
    {
        System.out.println("Turning the SmartLight ON");
    }

    public void turnOffSmartLight()
    {
        System.out.println("Turning OFF smart light");
    }
}

class AirConditionerAdapter implements SmartDeviceAdapter
{
    AirConditioner airConditioner;

    AirConditionerAdapter(AirConditioner conditioner)
    {
        this.airConditioner = conditioner;
    }

    @Override
    public void turnOn()
    {
        airConditioner.turnOnAirConditioner();
    }

    @Override
    public void turnOff()
    {
        airConditioner.turnOffAirConditioner();
    }
}

class CoffeeMachineAdapter implements SmartDeviceAdapter
{
    CoffeeMachine coffeeMachine;

    CoffeeMachineAdapter(CoffeeMachine coffeeMachine)
    {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void turnOn()
    {
        coffeeMachine.startBrewing();
    }

    @Override
    public void turnOff()
    {
        coffeeMachine.stopBrewing();
    }
}

class SmartLightAdapter implements SmartDeviceAdapter
{
    SmartLight smartLight;
    SmartLightAdapter(SmartLight smartLight)
    {
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn()
    {
        smartLight.turnOnSmartLight();
    }

    @Override
    public void turnOff()
    {
        smartLight.turnOffSmartLight();
    }
}

public class AdapterDesignPattern
{
    public static void main(String[] args)
    {
        AirConditionerAdapter airConditionerAdapter = new AirConditionerAdapter(new AirConditioner());
        SmartLightAdapter smartLightAdapter = new SmartLightAdapter(new SmartLight());
        CoffeeMachineAdapter coffeeMachineAdapter = new CoffeeMachineAdapter(new CoffeeMachine());

        System.out.println("Turning the devices ON");

        airConditionerAdapter.turnOn();
        smartLightAdapter.turnOn();
        coffeeMachineAdapter.turnOn();


        System.out.println("Turning the devices OFF");
        airConditionerAdapter.turnOff();
        smartLightAdapter.turnOff();
        coffeeMachineAdapter.turnOff();
    }
}
