interface Shape {
    double calcArea();
    String calcFillColor();
    String calcBorderColor();

    default double calcPerimeter() {
        return 0;
    }
}

class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String calcFillColor() {
        return fillColor;
    }

    @Override
    public String calcBorderColor() {
        return borderColor;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public String calcFillColor() {
        return fillColor;
    }

    @Override
    public String calcBorderColor() {
        return borderColor;
    }
}

class Triangle implements Shape {
    private double a;
    private double b;
    private double c;
    private String fillColor;
    private String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return a + b + c;
    }

    @Override
    public double calcArea() {
        double p = calcPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String calcFillColor() {
        return fillColor;
    }

    @Override
    public String calcBorderColor() {
        return borderColor;
    }
}

public class MainGeometric {
    public static void main(String[] args) {
        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Зеленый");
        Triangle triangle = new Triangle(3, 4, 5, "Желтый", "Серый");

        System.out.println("Круг:");
        System.out.println("Периметр: " + circle.calcPerimeter());
        System.out.println("Площадь: " + circle.calcArea());
        System.out.println("Цвет фона: " + circle.calcFillColor());
        System.out.println("Цвет границы: " + circle.calcBorderColor());

        System.out.println("\nПрямоугольник:");
        System.out.println("Периметр: " + rectangle.calcPerimeter());
        System.out.println("Площадь: " + rectangle.calcArea());
        System.out.println("Цвет фона: " + rectangle.calcFillColor());
        System.out.println("Цвет границы: " + rectangle.calcBorderColor());

        System.out.println("\nТреугольник:");
        System.out.println("Периметр: " + triangle.calcPerimeter());
        System.out.println("Площадь: " + triangle.calcArea());
        System.out.println("Цвет фона: " + triangle.calcFillColor());
        System.out.println("Цвет границы: " + triangle.calcBorderColor());
    }
}
