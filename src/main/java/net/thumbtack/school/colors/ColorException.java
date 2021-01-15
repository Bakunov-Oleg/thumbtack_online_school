package net.thumbtack.school.colors;

import java.util.Objects;

public class ColorException extends Exception {

    private ColorErrorCode exceptionCode;

    //конструктор, принимающий enum ColorErrorCode и геттер (getErrorCode) для него.
    public ColorException(ColorErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ColorErrorCode getErrorCode() {
        return this.exceptionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorException that = (ColorException) o;
        return exceptionCode == that.exceptionCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionCode);
    }
}
