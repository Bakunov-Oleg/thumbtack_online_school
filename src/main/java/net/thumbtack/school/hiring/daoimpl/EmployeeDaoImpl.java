package net.thumbtack.school.hiring.daoimpl;

import com.google.common.collect.TreeMultimap;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.database.Database;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.User;
import net.thumbtack.school.hiring.model.Vacancy;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public String insert(Employee employee, String token) throws ServerException {
        return Database.getDatabase().insertUser((User) employee, token);
    }

    @Override
    public Set<Skill> addSkills(Employee employee, Set<SkillDto> skillsDto) {
        Set<Skill> skills = new HashSet<>();
        for (SkillDto skill : skillsDto){
            skills.add(new Skill(skill.getName(), skill.getLevel()));
        }
        return Database.getDatabase().addSkillsEmployee(employee, skills);
    }

    @Override
    public Set<Skill> delSkills(Employee employee, Set<SkillDto> skillsDto) {
        Set<Skill> skills = new HashSet<>();
        for (SkillDto skill : skillsDto){
            skills.add(new Skill(skill.getName(), skill.getLevel()));
        }
        return Database.getDatabase().delSkillsEmployee(employee, skills);
    }

    @Override
    public Set<Skill> changeLevelSkills(Employee employee, Set<SkillDto> skillsDto) throws ServerException {
        Set<Skill> skills = new HashSet<>();
        for (SkillDto skill : skillsDto){
            skills.add(new Skill(skill.getName(), skill.getLevel()));
        }
        return Database.getDatabase().changeLevelSkillsEmployee(employee, skills);
    }

    @Override
    public Boolean setEmployeeActive(String token) throws ServerException {
        return Database.getDatabase().setEmployeeActive(token);
    }

    @Override
    public Boolean setEmployeeNonActive(String token) throws ServerException {
        return Database.getDatabase().setEmployeeNonActive(token);
    }

    @Override
    public Employee changeDataEmployee(Employee employee, Employee newEmployee) {
        return Database.getDatabase().changeDataEmployee(employee, newEmployee);
    }

    @Override
    public Employee getEmployeeByToken(String token) throws ServerException {
        return Database.getDatabase().getEmployeeByToken(token);
    }

    @Override
    public Set<Vacancy> getVacanciesSkillsAtRequirementLevel(Set<Skill> skills) { //Ваканасии, уровень соответствует требуемому.
        return Database.getDatabase().getVacanciesSkillsAtRequirementLevel(skills);
    }

    @Override
    public Set<Vacancy> getVacanciesRequiredSkillsNotLowerRequiredLevel(Set<Skill> skills) {//Ваканасии, уровень соответствует ОБЯЗАТЕЛЬНОМУ требуемому.
        return Database.getDatabase().getVacanciesRequiredSkillsNotLowerRequiredLevel(skills);
    }

    @Override
    public Set<Vacancy> getVacanciesAnyoneSkillsAnyLevel(Set<Skill> skills) { //Вакансии, для которых его набор умений соответствует требованиям работодателя на любом уровне
        return Database.getDatabase().getVacanciesAnyoneSkillsAnyLevel(skills);
    }

    @Override
    public TreeMultimap<Integer, Vacancy> getVacanciesSortedByNumberOfSkills(Set<Skill> skills) {//Вакансии отсортированные по числу умений, в начале списка вакансии, для которых работник имеет большее число умений.
        return Database.getDatabase().getVacanciesSortedByNumberOfSkills(skills);
    }
}
