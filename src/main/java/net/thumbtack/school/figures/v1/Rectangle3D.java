package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle3D extends Rectangle{

    int height;

    public Rectangle3D(Point2D leftTop, Point2D rightBottom, int height) {
        //Создает Rectangle3D по координатам углов основания (левого верхнего и правого нижнего)и высотой.
        super(leftTop, rightBottom);
        this.height = height;
    }

    public Rectangle3D(int xLeft, int yTop, int xRight, int yBottom, int height) {
        //Создает Rectangle3D по координатам углов основания (левого верхнего и правого нижнего)и высотой.
        this(new Point2D(xLeft, yTop), new Point2D(xRight, yBottom), height);
     }

    public Rectangle3D(int length, int width, int height) {
        //Создает Rectangle3D, левый нижний угол которого находится в начале координат, а длина, ширина и высота задаются.
        this(0, -width, length, 0, height);
    }

    public Rectangle3D() {
        //Создает Rectangle3D с размерами (1, 1, 1),левый нижний угол которого находится в начале координат.
        this(0, -1, 1, 0, 1);
    }

    public int getHeight() {
        //Возвращает высоту параллелепипеда.
        return height;
    }

    public void setHeight(int height) {
        //Устанавливает высоту параллелепипеда.
        this.height = height;
    }

    public double getVolume() {
        //Возвращает объем параллелепипеда.
        return getArea() * getHeight();
    }

    public boolean isInside(int x, int y, int z) {
        //Определяет, лежит ли точка (x, y, z)внутри Rectangle3D.Если точка лежит на стороне, считается, что она лежит
        //внутри.
        return isInside(x, y) && z <= height;
    }

    public boolean isInside(Point3D point) {
        //Определяет, лежит ли точка point внутри Rectangle3D.Если точка лежит на стороне, считается, что она лежит
        //внутри.
        return isInside((Point2D)point) && point.getZ() <= height;
    }

    public boolean isIntersects(Rectangle3D rectangle) {
        //Определяет, пересекается ли Rectangle3D с другим Rectangle3D.Считается, что параллелепипеды пересекаются, если
        //у них есть хоть одна общая точка.
        return super.isIntersects((Rectangle)rectangle);
    }

    public boolean isInside(Rectangle3D rectangle) {
        //Определяет, лежит ли rectangle3D целиком внутри текущего Rectangle3D.
        return super.isInside((Rectangle) rectangle) && height == rectangle.getHeight();
    }

    //методы equals и hashCode.
    //Не пишите эти методы сами, используйте средства IDEA.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle3D that = (Rectangle3D) o;
        return height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }
}
