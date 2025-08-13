class TV {

    //int volume = 10;

    public void turnOnTV() {
        System.out.println("Turn the TV ON");
    }

    public void turnOffTV() {
        System.out.println("Turn the TV Off");
    }

    public void increaseVolume() {
        System.out.println("Increase the Volume");
    }

    public void decreaseVolume() {
        System.out.println("Decrease the volume");
    }
}

interface Command {
    void execute();

    void undo();
}

class TurnONTVCommand implements Command {
    TV tv;

    TurnONTVCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOffTV();
    }

    @Override
    public void undo() {
        tv.turnOffTV();
    }
}

class TurnOffTVCommand implements Command {
    TV tv;

    TurnOffTVCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOffTV();
    }

    @Override
    public void undo() {
        tv.turnOnTV();
    }
}

class IncreaseVolumeCommand implements Command {
    TV tv;

    IncreaseVolumeCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.increaseVolume();
    }

    @Override
    public void undo() {
        tv.decreaseVolume();
    }
}

class DecreaseVolumeCommand implements Command {
    TV tv;

    DecreaseVolumeCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.decreaseVolume();
    }

    @Override
    public void undo() {
        tv.increaseVolume();
    }
}

class RemoteControl {
    private Command command;

    RemoteControl(Command command) {
        this.command = command;
    }

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }

    void pressUndoButton() {
        command.undo();
    }
}

public class CommandDesignPattern {
    public static void main(String[] args) {
        TV tv = new TV();
        TurnONTVCommand turnONTVCommand = new TurnONTVCommand(tv);
        TurnOffTVCommand turnOffTVCommand = new TurnOffTVCommand(tv);
        IncreaseVolumeCommand increaseVolumeCommand = new IncreaseVolumeCommand(tv);
        DecreaseVolumeCommand decreaseVolumeCommand = new DecreaseVolumeCommand(tv);

        RemoteControl remoteControl = new RemoteControl(turnONTVCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOffTVCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(increaseVolumeCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(decreaseVolumeCommand);
        remoteControl.pressButton();
    }
}
