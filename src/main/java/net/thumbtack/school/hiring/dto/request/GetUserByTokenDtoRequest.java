package net.thumbtack.school.hiring.dto.request;

public class GetUserByTokenDtoRequest {
    private String token;

    public GetUserByTokenDtoRequest(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Boolean validate(){
        return true;
    }
}
