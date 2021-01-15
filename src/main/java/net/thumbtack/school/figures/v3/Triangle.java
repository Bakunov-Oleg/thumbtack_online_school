package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.util.Objects;

public class Triangle extends Figure implements HasArea {

    private Point2D pTrianglePoint1 = null;
    private Point2D pTrianglePoint2 = null;
    private Point2D pTrianglePoint3 = null;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, Color color) throws ColorException {
        //Создает Triangle по координатам трех точек.
        super(color);
        pTrianglePoint1 = new Point2D(point1.getX(), point1.getY());
        pTrianglePoint2 = new Point2D(point2.getX(), point2.getY());
        pTrianglePoint3 = new Point2D(point3.getX(), point3.getY());
    }

    public Triangle(Point2D point1, Point2D point2, Point2D point3, String colorString) throws ColorException {
        //Создает Triangle по координатам трех точек.
        this(point1, point2, point3, Color.colorFromString(colorString));
    }

    public Point2D getPoint1() {
        //Возвращает точку 1 треугольника.
        return pTrianglePoint1;
    }

    public Point2D getPoint2() {
        //Возвращает точку 2 треугольника.
        return pTrianglePoint2;
    }

    public Point2D getPoint3() {
        //Возвращает точку 3 треугольника.
        return pTrianglePoint3;
    }

    public void setPoint1(Point2D point) {
        //Устанавливает точку 1 треугольника.
        pTrianglePoint1.moveTo(point.getX(), point.getY());
    }

    public void setPoint2(Point2D point) {
        //Устанавливает точку 2 треугольника.
        pTrianglePoint2.moveTo(point.getX(), point.getY());
    }

    public void setPoint3(Point2D point) {
        //Устанавливает точку 3 треугольника.
        pTrianglePoint3.moveTo(point.getX(), point.getY());
    }

    public double getSide12() {
        //Возвращает длину стороны 1 - 2.
        return Math.sqrt(Math.pow(pTrianglePoint2.getX() - pTrianglePoint1.getX(), 2) + Math.pow(pTrianglePoint2.getY() - pTrianglePoint1.getY(), 2));
    }

    public double getSide13() {
        //Возвращает длину стороны 1 - 3.
        return Math.sqrt(Math.pow(pTrianglePoint3.getX() - pTrianglePoint1.getX(), 2) + Math.pow(pTrianglePoint3.getY() - pTrianglePoint1.getY(), 2));
    }

    public double getSide23() {
        //Возвращает длину стороны 2 - 3.
        return Math.sqrt(Math.pow(pTrianglePoint3.getX() - pTrianglePoint2.getX(), 2) + Math.pow(pTrianglePoint3.getY() - pTrianglePoint2.getY(), 2));
    }

    @Override
    public void moveRel(int dx, int dy) {
        //Передвигает Triangle на(dx, dy).
        pTrianglePoint1.moveRel(dx, dy);
        pTrianglePoint2.moveRel(dx, dy);
        pTrianglePoint3.moveRel(dx, dy);
    }

    public double getArea() {
        //Возвращает площадь треугольника.
        double p;
        p = (getSide12() + getSide13() + getSide23()) / 2;
        return Math.sqrt(p * (p - getSide12()) * (p - getSide23()) * (p - getSide13()));
    }

    public double getPerimeter() {
        //Возвращает периметр треугольника.
        return getSide12() + getSide13() + getSide23();
    }

    public boolean isInside(int x, int y) {
        //Определяет, лежит ли точка (x, y)внутри Triangle.Если точка лежит на стороне треугольника, считается, что
        //она лежит внутри.
        Point2D point = new Point2D();
        point.moveTo(x, y);

        return twoPointOfLine(pTrianglePoint1, pTrianglePoint2, pTrianglePoint3, point) &&
                twoPointOfLine(pTrianglePoint2, pTrianglePoint3, pTrianglePoint1, point) &&
                twoPointOfLine(pTrianglePoint3, pTrianglePoint1, pTrianglePoint2, point);
    }

    public double pointOfLine(Point2D point1, Point2D point2, Point2D point) {
        return (point.getX() - point1.getX()) * (point2.getY() - point1.getY()) - (point.getY() - point1.getY()) * (point2.getX() - point1.getX());
    }

    public boolean twoPointOfLine(Point2D point1, Point2D point2, Point2D point3, Point2D point4) {
        return pointOfLine(point1, point2, point3) * pointOfLine(point1, point2, point4) >= 0;
    }

    public boolean isInside(Point2D point) {
        //Определяет, лежит ли точка point внутри Triangle.Если точка лежит на стороне треугольника, считается, что
        //она лежит внутри.
        return isInside(point.getX(), point.getY());
    }

    //методы equals и hashCode.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(pTrianglePoint1, triangle.pTrianglePoint1) &&
                Objects.equals(pTrianglePoint2, triangle.pTrianglePoint2) &&
                Objects.equals(pTrianglePoint3, triangle.pTrianglePoint3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pTrianglePoint1, pTrianglePoint2, pTrianglePoint3);
    }
}
