package net.thumbtack.school.hiring.dto.request.vacancy;

import net.thumbtack.school.hiring.dto.VacancyDto;

public class ChangeActivityVacancyDtoRequest {
    private String token;
    private VacancyDto vacancyDto;

    public ChangeActivityVacancyDtoRequest(String token, VacancyDto vacancyDto) {
        this.token = token;
        this.vacancyDto = vacancyDto;
    }

    public VacancyDto getVacancy() {
        return vacancyDto;
    }

    public String getToken(){
        return token;
    }

    public boolean validate() {
        return true;
    }
}
