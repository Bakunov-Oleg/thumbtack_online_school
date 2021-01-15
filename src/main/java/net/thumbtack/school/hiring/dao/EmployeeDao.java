package net.thumbtack.school.hiring.dao;

import com.google.common.collect.TreeMultimap;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Vacancy;

import java.util.Set;

public interface EmployeeDao {
    public String insert(Employee employee, String token) throws ServerException;

    public Set<Skill> addSkills(Employee employee, Set<SkillDto> skills);

    public Set<Skill> delSkills(Employee employee, Set<SkillDto> skills);

    public Set<Skill> changeLevelSkills(Employee employee, Set<SkillDto> skills) throws ServerException;

    public Boolean setEmployeeActive(String token) throws ServerException;

    public Boolean setEmployeeNonActive(String token) throws ServerException;

    public Employee changeDataEmployee(Employee employee, Employee newEmployee);

    public Employee getEmployeeByToken(String token) throws ServerException;

    public Set<Vacancy> getVacanciesSkillsAtRequirementLevel(Set<Skill> skills);

    public Set<Vacancy> getVacanciesRequiredSkillsNotLowerRequiredLevel(Set<Skill> skills);

    public Set<Vacancy> getVacanciesAnyoneSkillsAnyLevel(Set<Skill> skills);

    public TreeMultimap<Integer, Vacancy> getVacanciesSortedByNumberOfSkills(Set<Skill> skills);
}
