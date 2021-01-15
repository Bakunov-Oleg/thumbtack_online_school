package net.thumbtack.school.hiring;

public enum ServerErrorCode {
    NULL_REQUEST_STRING("Пустая строка запроса"),
    JSON_SYNTAX_EXCEPTION("Синтаксическая ошибка JSON"),
    REQUEST_NOT_VALIDE("Невалидный запрос"),
    LOGIN_ALREADY_USED("Данный логин уже существует"),
    EMPLOYEE_BY_LOGIN_IS_EMPLOYER("Под данным логином зарегистрирован работодатель"),
    EMPLOYER_BY_LOGIN_IS_EMPLOYEE("Под данным логином зарегистрирован работник"),
    EMPLOYEE_BY_TOKEN_IS_EMPLOYER("Под данным токеном зарегистрирован работодатель"),
    EMPLOYER_BY_TOKEN_IS_EMPLOYEE("Под данным токеном зарегистрирован работник"),
    INCORRECT_LOGIN("Данный логин не существует"),
    INCORRECT_TOKEN("Данный токен не существует"),
    SKILL_NO_EXIST("Изменяемый навык не существует");

    private String errorString;

    private ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}
