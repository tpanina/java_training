package ru.stqa.pft.sandbox;

public class Point {

    int x; //координата оси X
    int y; //координата оси Y

    // задаем параметры в конструкторе. Параметры равны координатам оси
    Point(int x, int y) {
        this.y=y;
        this.x=x;
    }

    // добавляем метод в класс
    public double distance() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
}
