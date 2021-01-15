package net.thumbtack.school.ttschool;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class School {

    private String name;
    private int year;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        //Создает School с указанными значениями полей и пустым множеством групп. Для недопустимых значений входных параметров выбрасывает TrainingException с соответствующим TrainingErrorCode
        checkName(name);
        groups = new HashSet<>();
        this.name = name;
        this.year = year;
    }

    public String getName() {
        //Возвращает название школы.
        return name;
    }

    public void setName(String name) throws TrainingException {
        //Устанавливает название школы. Для недопустимого значения входного параметра выбрасывает TrainingException с TrainingErrorCode.SCHOOL_WRONG_NAME
        checkName(name);
        this.name = name;

    }

    public int getYear() {
        //Возвращает год начала обучения.
        return year;
    }

    public void setYear(int year) {
        //Устанавливает год начала обучения.
        this.year = year;
    }

    public Set<Group> getGroups() {
        //Возвращает список групп
        return groups;
    }

    public void addGroup(Group group) throws TrainingException {
        //Добавляет Group в школу. Если группа с таким именем уже есть, выбрасывает TrainingException с  TrainingErrorCode.DUPLICATE_GROUP_NAME
        for (Group pGroup : groups) {
            if (pGroup.getName().equals(group.getName())) {
                throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
            }
        }
        groups.add(group);
    }

    public void removeGroup(Group group) throws TrainingException {
        //Удаляет Group из школы. Если такой Group в школе нет, выбрасывает TrainingException с TrainingErrorCode.GROUP_NOT_FOUND
        if (!groups.remove(group)) throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public void removeGroup(String name) throws TrainingException {
        //Удаляет Group с данным названием из школы. Если группа с таким названием не найдена, выбрасывает TrainingException с TrainingErrorCode.GROUP_NOT_FOUND
        for (Group pGroup : groups) {
            if (pGroup.getName().equals(name)) {
                groups.remove(pGroup);
                return;
            }
        }
        throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public boolean containsGroup(Group group) {
        //Определяет, есть ли в школе группа с таким названием.
        return groups.contains(group);
    }

    private void checkName(String name) throws TrainingException {
        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        }
    }

    //Методы equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                Objects.equals(name, school.name) &&
                Objects.equals(groups, school.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, groups);
    }
}
