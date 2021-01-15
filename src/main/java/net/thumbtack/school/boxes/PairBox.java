package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

public class PairBox <T extends HasArea, V extends HasArea> implements HasArea {
    //В этом классе должны быть конструктор,  принимающий пару экземпляров вложенного объекта,
    //геттеры (getContentFirst, getContentSecond ) и сеттеры (setContentFirst,  setContentSecond) для вложенных объектов.
    //Кроме того, этот класс должен имплементировать интерфейс HasArea - метод getArea должен возвращать сумму площадей пары вложенных фигур.
    //Добавьте в класс PairBox метод isAreaEqual, проверяющий, равна ли сумма площадей двух фигур, находящихся в этом PairBox,
    //сумме площадей фигур, вложенных в другой PairBox.
    //Добавьте в класс PairBox статический метод isAreaEqual, проверяющий, равна ли эти суммы у двух разных PairBox.

    private T objFirst;
    private V objSecond;

    public PairBox (T obj1, V obj2){
        super();
        this.objFirst = obj1;
        this.objSecond = obj2;
    }

    public T getContentFirst(){
        return objFirst;
    }

    public V getContentSecond(){
        return objSecond;
    }

    public void setContentFirst(T objFirst){
        this.objFirst = objFirst;
    }

    public void setContentSecond(V objSecond){
        this.objSecond = objSecond;
    }

    public boolean isAreaEqual (PairBox box2){
        return getArea() == box2.getArea();
    }

    public static boolean isAreaEqual (PairBox box1, PairBox box2){
        return box1.getArea() == box2.getArea();
    }

    @Override
    public double getArea() {
        return objFirst.getArea() + objSecond.getArea();
    }
}
