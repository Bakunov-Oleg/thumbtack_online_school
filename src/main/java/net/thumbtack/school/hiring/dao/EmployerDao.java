package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.model.*;

import java.util.List;
import java.util.Set;

public interface EmployerDao {
    public String insert(Employer employer, String token) throws ServerException;

    public Employer changeDataEmployer(Employer employer);

    public Employer getEmployerByToken(String token) throws ServerException;

    public List<Vacancy> addVacancy(String token, Vacancy vacancy) throws ServerException;

    public List<Vacancy> delVacancy(String token, Vacancy vacancy) throws ServerException;

    public Vacancy addVacancySkill(Vacancy vacancy) throws ServerException;

    public Vacancy removeVacancySkill(Vacancy vacancy) throws ServerException;

    public Vacancy changeVacancySkill(Vacancy vacancy) throws ServerException;

    public Set<Employee> getEmployeesSkillsAtRequirementLevel(Set<Requirement> requirements);

    public Set<Employee> getEmployeesRequiredSkillsNotLowerRequiredLevel(Set<Requirement> requirements);//getEmployeesOneSkillPerLevelRequirement(Set<Skill> skills)

    public Set<Employee> getEmployeesAnyoneSkillsAnyLevel(Set<Skill> skills);

    public Set<Employee> getEmployeesOneSkillPerLevelRequirement(Set<Skill> skills);

    public Boolean setVacancyNonActive(String token, Vacancy vacancy) throws ServerException;
    public Boolean setVacancyActive(String token, Vacancy vacancy) throws ServerException;

    //public ArrayList<String> getVacancyActive(String token);
    //public ArrayList<String> getVacancyNonActive(String token);
    //public ArrayList<String> getVacancyAll(String token);


    public Boolean acceptEmployeeToJob(String login) throws ServerException;

}
