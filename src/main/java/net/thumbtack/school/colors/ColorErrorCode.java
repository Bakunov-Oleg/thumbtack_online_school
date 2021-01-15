package net.thumbtack.school.colors;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Указан неверный цвет"),
    NULL_COLOR("Цвет isNULL");

    private String errorString;

    private ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}

