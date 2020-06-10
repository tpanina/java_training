package ru.stqa.pft.sandbox;

public class Point {

    public double x, y; //координаты осей X и Y

    // задаем параметры Point в конструкторе. Параметры равны координатам оси

    public Point (double x, double y) {
        this.y = y;
        this.x = x;
    }

    // добавляем метод в класс, считает дистанцию между точками
    public double distance(Point p1, Point p2) {
        return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }
}

