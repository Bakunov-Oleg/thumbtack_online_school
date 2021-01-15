package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorErrorCode;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import java.util.Objects;

public abstract class Figure implements Colored, HasArea {
    private Color color;

    public Figure(Color color) throws ColorException {
        setColor(color);
    }

    protected Figure() {
    }

    @Override
    public void setColor(Color color) throws ColorException {
        //Устанавливает цвет фигуры
        if (color == null){
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
            this.color = color;
    }

    @Override
    public Color getColor() {
        //Возвращает цвет фигуры
        return this.color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        setColor(Color.colorFromString(colorString));
    }

    //Общие, но разная сигнатура
    abstract public void moveRel(int dx, int dy);

    abstract public double getArea();

    abstract public double getPerimeter();

    abstract public boolean isInside(int x, int y);

    abstract public boolean isInside(Point2D point);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}

