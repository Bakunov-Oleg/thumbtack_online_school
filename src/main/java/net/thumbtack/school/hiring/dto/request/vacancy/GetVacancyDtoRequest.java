package net.thumbtack.school.hiring.dto.request.vacancy;

public class GetVacancyDtoRequest {
    private String token;

    public  GetVacancyDtoRequest (String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public boolean validate() {
        return true;
    }
}
