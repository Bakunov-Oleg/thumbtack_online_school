package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle {
    private Point2D pRectangleLeftTop = null;
    private Point2D pRectangleRightBottom = null;

    public Rectangle(Point2D leftTop, Point2D rightBottom) {
        //Создает Rectangle по координатам углов - левого верхнего и правого нижнего.
        pRectangleLeftTop = new Point2D(leftTop.getX(), leftTop.getY());
        pRectangleRightBottom = new Point2D(rightBottom.getX(), rightBottom.getY());
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        //Создает Rectangle по координатам углов - левого верхнего и правого нижнего.
        this (new Point2D(xLeft, yTop), new Point2D(xRight, yBottom));
    }

    public Rectangle(int length, int width) {
        //Создает Rectangle, левый нижний угол которого находится в начале координат, а длина(по оси X) и ширина (по оси Y)
        //задаются.
        this(0, -width, length, 0);
    }

    public Rectangle() {
        //Создает Rectangle с размерами (1, 1),левый нижний угол которого находится в начале координат.
        this(0, -1, 1, 0);
    }

    public Point2D getTopLeft() {
        //Возвращает левую верхнюю точку Rectangle.
        return pRectangleLeftTop;
    }

    public Point2D getBottomRight() {
        //Возвращает правую нижнюю точку Rectangle.
        return pRectangleRightBottom;
    }

    public void setTopLeft(Point2D topLeft) {
        //Устанавливает левую верхнюю точку Rectangle.
        pRectangleLeftTop.setX(topLeft.getX());
        pRectangleLeftTop.setY(topLeft.getY());
    }

    public void setBottomRight(Point2D bottomRight) {
        //Устанавливает правую нижнюю точку Rectangle.
        pRectangleRightBottom.setX(bottomRight.getX());
        pRectangleRightBottom.setY(bottomRight.getY());
    }

    public int getLength() {
        //Возвращает длину прямоугольника.
        return pRectangleRightBottom.getX() - pRectangleLeftTop.getX();
    }

    public int getWidth() {
        //Возвращает ширину прямоугольника.
        return pRectangleRightBottom.getY() - pRectangleLeftTop.getY();
    }

    public void moveRel(int dx, int dy) {
        //Передвигает Rectangle на(dx, dy).
        pRectangleLeftTop.moveRel(dx, dy);
        pRectangleRightBottom.moveRel(dx, dy);
    }

    public void enlarge(int nx, int ny) {
        //Увеличивает стороны Rectangle в (nx, ny)раз при сохранении координат левой верхней вершины.
        pRectangleRightBottom.moveTo((pRectangleRightBottom.getX() - pRectangleLeftTop.getX()) * nx + pRectangleLeftTop.getX(),
                (pRectangleRightBottom.getY() - pRectangleLeftTop.getY()) * ny + pRectangleLeftTop.getY());
    }

    public double getArea() {
        //Возвращает площадь прямоугольника.
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        //Возвращает периметр прямоугольника.
        return getLength() * 2 + getWidth() * 2;
    }

    public boolean isInside(int x, int y) {
        //Определяет, лежит ли точка (x, y)внутри Rectangle.Если точка лежит на стороне, считается, что она лежит внутри.
        return pRectangleLeftTop.getX() <= x && pRectangleRightBottom.getX() >= x
                && pRectangleLeftTop.getY() <= y && pRectangleRightBottom.getY() >= y;
    }

    public boolean isInside(Point2D point) {
        //Определяет, лежит ли точка point внутри Rectangle.Если точка лежит на стороне, считается, что она лежит внутри.
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {
        //Определяет, пересекается ли Rectangle с другим Rectangle.Считается, что прямоугольники пересекаются, если у них
        //есть хоть одна общая точка.
        return !(pRectangleLeftTop.getY() > rectangle.pRectangleRightBottom.getY() ||
                pRectangleRightBottom.getY() < rectangle.pRectangleLeftTop.getY() ||
                pRectangleRightBottom.getX() < rectangle.pRectangleLeftTop.getX() ||
                pRectangleLeftTop.getX() > rectangle.pRectangleRightBottom.getX());
    }

    public boolean isInside(Rectangle rectangle) {
        //Определяет, лежит ли rectangle целиком внутри текущего Rectangle.
        return isInside(rectangle.pRectangleLeftTop) && isInside(rectangle.pRectangleRightBottom);
    }

    //методы equals и hashCode.
    //Не пишите эти методы сами, используйте средства IDEA.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(pRectangleLeftTop, rectangle.pRectangleLeftTop) &&
                Objects.equals(pRectangleRightBottom, rectangle.pRectangleRightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pRectangleLeftTop, pRectangleRightBottom);
    }

}
