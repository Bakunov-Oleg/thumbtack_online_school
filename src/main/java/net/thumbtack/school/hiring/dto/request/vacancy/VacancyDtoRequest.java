package net.thumbtack.school.hiring.dto.request.vacancy;

import net.thumbtack.school.hiring.dto.RequirementDto;

import java.util.Set;

public class VacancyDtoRequest {
    private String vacancyName;
    private Integer salary;
    private String token;
    private Set<RequirementDto> requirement;


    public VacancyDtoRequest(String token, String vacancyName, Integer salary, Set<RequirementDto> requirement) {
        this.token = token;
        this.vacancyName = vacancyName;
        this.salary = salary;
        this.requirement = requirement;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public boolean validate() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public Integer getSalary() {
        return salary;
    }

    public Set<RequirementDto> getRequirement() {
        return requirement;
    }
}
