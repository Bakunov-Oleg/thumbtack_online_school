package net.thumbtack.school.hiring.dto.response.employee;

public class RegisterEmployeeDtoResponse {
    private String token;

    public RegisterEmployeeDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}