package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

public class Box <T extends Figure> implements HasArea{

    private T item;

    public Box (T obj){
        this.item = obj;
    }

    public void setContent (T obj){
        this.item = obj;
    }

    public T getContent(){
        return item;
    }

    public boolean isAreaEqual(Box<? extends Figure> box1){
        return item.getArea() == box1.getArea();
    }

    static public boolean isAreaEqual(Box<? extends Figure> box1, Box<? extends Figure> box2){
        return box1.isAreaEqual(box2);
    }

    @Override
    public double getArea() {
        return item.getArea();
    }
}
