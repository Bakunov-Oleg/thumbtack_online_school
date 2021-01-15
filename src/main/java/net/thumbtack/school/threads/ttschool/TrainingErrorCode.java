package net.thumbtack.school.threads.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Имя не может быть пустым"),
    TRAINEE_WRONG_LASTNAME("Фамилия не может быть пустой"),
    TRAINEE_WRONG_RATING("Оценака должна быть от 1 до 4"),
    GROUP_WRONG_NAME("Имя группы не может быть пустым"),
    GROUP_WRONG_ROOM("Название группы не может быть пустым"),
    TRAINEE_NOT_FOUND("Нет такого студента"),
    SCHOOL_WRONG_NAME("Имя школы не может быть пустым"),
    DUPLICATE_GROUP_NAME("Такая группа уже существует"),
    GROUP_NOT_FOUND("Группа не существует"),
    DUPLICATE_TRAINEE("Дубликат Trainee"),
    EMPTY_TRAINEE_QUEUE("Очередь пуста");

    private String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}