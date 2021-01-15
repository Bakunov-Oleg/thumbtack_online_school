package net.thumbtack.school.hiring.dto.request.employer;

public class GetEmployerByTokenDtoRequest {
    private String token;

    public GetEmployerByTokenDtoRequest(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Boolean validate(){
        return true;
    }
}
