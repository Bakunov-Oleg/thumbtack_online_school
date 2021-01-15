package net.thumbtack.school.database.model;

import java.util.Objects;

public class Trainee {
    private int id;
    private String firstName;
    private String lastName;
    private int rating;
    private Integer group_id;


    public Trainee() { //Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится, но будет нужен на следующем занятии, поэтому лучше его сразу сделать.
    }

    public Trainee(int id, String firstName, String lastName, int rating) { //Конструктор, устанавливающий значения всех полей
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);
        setId(id);
        setGroupId(null);

    }

    public Trainee(String firstName, String lastName, int rating) { //Конструктор, устанавливающий значения всех полей , Полю id присваивается значение 0.
        this(0, firstName, lastName, rating);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        //Устанавливает имя учащегося. Для недопустимого значения входного параметров выбрасывает TrainingException с TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME
        this.firstName = firstName;
    }

    public Integer getGroup_id() {
        return group_id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGroupId() {
        return group_id;
    }

    public void setGroupId(Integer groupId) {
        this.group_id = groupId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;
        Trainee trainee = (Trainee) o;
        return getId() == trainee.getId() &&
                getRating() == trainee.getRating() &&
                Objects.equals(getFirstName(), trainee.getFirstName()) &&
                Objects.equals(getLastName(), trainee.getLastName()) &&
                Objects.equals(getGroup_id(), trainee.getGroup_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getRating(), getGroup_id());
    }
}
