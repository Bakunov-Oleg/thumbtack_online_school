package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.model.Employer;

import java.util.HashSet;
import java.util.Set;

public class VacancyDto implements Comparable {
    private String vacancyName;
    private Integer salary;
    private Set<RequirementDto> requirement;
    private Employer employer;


    public VacancyDto(String vacancyName, Integer salary) {
        this.vacancyName = vacancyName;
        this.salary = salary;
        requirement = new HashSet<>();
    }
    public VacancyDto(String vacancyName, Integer salary, Set<RequirementDto> requirement, Employer employer){
        this.vacancyName = vacancyName;
        this.salary = salary;
        this.employer = employer;
        this.requirement = requirement;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void addRequirement(Set<RequirementDto> requirement) {
        this.requirement.addAll(requirement);
    }

    public Set<RequirementDto> getRequirement() {
        return requirement;
    }

    public Employer getEmployer(){
        return employer;
    }

    public void setEmployer(Employer employer){
        this.employer = employer;
    }

    public Boolean validate(){
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}