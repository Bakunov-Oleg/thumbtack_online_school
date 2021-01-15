package net.thumbtack.school.hiring.dto.response.employer;

public class RegisterEmployerDtoResponse {

    private String token;

    public RegisterEmployerDtoResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}

