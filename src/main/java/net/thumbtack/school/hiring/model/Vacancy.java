package net.thumbtack.school.hiring.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vacancy implements Comparable {
    private String vacancyName;
    private Integer salary;
    private Set<Requirement> requirement;
    private Employer employer;


    public Vacancy(String vacancyName, Integer salary) {
        this.vacancyName = vacancyName;
        this.salary = salary;
        requirement = new HashSet<>();
    }

    public Vacancy(String vacancyName, Integer salary, Set<Requirement> requirement, Employer employer) {
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

    public void addRequirement(Set<Requirement> requirement) {
        this.requirement.addAll(requirement);
    }

    public Set<Requirement> getRequirement() {
        return requirement;
    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static int compare(Vacancy v1, Vacancy v2) {
        return v1.getVacancyName().compareTo(v2.getVacancyName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(vacancyName, vacancy.vacancyName) &&
                Objects.equals(salary, vacancy.salary) &&
                Objects.equals(requirement, vacancy.requirement) &&
                Objects.equals(employer, vacancy.employer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyName, salary, requirement, employer);
    }
}
