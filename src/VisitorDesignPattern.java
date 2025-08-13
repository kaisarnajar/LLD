abstract class Patient {
    abstract void accept(Visitor visitor);
}

class ChildPatient extends Patient {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class AdultPatient extends Patient {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class SeniorPatient extends Patient {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(ChildPatient childPatient);

    void visit(AdultPatient adultPatient);

    void visit(SeniorPatient seniorPatient);
}

class BillingVisitor implements Visitor {
    @Override
    public void visit(ChildPatient childPatient) {
        System.out.println("Visit done by billing visitor to childPatient");
    }

    @Override
    public void visit(AdultPatient adultPatient) {
        System.out.println("Visit done by billing person to adultPatient");
    }

    @Override
    public void visit(SeniorPatient seniorPatient) {
        System.out.println("Visit done by billing persont to Senior Patient");
    }
}

class ReceptionVistor implements Visitor {
    @Override
    public void visit(ChildPatient childPatient) {
        System.out.println("Visit done by Recetion visitor to childPatient");
    }

    @Override
    public void visit(AdultPatient adultPatient) {
        System.out.println("Visit done by Recpetion Visitor to adultPatient");
    }

    @Override
    public void visit(SeniorPatient seniorPatient) {
        System.out.println("Visit done by Recpetion Visitor to Senior Patient");
    }
}

public class VisitorDesignPattern {
    public static void main(String[] args) {
        ChildPatient childPatient = new ChildPatient();
        AdultPatient adultPatient = new AdultPatient();
        SeniorPatient seniorPatient = new SeniorPatient();


        ReceptionVistor receptionVistor = new ReceptionVistor();
        BillingVisitor billingVisitor = new BillingVisitor();

        childPatient.accept(receptionVistor);
        childPatient.accept(billingVisitor);

        adultPatient.accept(receptionVistor);
        adultPatient.accept(billingVisitor);

        seniorPatient.accept(receptionVistor);
        seniorPatient.accept(billingVisitor);
    }
}

