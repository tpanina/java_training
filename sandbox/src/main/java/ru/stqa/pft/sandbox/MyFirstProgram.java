package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello ("Vasya");
        hello ("Josephina");
        hello ("Bob");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

        Point p1 = new Point(1,2);
        Point p2 = new Point(2,3);
        System.out.println("Расстояние между точкой p1 с координатами x = " + p1.x + " и y = " + p1.y + " и точкой p2 с координатами x = " + p2.x + " и y = " + p2.y + " равно " + p1.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(Square s) {
        return s.l * s.l;
    }

    public static double area(Rectangle r) {
        return r.a * r.b;
    }

} 