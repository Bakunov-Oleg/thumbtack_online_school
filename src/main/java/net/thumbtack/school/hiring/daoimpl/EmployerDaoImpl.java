package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dao.EmployerDao;
import net.thumbtack.school.hiring.database.Database;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.model.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Set;

public class EmployerDaoImpl implements EmployerDao {
    @Override
    public String insert(Employer employer, String token) throws ServerException {
        return Database.getDatabase().insertUser((User)employer, token);
    }

    @Override
    public Employer changeDataEmployer(Employer employer) {
        return Database.getDatabase().changeDataEmployer(employer);
    }

    @Override
    public Employer getEmployerByToken(String token) throws ServerException {
        return Database.getDatabase().getEmployerByToken(token);
    }

    @Override
    public List<Vacancy> addVacancy(String token, Vacancy vacancy) throws ServerException {
        return Database.getDatabase().addVacancy(token, vacancy);
    }

    @Override
    public List<Vacancy> delVacancy(String token, Vacancy vacancy) throws ServerException {
        return Database.getDatabase().delVacancy(token, vacancy);
    }

    @Override
    public Vacancy addVacancySkill(Vacancy vacancy) throws ServerException {
        return Database.getDatabase().addSkillVacancy(vacancy);
    }

    @Override
    public Vacancy removeVacancySkill(Vacancy vacancy) throws ServerException {
        return Database.getDatabase().delSkillVacancy(vacancy);
    }

    @Override
    public Vacancy changeVacancySkill(Vacancy vacancy) throws ServerException {
        return Database.getDatabase().changeSkillVacancy(vacancy);
    }

    @Override
    public Set<Employee> getEmployeesSkillsAtRequirementLevel(Set<Requirement> requirements) {
        return Database.getDatabase().getEmployeesSkillsAtRequirementLevel(requirements);
    }

    @Override
    public Set<Employee> getEmployeesRequiredSkillsNotLowerRequiredLevel(Set<Requirement> requirements) {
        return Database.getDatabase().getEmployeesRequiredSkillsNotLowerRequiredLevel(requirements);
    }

    @Override
    public Set<Employee> getEmployeesAnyoneSkillsAnyLevel(Set<Skill> skills) {
        return Database.getDatabase().getEmployeesAnyoneSkillsAnyLevel(skills);
    }

    @Override
    public Set<Employee> getEmployeesOneSkillPerLevelRequirement(Set<Skill> skills) {
        return Database.getDatabase().getEmployeesOneSkillPerLevelRequirement(skills);
    }

    @Override
    public Boolean setVacancyNonActive(String token, Vacancy vacancy) throws ServerException {
        return Database.getDatabase().setVacancyActive(token, vacancy);
    }

    @Override
    public Boolean setVacancyActive(String token, Vacancy vacancy) throws ServerException {
        return Database.getDatabase().setVacancyNonActive(token, vacancy);
    }

    @Override
    public Boolean acceptEmployeeToJob(String login) throws ServerException {
        return Database.getDatabase().acceptEmployeeToJob(login);
    }


//
//    @Override
//    public String addVacancy(String token, vacancy vacancy) {
//        Database.getDatabase().addVacancy(token, vacancy.getVacancyName(), vacancy.getSalary(), vacancy.getSkillsLevel(), vacancy.getSkillsRequired());
//        return null;
//    }
//
//    @Override
//    public String setVacancyActive(String token, String vacancyName) {
//        Database.getDatabase().setVacancyActive(token, vacancyName);
//        return null;
//    }
//
//    @Override
//    public String setVacancyNonActive(String token, String vacancyName) {
//        Database.getDatabase().setVacancyNonActive(token, vacancyName);
//        return null;
//    }
//
//    @Override
//    public String delVacancy(String token, String vacancyName) {
//        Database.getDatabase().delVacancy(token, vacancyName);
//        return null;
//    }
//
//    @Override
//    public ArrayList<String> getVacancyActive(String token) {
//        return Database.getDatabase().getVacancyActive(token);
//    }
//
//    @Override
//    public ArrayList<String> getVacancyNonActive(String token) {
//        return Database.getDatabase().getVacancyNonActive(token);
//    }
//
//    @Override
//    public ArrayList<String> getVacancyAll(String token) {
//        return Database.getDatabase().getVacancyAll(token);
//    }

//    @Override
//    public String addVacancySkill(String token, String vacancyName, Map<String, Integer> skillsLevel, Map<String, Boolean> skillsRequired) {
//        return null;
//    }
//
//    @Override
//    public String removeVacancySkill(String token, String vacancyName, Map<String, Integer> skillsLevel, Map<String, Boolean> skillsRequired) {
//        return null;
//    }
//
//    @Override
//    public String changeVacancySkill(String token, String vacancyName, Map<String, Integer> skillsLevel, Map<String, Boolean> skillsRequired) {
//        return null;
//    }
//
//    @Override
//    public String acceptEmployeeToJob(String token, String employeeLogin, String vacancyName) {
//        return null;
//    }
//
//    @Override
//    public String getEmployeesSkillsAtRequirementLevel(vacancy vacancy) {
//        return null;
//    }
//
//    @Override
//    public String getEmployeesRequiredSkillsNotLowerRequiredLevel(vacancy vacancy) {
//        return null;
//    }
//
//    @Override
//    public String getEmployeesAnyoneSkillsAnyLevel(vacancy vacancy) {
//        return null;
//    }
//
//    @Override
//    public String getEmployeesOneSkillPerLevelRequirement(vacancy vacancy) {
//        return null;
//    }
}
