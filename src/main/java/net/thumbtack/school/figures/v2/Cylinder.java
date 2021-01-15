package net.thumbtack.school.figures.v2;

import java.util.Objects;

public class Cylinder extends Circle {

    private int height;

    public Cylinder(Point2D center, int radius, int height, int color) {
        //Создает Cylinder по координатам центра, значению радиуса и высоте.
        super(center, radius, color);
        this.height = height;
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height, int color) {
        //Создает Cylinder по координатам центра,
        //значению радиуса и высоте.
        this(new Point2D(xCenter, yCenter), radius, height, color);
    }

    public Cylinder(int radius, int height, int color) {
        //Создает Cylinder с центром в точке (0, 0)с указанными радиусом и высотой.
        this(0, 0, radius, height, color);
    }

    public Cylinder(int color) {
        //Создает Cylinder с центром в точке (0, 0)с радиусом 1 и высотой 1.
        this(0, 0, 1, 1, color);
    }

    public int getHeight() {
        //Возвращает высоту Cylinder.
        return height;
    }

    public void setHeight(int height) {
        //Устанавливает высоту Cylinder.
        this.height = height;
    }

    public double getVolume() {
        //Возвращает объем цилиндра.
        return getArea() * height;
    }

    public boolean isInside(int x, int y, int z) {
        //Определяет, лежит ли точка (x, y, z)внутри Cylinder.Если точка лежит на поверхности, считается, что она лежит
        //внутри.
        return super.isInside(x, y) && (z <= height);
    }

    public boolean isInside(Point3D point) {
        //Определяет, лежит ли точка point внутри Cylinder.Если точка лежит на поверхности, считается, что она лежит
        //внутри.
        return isInside(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cylinder cylinder = (Cylinder) o;
        return height == cylinder.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }
}
