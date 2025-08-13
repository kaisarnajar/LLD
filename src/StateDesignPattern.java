class TrafficLightContext
{
    TrafficLightState state;

    TrafficLightContext(TrafficLightState state)
    {
        this.state = state;
    }

    void changeState()
    {
        state.change(this);
    }

    void getCurrentState()
    {
        state.getColor();
    }

    void setState(TrafficLightState state)
    {
        this.state = state;
    }
}

interface TrafficLightState {
    void change(TrafficLightContext context);
    void getColor();
}

class YellowLight implements TrafficLightState
{
    @Override
    public void change(TrafficLightContext context)
    {
        context.setState(new RedLight());
    }

    public void getColor()
    {
        System.out.println("The current color is Yellow");
    }
}

class GreenLight implements TrafficLightState
{
    @Override
    public void change(TrafficLightContext context)
    {
        context.setState(new YellowLight());
    }

    @Override
    public void getColor()
    {
        System.out.println("The current color is Green");
    }
}

class RedLight implements TrafficLightState
{
    @Override
    public void change(TrafficLightContext context)
    {
        context.setState(new GreenLight());
    }

    @Override
    public void getColor()
    {
        System.out.println("The current color is Red");
    }
}

public class StateDesignPattern
{
    public static void main(String[] args)
    {
        TrafficLightContext context = new TrafficLightContext(new RedLight());
        context.getCurrentState();

        context.changeState();
        context.getCurrentState();

        context.changeState();
        context.getCurrentState();

        context.changeState();
        context.getCurrentState();
    }
}
