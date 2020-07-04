package ru.stqa.pft.sandbox;

public class Point {

    public int x, y; //координаты осей X и Y

    // задаем параметры Point в конструкторе. Параметры равны координатам оси

    public Point (int x, int y) {
        this.y = y;
        this.x = x;
    }

    // добавляем метод в класс, считает дистанцию между точками
    public double distance(Point p2) {
        return Math.sqrt((p2.y - this.y) * (p2.y - this.y) + (p2.x - this.x) * (p2.x - this.x));
    }
}

