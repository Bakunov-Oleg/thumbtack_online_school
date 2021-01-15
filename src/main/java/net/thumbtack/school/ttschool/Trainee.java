package net.thumbtack.school.ttschool;

import java.io.Serializable;
import java.util.Objects;

public class Trainee implements Serializable {
    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
        //Создает Trainee с указанными значениями полей.Для недопустимых значений входных параметров выбрасывает
        //TrainingException с соответствующим TrainingErrorCode
        checkFirstName(firstName);
        checkLastName(lastName);
        checkRating(rating);
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);
    }

    public String getFirstName() {
        //Возвращает имя учащегося
        return firstName;
    }

    public void setFirstName(String firstName) throws TrainingException {
        //Устанавливает имя учащегося. Для недопустимого значения входного параметров выбрасывает TrainingException с TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME
        checkFirstName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        //Возвращает фамилию учащегося
        return lastName;
    }

    public void setLastName(String lastName) throws TrainingException {
        //Устанавливает фамилию учащегося. Для недопустимого значения входного параметров выбрасывает TrainingException с TrainingErrorCode.TRAINEE_WRONG_LASTNAME
        checkLastName(lastName);
        this.lastName = lastName;
    }

    public int getRating() {
        //Возвращает оценку учащегося
        return rating;
    }

    public void setRating(int rating) throws TrainingException {
        //Устанавливает оценку учащегося. Для недопустимого значения входного параметров выбрасывает TrainingException с TrainingErrorCode.TRAINEE_WRONG_RATING
        checkRating(rating);
        this.rating = rating;
    }

    public String getFullName() {
        //Возвращает полное имя учащегося (имя и фамилию, разделенные пробелом)
        return firstName + " " + lastName;
    }

    private void checkFirstName(String firstName) throws TrainingException {
        if (firstName == null || firstName.equals("")) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }
    }

    private void checkLastName(String lastName) throws TrainingException {
        if (lastName == null || lastName.equals("")) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }
    }

    private void checkRating(int rating) throws TrainingException {
        if (rating < 1 || rating > 5) throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
    }

    //Методы equals и hashCode
    //Не пишите эти методы сами, используйте средства IDEA.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return rating == trainee.rating &&
                Objects.equals(firstName, trainee.firstName) &&
                Objects.equals(lastName, trainee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, rating);
    }
}
