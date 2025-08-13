interface Shape {
    void findArea();
}

class Rectangle implements Shape {

    @Override
    public void findArea() {
        System.out.println("Rectangle");
    }
}

class Circle implements Shape {

    @Override
    public void findArea() {
        System.out.println("Circle");
    }
}

class Triangle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Triangle");
    }
}

class ShapeFactory {
    public static Shape createShape(String shapeType) {
        return switch (shapeType) {
            case "Circle" -> new Circle();
            case "Rectangle" -> new Rectangle();
            case "Triangle" -> new Triangle();
            default -> null;
        };
    }
}

public class FactoryDesignPattern {
    public static void main(String[] args) {

        Shape circle = ShapeFactory.createShape("Circle");
        Shape triangle = ShapeFactory.createShape("Triangle");
        Shape rectangle = ShapeFactory.createShape("Rectangle");

        circle.findArea();
        triangle.findArea();
        rectangle.findArea();

    }
}
