package net.thumbtack.school.boxes;

import net.thumbtack.school.figures.v3.Figure;

public class NamedBox <T extends Figure> extends Box<T>{
    //Этот класс должен быть наследником класса Box.
    // Конструктор класса должен принимать дополнительно текстовую строку - имя вложенного объекта.
    // В классе также должны быть следующие методы : геттер (getName) и сеттер (setName) для имени вложенного объекта.
    private String name;

    public NamedBox(T obj, String name) {
        super(obj);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
