package net.thumbtack.school.hiring.dto.request;

public class GetUserByLoginDtoRequest {
    private String login;

    public GetUserByLoginDtoRequest(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Boolean validate(){
        return true;
    }
}
