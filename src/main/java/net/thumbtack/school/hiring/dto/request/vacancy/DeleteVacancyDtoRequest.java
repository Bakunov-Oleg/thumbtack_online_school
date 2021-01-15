package net.thumbtack.school.hiring.dto.request.vacancy;

public class DeleteVacancyDtoRequest {
    private String token;
    private String vacancyName;

    public DeleteVacancyDtoRequest(String token, String vacancyName){
        this.token = token;
        this.vacancyName = vacancyName;
    }

    public String getToken(){
        return token;
    }

    public String getVacancyName(){
        return vacancyName;
    }

    public boolean validate() {
        return true;
    }
}
