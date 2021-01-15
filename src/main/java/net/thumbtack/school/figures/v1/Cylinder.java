package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Cylinder extends Circle{

    private int height;

    public Cylinder(Point2D center, int radius, int height) {
        //Создает Cylinder по координатам центра, значению радиуса и высоте.
        super(center, radius);
        this.height = height;
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height) {
        //Создает Cylinder по координатам центра,
        //значению радиуса и высоте.
        this(new Point2D(xCenter, yCenter), radius, height);
    }

    public Cylinder(int radius, int height) {
        //Создает Cylinder с центром в точке (0, 0)с указанными радиусом и высотой.
        this(0, 0, radius, height);
    }

    public Cylinder() {
        //Создает Cylinder с центром в точке (0, 0)с радиусом 1 и высотой 1.
        this(0, 0, 1, 1);
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
        return super.isInside((Point2D)point) && point.getY() <= height;
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
