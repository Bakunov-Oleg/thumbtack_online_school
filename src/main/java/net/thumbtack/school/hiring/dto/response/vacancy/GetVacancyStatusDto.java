package net.thumbtack.school.hiring.dto.response.vacancy;

public class GetVacancyStatusDto {
    private Boolean statusVacancy;

    public GetVacancyStatusDto(Boolean statusVacancy){
        this.statusVacancy = statusVacancy;
    }

    public Boolean getStatusVacancy(){
        return statusVacancy;
    }

    public Boolean validate(){
        return true;
    }
}
