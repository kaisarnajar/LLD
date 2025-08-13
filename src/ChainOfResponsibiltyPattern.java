class LeaveRequest {
    private int noOfDays;
    private String employeeName;

    LeaveRequest(String name, int days) {
        this.noOfDays = days;
        this.employeeName = name;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getNoOfDays() {
        return noOfDays;
    }
}

abstract class LeaveHandler {
    LeaveHandler nextHandler;

    LeaveHandler(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void handleRequest(LeaveRequest leaveRequest);
}

class SuperVisiorHandler extends LeaveHandler {
    SuperVisiorHandler(LeaveHandler leaveHandler) {
        super(leaveHandler);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getNoOfDays() <= 3) {
            System.out.println("Leave handled by Supervisior for employee " + leaveRequest.getEmployeeName());
        } else {
            nextHandler.handleRequest(leaveRequest);
        }
    }
}

class ManagerHandler extends LeaveHandler {
    ManagerHandler(LeaveHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getNoOfDays() <= 10) {
            System.out.println("Leave handled by Manager for employee " + leaveRequest.getEmployeeName());
        } else {
            nextHandler.handleRequest(leaveRequest);
        }
    }
}

class DirectorHandler extends LeaveHandler {
    DirectorHandler(LeaveHandler handler) {
        super(handler);
    }

    @Override
    public void handleRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getNoOfDays() <= 20) {
            System.out.println("Leave handled by Director for employee " + leaveRequest.getEmployeeName());
        } else {
            System.out.println("Leave is Rejected :) for employee " + leaveRequest.getEmployeeName());
        }
    }
}

public class ChainOfResponsibiltyPattern {

    public static void main(String[] args) {
        LeaveRequest leaveRequest1 = new LeaveRequest("Kaisar", 4);
        LeaveRequest leaveRequest2 = new LeaveRequest("Tahir", 10);
        LeaveRequest leaveRequest3 = new LeaveRequest("Sajad", 40);

        DirectorHandler directorHandler = new DirectorHandler(null);
        ManagerHandler managerHandler = new ManagerHandler(directorHandler);
        SuperVisiorHandler superVisiorHandler = new SuperVisiorHandler(managerHandler);

        superVisiorHandler.handleRequest(leaveRequest1);
        superVisiorHandler.handleRequest(leaveRequest2);
        superVisiorHandler.handleRequest(leaveRequest3);


    }
}

