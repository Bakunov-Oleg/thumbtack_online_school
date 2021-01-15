package net.thumbtack.school.database.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class School {

    private String name;
    private int year;
    private List<Group> groups;
    private int id; //id для School. Для несохраненной в БД School это поле имеет значение 0, после сохранения  значение присваивается БД


    public School() { // Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится, но будет нужен на следующем занятии, поэтому лучше его сразу сделать.

    }

    public School(int id, String name, int year, List<Group> groups) { //Конструктор, устанавливающий значения всех полей
        this.groups = groups;
        setName(name);
        setYear(year);
        setId(id);
    }

    public School(int id, String name, int year) { //Конструктор, устанавливающий значения всех полей. Полю - списку присваивается пустой список (не null!)
        this(id, name, year, new ArrayList<>());
    }

    public School(String name, int year) { //Конструктор, устанавливающий значения всех полей. Полю id присваивается значение 0,  полю - списку - пустой список (не null!)
        this(0, name, year, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public static int compare() {
        return 0;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void removeGroup(String name) {
        for (Group pGroup : groups) {
            if (pGroup.getName().equals(name)) {
                groups.remove(pGroup);
                return;
            }
        }
    }

    public boolean containsGroup(Group group) {
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return getYear() == school.getYear() &&
                getId() == school.getId() &&
                Objects.equals(getName(), school.getName()) &&
                Objects.equals(getGroups(), school.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getYear(), getGroups(), getId());
    }
}
