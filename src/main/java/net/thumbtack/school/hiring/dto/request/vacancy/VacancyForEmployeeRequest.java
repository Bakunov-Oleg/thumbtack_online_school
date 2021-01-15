package net.thumbtack.school.hiring.dto.request.vacancy;

public class VacancyForEmployeeRequest {
    private String token;

    public VacancyForEmployeeRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
