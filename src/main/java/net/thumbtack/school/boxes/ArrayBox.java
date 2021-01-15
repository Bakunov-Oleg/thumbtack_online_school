package net.thumbtack.school.boxes;

public class ArrayBox <T>{

    private T[] items;

    public ArrayBox (T[] items){
        this.items = items;
    }

    public void setContent (T[] obj){
        this.items = obj;
    }

    public T[] getContent(){
        return items;
    }

    public Object getElement(int i){
        return items[i];
    }

    public void setElement(int i, T obj){
        this.items[i] = obj;
    }

    public boolean isSameSize(ArrayBox box){
       return items.length == box.items.length;
    }
}
