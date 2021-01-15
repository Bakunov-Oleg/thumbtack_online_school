package net.thumbtack.school.figures.v2;

abstract class Figure implements Colored {

    private int color;

    public Figure(int color) {
        setColor(color);
    }

    protected Figure() {
    }

    @Override
    public void setColor(int color) { //FIXME форматирование
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    abstract public void moveRel(int dx, int dy);

    abstract public double getArea();

    abstract public double getPerimeter();

    abstract public boolean isInside(int x, int y);

    public boolean isInside(Point2D point){
        return isInside(point.getX(), point.getY());
    };
}

