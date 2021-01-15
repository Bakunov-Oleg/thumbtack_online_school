package net.thumbtack.school.ttschool;

import java.util.Objects;

public class TrainingException extends Exception {
    private TrainingErrorCode exceptionCode;

    public TrainingException(TrainingErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public TrainingErrorCode getErrorCode() {
        return this.exceptionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingException that = (TrainingException) o;
        return exceptionCode == that.exceptionCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionCode);
    }
}
