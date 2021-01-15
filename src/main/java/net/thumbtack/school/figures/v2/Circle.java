package net.thumbtack.school.figures.v2;

import java.util.Objects;

public class Circle extends Figure {

    private int color;
    private int rad;
    private Point2D pCircle = null;

    public Circle(Point2D center, int radius, int color) {
        //Создает Circle по координатам центра и значению радиуса.
        super(color);
        this.color = getColor();
        pCircle = new Point2D(center.getX(), center.getY());
        rad = radius;
     }

    public Circle(int xCenter, int yCenter, int radius, int color) {
        //Создает Circle по координатам центра и значению радиуса.
        this(new Point2D(xCenter, yCenter), radius, color);
    }

    public Circle(int radius, int color) {
        //Создает Circle с центром в точке (0,0) указанного радиуса.
        this(0, 0, radius, color);
    }

    public Circle(int color) {
        //Создает Circle с центром в точке (0,0) с радиусом 1.
        this(0, 0, 1, color);
    }

    public Circle() {
    }

    public Point2D getCenter() {
        //Возвращает центр Circle.
        return pCircle;
    }

    public int getRadius() {
        //Возвращает радиус Circle.
        return rad;
    }

    public void setCenter(Point2D center) {
        //Устанавливает центр Circle.
        pCircle.moveTo(center.getX(), center.getY());
    }

    public void setRadius(int radius) {
        //Устанавливает радиус Circle.
        rad = radius;
    }

    @Override
    public void moveRel(int dx, int dy) {
        //Передвигает Circle на (dx, dy).
        pCircle.moveRel(dx, dy);
    }

    public void enlarge(int n) {
        //Увеличивает радиус Circle в n раз, не изменяя центра.
        rad = rad * n;
    }

    public double getArea() {
        //Возвращает площадь круга.
        double result;
        result = Math.PI * Math.pow(rad, 2);
        return result;
    }

    public double getPerimeter() {
        //Возвращает периметр окружности (длину окружности).
        double result;
        result = 2 * Math.PI * rad;
        return result;
    }

    public boolean isInside(int x, int y) {
        //Определяет, лежит ли точка (x, y) внутри Circle. Если точка лежит на окружности, считается, что она лежит внутри.
        return Math.pow(x - pCircle.getX(), 2) + Math.pow(y - pCircle.getY(), 2) <= Math.pow(rad, 2);
    }

//    public boolean isInside(Point2D point) {
//        //Определяет, лежит ли точка point внутри Circle. Если точка лежит на окружности, считается, что она лежит внутри.
//        return isInside(point.getX(), point.getY());
//    }

    //методы equals и hashCode.
    //Не пишите эти методы сами, используйте средства IDEA.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return color == circle.color &&
                rad == circle.rad &&
                Objects.equals(pCircle, circle.pCircle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, rad, pCircle);
    }

}
