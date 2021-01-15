package net.thumbtack.school.hiring.database;


import com.google.common.collect.TreeMultimap;
import net.thumbtack.school.hiring.ServerErrorCode;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.TypeUser;
import net.thumbtack.school.hiring.model.*;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.io.Serializable;
import java.util.*;

public final class Database implements Serializable {

    private static Database database;

    private BidiMap<String, User> userByToken = new DualHashBidiMap<>();
    private Map<String, User> userByLogin = new HashMap<>();

    private SortedSet<Skill> allSkills = new TreeSet<>(Skill::compare);
    private TreeMultimap<Skill, Employee> employeeBySkill = TreeMultimap.create(Skill::compare, User::compare);
    private TreeMultimap<String, Vacancy> vacancyBySkillName = TreeMultimap.create(String::compareTo, Vacancy::compare);
    private TreeMultimap<Boolean, Vacancy> vacancyByStatusActivity = TreeMultimap.create(Boolean::compareTo, Vacancy::compare);
    private TreeMultimap<Boolean, Employee> employeeByStatusActivity = TreeMultimap.create(Boolean::compareTo, Employee::compare);

    public Database() {
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private void addUserByToken(String token, User user) {
        userByToken.put(token, user);
    }

    private void addUserByLogin(String login, User user) throws ServerException {
        if (userByLogin.putIfAbsent(login, user) != null) throw new ServerException(ServerErrorCode.LOGIN_ALREADY_USED);
    }

    public String insertUser(User user, String token) throws ServerException {
        addUserByLogin(user.getLogin(), user);
        addUserByToken(token, user);
        return userByToken.getKey(user);
    }

    public Employee getEmployeeByLogin(String login) throws ServerException {
        try {
            if (userByLogin.get(login).getTypeUser() == TypeUser.Employee) {
                return (Employee) userByLogin.get(login);
            } else {
                throw new ServerException(ServerErrorCode.EMPLOYEE_BY_LOGIN_IS_EMPLOYER);
            }
        } catch (NullPointerException ex) {
            throw new ServerException(ServerErrorCode.INCORRECT_LOGIN);
        }
    }

    public Employee getEmployeeByToken(String token) throws ServerException {

        if (userByToken.get(token) == null) throw new ServerException(ServerErrorCode.INCORRECT_TOKEN);
        if (userByToken.get(token).getTypeUser() == TypeUser.Employee) {
            return (Employee) userByToken.get(token);
        } else {
            throw new ServerException(ServerErrorCode.EMPLOYEE_BY_TOKEN_IS_EMPLOYER);
        }
    }

    public Boolean setEmployeeActive(String token) throws ServerException {
        Employee employee = getEmployeeByToken(token);
        employee.setProfileActive();
        employeeByStatusActivity.put(true, employee);
        employeeByStatusActivity.remove(false, employee);
        return employeeByStatusActivity.get(true).contains(employee);
    }

    public Boolean setEmployeeNonActive(String token) throws ServerException {
        Employee employee = getEmployeeByToken(token);
        employee.setProfileNonActive();
        employeeByStatusActivity.put(false, employee);
        employeeByStatusActivity.remove(true, employee);
        return employeeByStatusActivity.get(false).contains(employee);
    }

    public Employer getEmployerByLogin(String login) throws ServerException {
        if (userByLogin.get(login) == null) throw new ServerException(ServerErrorCode.INCORRECT_LOGIN);
        if (userByLogin.get(login).getTypeUser() == TypeUser.Employer) {
            return (Employer) userByLogin.get(login);
        } else {
            throw new ServerException(ServerErrorCode.EMPLOYER_BY_LOGIN_IS_EMPLOYEE);
        }
    }

    public Employer getEmployerByToken(String token) throws ServerException {
        if (userByToken.get(token) == null) throw new ServerException(ServerErrorCode.INCORRECT_TOKEN);
        if (userByToken.get(token).getTypeUser() == TypeUser.Employer) {
            return (Employer) userByToken.get(token);
        } else {
            throw new ServerException(ServerErrorCode.EMPLOYER_BY_TOKEN_IS_EMPLOYEE);
        }
    }

    public Boolean acceptEmployeeToJob(String employeeLogin) throws ServerException {
        return setEmployeeNonActive(getEmployeeByLogin(employeeLogin).getToken());
    }

    public NavigableSet<Employee> getEmployeesBySkill(Skill skill) {
        return employeeBySkill.get(skill);
    }

    public Set<Skill> addSkillsEmployee(Employee employee, Set<Skill> skills) {
        for (Skill skill : skills) {
            employeeBySkill.put(skill, (Employee) userByToken.get(employee.getToken()));

        }
        ((Employee) userByToken.get(employee.getToken())).addSkills(skills);
        allSkills.addAll(skills);
        return ((Employee) userByToken.get(employee.getToken())).getSkills();
    }

    public Set<Skill> delSkillsEmployee(Employee employee, Set<Skill> skills) {
        for (Skill skill : skills) {
            employeeBySkill.remove(skill.getName(), userByToken.get(employee.getToken()));
        }
        ((Employee) userByToken.get(employee.getToken())).delSkills(skills);

        return ((Employee) userByToken.get(employee.getToken())).getSkills();
    }

    public Set<Skill> changeLevelSkillsEmployee(Employee employee, Set<Skill> skills) throws ServerException {
        employee = getEmployeeByToken(employee.getToken());
        employee.changeLevelSkills(skills);

        return ((Employee) userByToken.get(employee.getToken())).getSkills();
    }

    public Employee changeDataEmployee(Employee employee, Employee newEmployee) {

        addSkillsEmployee(employee, newEmployee.getSkills());

        userByToken.replace(employee.getToken(), newEmployee);
        userByLogin.replace(employee.getLogin(), newEmployee);

        return (Employee) userByToken.get(employee.getToken());
    }

    public Employer changeDataEmployer(Employer employer) {
        userByToken.replace(employer.getToken(), employer);
        userByToken.replace(employer.getLogin(), employer);

        return (Employer) userByToken.get(employer.getToken());
    }

    public List<Vacancy> addVacancy(String token, Vacancy vacancy) throws ServerException {
        Employer employer = getEmployerByToken(token);
        employer.addVacancy(vacancy);
        setVacancyActive(token, vacancy);
        for (Requirement requirement : vacancy.getRequirement()) {
            vacancyBySkillName.put(requirement.getName(), vacancy);
            allSkills.add(new Skill(requirement.getName(), requirement.getLevel()));
        }

        return getEmployerByToken(token).getVacancies();
    }

    public List<Vacancy> delVacancy(String token, Vacancy vacancy) throws ServerException {
        Employer employer = getEmployerByToken(token);
        employer.delVacancy(vacancy);
        setVacancyNonActive(token, vacancy);
        vacancyByStatusActivity.remove(true, vacancy);
        vacancyByStatusActivity.remove(false, vacancy);
        for (Requirement requirement : vacancy.getRequirement()) {
            vacancyBySkillName.remove(requirement.getName(), vacancy);
        }
        return getEmployerByToken(token).getVacancies();
    }

    public Vacancy addSkillVacancy(Vacancy vacancy) throws ServerException {
        Set<String> skillNameVacancy = new HashSet<>();
        for (Requirement requirement : vacancy.getRequirement()) {
            vacancyBySkillName.put(requirement.getName(), vacancy);
            skillNameVacancy.add(requirement.getName());
        }

        for (String skill : skillNameVacancy) {
            if (!vacancyBySkillName.get(skill).contains(vacancy)) {
                return null;
            }
        }
        return vacancy;
    }

    public Vacancy delSkillVacancy(Vacancy vacancy) throws ServerException {
        Vacancy newVacancy;
        NavigableSet<Vacancy> setVacancy = new TreeSet<>();
        Set<String> skillNameVacancy = new HashSet<>();
        for (Requirement req : vacancy.getRequirement()) {
            setVacancy.addAll(vacancyBySkillName.get(req.getName()));
            skillNameVacancy.add(req.getName());
        }
        for (Vacancy vacancy1 : setVacancy) {
            if (vacancy.getVacancyName().equals(vacancy1.getVacancyName())) {
                for (Requirement req : vacancy.getRequirement()) {
                    vacancyBySkillName.remove(req.getName(), vacancy1);
                }
            }
        }
        for (String skill : skillNameVacancy) {
            if (vacancyBySkillName.get(skill).contains(vacancy)) {
                return null;
            }
        }
        return vacancy;
    }

    public Vacancy changeSkillVacancy(Vacancy vacancy) throws ServerException {
        NavigableSet<Vacancy> setVacancy = new TreeSet<>();
        Set<String> skillNameVacancy = new HashSet<>();
        for (Requirement req : vacancy.getRequirement()) {
            setVacancy.addAll(vacancyBySkillName.get(req.getName()));
        }
        for (Vacancy vacancy1 : setVacancy) {
            if (vacancy.getVacancyName().equals(vacancy1.getVacancyName())) {
                for (Requirement req : vacancy.getRequirement()) {
                    vacancyBySkillName.remove(req.getName(), vacancy1);
                    vacancyBySkillName.put(req.getName(), vacancy);
                }
            }
        }
        for (Requirement req : vacancy.getRequirement()) {
            if (!vacancyBySkillName.get(req.getName()).contains(vacancy)) {
                return null;
            }

        }
        return vacancy;
    }

    public Boolean setVacancyActive(String token, Vacancy vacancy) throws ServerException {
        Employer employer = getEmployerByToken(token);
        employer.setVacanciesActive(vacancy);
        vacancyByStatusActivity.put(true, vacancy);
        vacancyByStatusActivity.remove(false, vacancy);
        return vacancyByStatusActivity.get(true).contains(vacancy);
    }

    public Boolean setVacancyNonActive(String token, Vacancy vacancy) throws ServerException {
        Employer employer = getEmployerByToken(token);
        employer.setVacanciesNonActive(vacancy);
        vacancyByStatusActivity.remove(true, vacancy);
        vacancyByStatusActivity.put(false, vacancy);
        return vacancyByStatusActivity.get(false).contains(vacancy);
    }

    public Set<Employee> getEmployeesSkillsAtRequirementLevel(Set<Requirement> requirements) { //необходимые требоная, на необходимом уровне
        Set<Employee> employees = new HashSet<>();
        Set<Skill> skillsVacancy = new HashSet<>();
        SortedSet<Skill> subSkillsVacancy = new TreeSet<>(Skill::compare);
        SortedSet<Skill> subSkillsEmployee = new TreeSet<>(Skill::compare);

        for (Requirement requirement : requirements) {
            subSkillsVacancy.addAll(employeeBySkill.keySet().subSet(new Skill(requirement.getName(), requirement.getLevel()), new Skill(requirement.getName(), 6)));
            subSkillsVacancy.add(new Skill(requirement.getName(), requirement.getLevel()));
            skillsVacancy.add(new Skill(requirement.getName(), requirement.getLevel()));
        }

        for (Employee emoloyee : getEmployeeBySkillSet(subSkillsVacancy)) {
            subSkillsEmployee.clear();

            for (Skill skill : emoloyee.getSkills()) {
                subSkillsEmployee.addAll(subSkillsVacancy.subSet(new Skill(skill.getName(), 0), new Skill(skill.getName(), skill.getLevel() + 1)));
            }

            if (subSkillsEmployee.containsAll(skillsVacancy)) {
                employees.add((Employee) emoloyee);
            }
        }
        return employees;
    }

    public Set<Employee> getEmployeeBySkillSet(Set<Skill> skills) {
        Set<Employee> employees = new HashSet<>();

        for (Skill skill : skills) {
            employees.addAll(employeeBySkill.get(skill));
            for(Skill subskill : employeeBySkill.keySet().subSet(skill, new Skill(skill.getName(), 6))){
                employees.addAll(employeeBySkill.get(subskill));
            }
        }
        return employees;
    }

    public Set<Employee> getEmployeesRequiredSkillsNotLowerRequiredLevel(Set<Requirement> skills) { //обязательные требования на уровне не ниже необходимого
        Set<Requirement> requrementSkill = new HashSet<>();
        for (Requirement skill : skills) {
            if (skill.getRequired() == true) {
                requrementSkill.add(new Requirement(skill.getName(), skill.getLevel(), skill.getRequired()));
            }
        }
        if (requrementSkill.isEmpty() == false) {
            return getEmployeesSkillsAtRequirementLevel(requrementSkill);
        }
        return null;
    }

    public Set<Employee> getEmployeesAnyoneSkillsAnyLevel(Set<Skill> skills) { //Необходимые требования на любом уровне
        Set<Requirement> skillAtAnyLevel = new HashSet<>();
        for (Skill skill : skills) {
            skillAtAnyLevel.add(new Requirement(skill.getName(), 0, false));
        }
        if (skillAtAnyLevel.isEmpty() == false) {
            return getEmployeesSkillsAtRequirementLevel(skillAtAnyLevel);
        }
        return null;
    }

    public Set<Employee> getEmployeesOneSkillPerLevelRequirement(Set<Skill> skills) {//Одно умение на уровне не ниже требуемого
        Set<Employee> employees = new HashSet<>();
        SortedSet<Skill> subSkills = new TreeSet<>(Skill::compare);
        SortedSet<Skill> subSkillsEmployee = new TreeSet<>(Skill::compare);
        for (Skill skill : skills) {
            subSkills.addAll(employeeBySkill.keySet().subSet(skill, new Skill(skill.getName(), 6)));
        }
        subSkills.addAll(skills);

        for (Employee employee : getEmployeeBySkillSet(subSkills)) {
            subSkillsEmployee.clear();
            for (Skill skill : employee.getSkills()) {
                if (subSkills.contains(skill)) {
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    //--список всех вакансий работодателей, для которых его набор умений соответствует требованиям работодателя на уровне не ниже уровня, указанного в требовании
    public Set<Vacancy> getVacanciesSkillsAtRequirementLevel(Set<Skill> skills) {
        Set<Vacancy> vacancies = new HashSet<>();
        SortedSet<Skill> subSkillsEmployee = new TreeSet<>(Skill::compare);
        Set<Skill> tempSkillsVacancy = new HashSet<>();

        for (Skill skill : skills) {
              subSkillsEmployee.addAll(allSkills.subSet(new Skill(skill.getName(), 0), skill));
          }
        subSkillsEmployee.addAll(skills);

        for (Skill skill : subSkillsEmployee) {
            vacancies.addAll(vacancyBySkillName.get(skill.getName()));
        }

        for (Vacancy vacancy : vacancies) {
            tempSkillsVacancy.clear();
            for (Requirement requirement : vacancy.getRequirement()) {
                tempSkillsVacancy.add(new Skill(requirement.getName(), requirement.getLevel()));
            }
            if (!subSkillsEmployee.containsAll(tempSkillsVacancy)) {
                if(vacancies.contains(vacancy)) vacancies.remove(vacancy);
            }
        }

        return vacancies;
    }

    //getVacanciesRequiredSkillsNotLowerRequiredLevel
    //--список всех вакансий работодателей, для которых его набор умений соответствует ОБЯХАТЕЛЬНЫМ требованиям работодателя на уровне не ниже уровня, указанного в требовании
    public Set<Vacancy> getVacanciesRequiredSkillsNotLowerRequiredLevel(Set<Skill> skills) {
        Set<Vacancy> vacancies = new HashSet<>();
        SortedSet<Skill> subSkillsEmployee = new TreeSet<>(Skill::compare);
        Set<Skill> tempSkillsVacancy = new HashSet<>();

        for (Skill skill : skills) {
            subSkillsEmployee.addAll(allSkills.subSet(new Skill(skill.getName(), 0), skill));
        }
        subSkillsEmployee.addAll(skills);

        for (Skill skill : subSkillsEmployee) {
            vacancies.addAll(vacancyBySkillName.get(skill.getName()));
        }

        for (Vacancy vacancy : vacancies) {
            tempSkillsVacancy.clear();
            for (Requirement requirement : vacancy.getRequirement()) {
                if (requirement.getRequired() == true) {
                    tempSkillsVacancy.add(new Skill(requirement.getName(), requirement.getLevel()));
                }
            }
            if (!subSkillsEmployee.containsAll(tempSkillsVacancy)) {
                vacancies.remove(vacancy);
            }
        }

        return vacancies;
    }

    //--список всех вакансий работодателей, для которых его набор умений соответствует требованиям работодателя на любом уровне
    public Set<Vacancy> getVacanciesAnyoneSkillsAnyLevel(Set<Skill> skills) {
        Set<Skill> newSkills = new HashSet<>();
        for (Skill skill : skills) {
            newSkills.add(new Skill(skill.getName(), 5));
        }
        return getVacanciesSkillsAtRequirementLevel(newSkills);
    }

    // В этом случае список выдается отсортированным по числу найденных умений, то есть в начале списка приводятся те вакансии работодателей, для которых работник имеет большее число умений.
    public TreeMultimap<Integer, Vacancy> getVacanciesSortedByNumberOfSkills(Set<Skill> skills) {
        TreeMultimap<Integer, Vacancy> vacancySort = TreeMultimap.create(Integer::compareTo, Vacancy::compareTo);
        Set<Vacancy> vacancies = new HashSet<>();
        SortedSet<Skill> subSkillsEmployee = new TreeSet<>(Skill::compare);
        Set<Skill> tempSkillsVacancy = new HashSet<>();
        List<Vacancy> sortedVacancyes = new ArrayList<>();

        for (Skill skill : skills) {
            subSkillsEmployee.addAll(allSkills.subSet(new Skill(skill.getName(), 0), skill));
        }
        subSkillsEmployee.addAll(skills);

        for (Skill skill : subSkillsEmployee) {
            vacancies.addAll(vacancyBySkillName.get(skill.getName()));
        }

        int countSkills = 0;
        for (Vacancy vacancy : vacancies) {
            tempSkillsVacancy.clear();
            for (Requirement requirement : vacancy.getRequirement()) {
                if (subSkillsEmployee.contains(new Skill(requirement.getName(), requirement.getLevel()))){
                    countSkills++;
                };
            }
            if (countSkills > 0){
                vacancySort.put(countSkills, vacancy);
            }
            countSkills = 0;
        }

        return vacancySort;
    }


}