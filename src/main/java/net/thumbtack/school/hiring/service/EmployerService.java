package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hiring.ServerErrorCode;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dao.EmployerDao;
import net.thumbtack.school.hiring.daoimpl.EmployerDaoImpl;
import net.thumbtack.school.hiring.dto.EmployeeDto;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.dto.VacancyDto;
import net.thumbtack.school.hiring.dto.request.GetUserByLoginDtoRequest;
import net.thumbtack.school.hiring.dto.request.SkillSetDto;
import net.thumbtack.school.hiring.dto.request.employer.DataEmployerRequest;
import net.thumbtack.school.hiring.dto.request.employer.GetEmployerByTokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.employer.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.ChangeActivityVacancyDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.RequirementsSetDto;
import net.thumbtack.school.hiring.dto.request.vacancy.VacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hiring.dto.response.GetUserStatusDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.getEmployesDtoResponse;
import net.thumbtack.school.hiring.dto.response.employer.DataEmployerResponse;
import net.thumbtack.school.hiring.dto.response.employer.RegisterEmployerDtoResponse;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyDtoResponce;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyStatusDto;
import net.thumbtack.school.hiring.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.thumbtack.school.hiring.ServerErrorCode.*;

public class EmployerService {
    private static final EmployerDao employerDao = new EmployerDaoImpl();
    private static final Gson gson = new Gson();

    public EmployerService() {
    }

    public String registerEmployer(String requestJsonString) throws ServerException {
        RegisterEmployerDtoRequest regReq = classFromJson(requestJsonString, RegisterEmployerDtoRequest.class);

        if (regReq.validate()) {
            Employer employer = new Employer(regReq.getCompanyName(), regReq.getAdress(), regReq.getEMail(), regReq.getSurname(), regReq.getName(), regReq.getMiddleName(), regReq.getLogin(), regReq.getPassword(), regReq.getToken());
            RegisterEmployerDtoResponse regResponce = new RegisterEmployerDtoResponse(employerDao.insert(employer, employer.getToken()));
            return gson.toJson(regResponce);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String changeDataEmployer(String stringJson) throws ServerException {
        DataEmployerRequest dataReq = classFromJson(stringJson, DataEmployerRequest.class);
        if (dataReq.validate()) {
            Employer employer = new Employer(dataReq.getCompanyName(), dataReq.getAdress(), dataReq.getEMail(), dataReq.getSurname(), dataReq.getName(), dataReq.getMiddleName(),
                    dataReq.getLogin(), dataReq.getPassword(), dataReq.getToken());
            Employer newEmployer = employerDao.changeDataEmployer(employer);
            DataEmployerResponse dataResp = new DataEmployerResponse(newEmployer.getToken(), newEmployer.getSurname(), newEmployer.getName(), newEmployer.getMiddleName(), newEmployer.getEMail(), newEmployer.getLogin(), newEmployer.getPassword(), newEmployer.getCompanyName(), newEmployer.getAdress());
            return gson.toJson(dataResp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String addVacancy(String requestJsonString) throws ServerException {
        VacancyDtoRequest addVacancyReq = classFromJson(requestJsonString, VacancyDtoRequest.class);
        if (addVacancyReq.validate()) {
            Vacancy vacancy;
            List<Vacancy> vacancyList = new ArrayList<>();
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            Set<Requirement> requirements = new HashSet<>();
            for (RequirementDto requirementDto : addVacancyReq.getRequirement()) {
                requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
            }
            vacancy = new Vacancy(addVacancyReq.getVacancyName(), addVacancyReq.getSalary());
            vacancy.addRequirement(requirements);
            vacancyList = employerDao.addVacancy(addVacancyReq.getToken(), vacancy);
            for (Vacancy vacancy1 : vacancyList) {
                vacancyDtoSet.add(vacancyToVacancyDto(vacancy1));
            }
            GetVacancyDtoResponce resp = new GetVacancyDtoResponce(vacancyDtoSet);
            return gson.toJson(resp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String delVacancy(String requestJsonString) throws ServerException {
        VacancyDtoRequest delVac = classFromJson(requestJsonString, VacancyDtoRequest.class);
        if (delVac.validate()) {
            Vacancy vacancy;
            List<Vacancy> vacancies = new ArrayList<>();
            Set<VacancyDto> vacanciesDto = new HashSet<>();
            Set<Requirement> requirements = new HashSet<>();
            for (RequirementDto requirementDto : delVac.getRequirement()) {
                requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
            }
            vacancy = new Vacancy(delVac.getVacancyName(), delVac.getSalary());
            vacancies = employerDao.delVacancy(delVac.getToken(), vacancy);
            for (Vacancy vacancy1 : vacancies) {
                vacanciesDto.add(vacancyToVacancyDto(vacancy1));
            }
            GetVacancyDtoResponce resp = new GetVacancyDtoResponce(vacanciesDto);
            return gson.toJson(resp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String addVacancySkill(String requestJsonString) throws ServerException {
        VacancyDtoRequest skillVacancyReq = classFromJson(requestJsonString, VacancyDtoRequest.class);
        if (skillVacancyReq.validate()) {
            Vacancy vacancy;
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            Set<Requirement> requirements = new HashSet<>();
            for (RequirementDto requirementDto : skillVacancyReq.getRequirement()) {
                requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
            }
            vacancy = new Vacancy(skillVacancyReq.getVacancyName(), skillVacancyReq.getSalary());
            vacancy.addRequirement(requirements);
            vacancyDtoSet.add(vacancyToVacancyDto(employerDao.addVacancySkill(vacancy)));

            GetVacancyDtoResponce resp = new GetVacancyDtoResponce(vacancyDtoSet);
            return gson.toJson(resp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String removeVacancySkill(String requestJsonString) throws ServerException {
        VacancyDtoRequest skillVacancyReq = classFromJson(requestJsonString, VacancyDtoRequest.class);
        if (skillVacancyReq.validate()) {
            Vacancy vacancy;
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            Set<Requirement> requirements = new HashSet<>();
            for (RequirementDto requirementDto : skillVacancyReq.getRequirement()) {
                requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
            }
            vacancy = new Vacancy(skillVacancyReq.getVacancyName(), skillVacancyReq.getSalary());
            vacancy.addRequirement(requirements);
            vacancyDtoSet.add(vacancyToVacancyDto(employerDao.removeVacancySkill(vacancy)));

            GetVacancyDtoResponce resp = new GetVacancyDtoResponce(vacancyDtoSet);
            return gson.toJson(resp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String changeVacancySkill(String requestJsonString) throws ServerException {
        VacancyDtoRequest skillVacancyReq = classFromJson(requestJsonString, VacancyDtoRequest.class);
        if (skillVacancyReq.validate()) {
            Vacancy vacancy;
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            Set<Requirement> requirements = new HashSet<>();
            for (RequirementDto requirementDto : skillVacancyReq.getRequirement()) {
                requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
            }
            vacancy = new Vacancy(skillVacancyReq.getVacancyName(), skillVacancyReq.getSalary());
            vacancy.addRequirement(requirements);
            vacancyDtoSet.add(vacancyToVacancyDto(employerDao.changeVacancySkill(vacancy)));

            GetVacancyDtoResponce resp = new GetVacancyDtoResponce(vacancyDtoSet);
            return gson.toJson(resp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String getEmployeesSkillsAtRequirementLevel(String requestJsonString) throws ServerException {
        Set<Employee> employees;
        Set<EmployeeDto> employeesDto = new HashSet<>();
        Set<Requirement> requirements = new HashSet<>();
        RequirementsSetDto request = classFromJson(requestJsonString, RequirementsSetDto.class);
        if (request.validate()) {
            requirements = requirementDtoToRequirementSet(request.getRequirement());
            employees = employerDao.getEmployeesSkillsAtRequirementLevel(requirements);
            employeesDto = employeesToEmployeesDtoSet(employees);
        }
        getEmployesDtoResponse res = new getEmployesDtoResponse(employeesDto);
        return gson.toJson(res);
    }

    public String getEmployeesRequiredSkillsNotLowerRequiredLevel(String requestJsonString) throws ServerException {
        Set<Employee> employees;
        Set<EmployeeDto> employeesDto = new HashSet<>();
        Set<Requirement> requirements = new HashSet<>();
        RequirementsSetDto request = classFromJson(requestJsonString, RequirementsSetDto.class);
        if (request.validate()) {
            requirements = requirementDtoToRequirementSet(request.getRequirement());
            employees = employerDao.getEmployeesRequiredSkillsNotLowerRequiredLevel(requirements);
            employeesDto = employeesToEmployeesDtoSet(employees);
        }
        getEmployesDtoResponse res = new getEmployesDtoResponse(employeesDto);
        return gson.toJson(res);
    }

    public String getEmployeesAnyoneSkillsAnyLevel(String requestJsonString) throws ServerException {
        Set<Employee> employees;
        Set<EmployeeDto> employeesDto = new HashSet<>();
        Set<Skill> skills = new HashSet<>();
        SkillSetDto request = classFromJson(requestJsonString, SkillSetDto.class);
        if (request.validate()) {
            for (SkillDto skillDto : request.getSkillsDto()) {
                skills.add(new Skill(skillDto.getName(), skillDto.getLevel()));
            }
            employees = employerDao.getEmployeesAnyoneSkillsAnyLevel(skills);
            employeesDto = employeesToEmployeesDtoSet(employees);
        }
        getEmployesDtoResponse res = new getEmployesDtoResponse(employeesDto);
        return gson.toJson(res);
    }

    public String getEmployeesOneSkillPerLevelRequirement(String requestJsonString) throws ServerException {
        Set<Employee> employees;
        Set<EmployeeDto> employeesDto = new HashSet<>();
        Set<Skill> skills = new HashSet<>();
        SkillSetDto request = classFromJson(requestJsonString, SkillSetDto.class);
        if (request.validate()) {
            for (SkillDto skillDto : request.getSkillsDto()) {
                skills.add(new Skill(skillDto.getName(), skillDto.getLevel()));
            }
            employees = employerDao.getEmployeesOneSkillPerLevelRequirement(skills);
            employeesDto = employeesToEmployeesDtoSet(employees);
        }
        getEmployesDtoResponse res = new getEmployesDtoResponse(employeesDto);
        return gson.toJson(res);
    }

    public String setVacancyActive(String requestJsonString) throws ServerException {
        ChangeActivityVacancyDtoRequest request = classFromJson(requestJsonString, ChangeActivityVacancyDtoRequest.class);
        Vacancy vacancy;
        Boolean statusVacancy;
        if (request.validate()) {
            vacancy = vacancyDtoToVacancy(request.getVacancy());
            statusVacancy = employerDao.setVacancyActive(request.getToken(), vacancy);
            GetVacancyStatusDto response = new GetVacancyStatusDto(statusVacancy);
            return gson.toJson(response);
        } else {
            return "JSON ERROR".toString();
        }
    }

    public String setVacancyNonActive(String requestJsonString) throws ServerException {
        ChangeActivityVacancyDtoRequest request = classFromJson(requestJsonString, ChangeActivityVacancyDtoRequest.class);
        Vacancy vacancy;
        Boolean statusVacancy;
        if (request.validate()) {
            vacancy = vacancyDtoToVacancy(request.getVacancy());
            statusVacancy = employerDao.setVacancyNonActive(request.getToken(), vacancy);
            GetVacancyStatusDto response = new GetVacancyStatusDto(!statusVacancy);
            return gson.toJson(response);
        } else {
            return "JSON ERROR".toString();
        }
    }

    public String getVacancyActive(String requestJsonString) throws ServerException {
        GetEmployerByTokenDtoRequest request = classFromJson(requestJsonString, GetEmployerByTokenDtoRequest.class);
        if (request.validate()) {
            Employer employer = employerDao.getEmployerByToken(request.getToken());
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            for (Vacancy vacancy : employer.getVacanciesActive()) {
                vacancyDtoSet.add(vacancyToVacancyDto(vacancy));
            }
            return gson.toJson(new GetVacancyDtoResponce(vacancyDtoSet));
        } else {
            return "JSON ERROR".toString();
        }
    }

    public String getVacancyNonActive(String requestJsonString) throws ServerException {
        GetEmployerByTokenDtoRequest request = classFromJson(requestJsonString, GetEmployerByTokenDtoRequest.class);
        if (request.validate()) {
            Employer employer = employerDao.getEmployerByToken(request.getToken());
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            for (Vacancy vacancy : employer.getVacanciesNonActive()) {
                vacancyDtoSet.add(vacancyToVacancyDto(vacancy));
            }
            return gson.toJson(new GetVacancyDtoResponce(vacancyDtoSet));
        } else {
            return "JSON ERROR".toString();
        }
    }

    public String getVacancyAll(String requestJsonString) throws ServerException {
        GetEmployerByTokenDtoRequest request = classFromJson(requestJsonString, GetEmployerByTokenDtoRequest.class);
        if (request.validate()) {
            Employer employer = employerDao.getEmployerByToken(request.getToken());
            Set<VacancyDto> vacancyDtoSet = new HashSet<>();
            for (Vacancy vacancy : employer.getVacancies()) {
                vacancyDtoSet.add(vacancyToVacancyDto(vacancy));
            }
            return gson.toJson(new GetVacancyDtoResponce(vacancyDtoSet));
        } else {
            return "JSON ERROR".toString();
        }
    }

    public String acceptEmployeeToJob(String requestJsonString) throws ServerException {
        GetUserByLoginDtoRequest request = classFromJson(requestJsonString, GetUserByLoginDtoRequest.class);
        if (request.validate()){
            GetUserStatusDtoResponse response = new GetUserStatusDtoResponse(!employerDao.acceptEmployeeToJob(request.getLogin()));
            return gson.toJson(response);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    private <T> T classFromJson(String requestJsonString, Class<T> inClass) throws ServerException {
        if (requestJsonString == null) throw new ServerException(NULL_REQUEST_STRING);
        try {
            return gson.fromJson(requestJsonString, inClass);
        } catch (JsonSyntaxException ex) {
            throw new ServerException(JSON_SYNTAX_EXCEPTION);
        }
    }

    private String returnErrorDtoResponse(ServerErrorCode error) {
        ErrorDtoResponse errorResponce = new ErrorDtoResponse(error);
        return gson.toJson(errorResponce);
    }

    private VacancyDto vacancyToVacancyDto(Vacancy vacancy) {
        Set<RequirementDto> requirementsDto = new HashSet<>();
        for (Requirement requirement : vacancy.getRequirement()) {
            requirementsDto.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
        }
        return new VacancyDto(vacancy.getVacancyName(), vacancy.getSalary(), requirementsDto, vacancy.getEmployer());
    }

    private Vacancy vacancyDtoToVacancy(VacancyDto vacancyDto) {
        Set<Requirement> requirements = new HashSet<>();
        for (RequirementDto requirementDto : vacancyDto.getRequirement()) {
            requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
        }
        return new Vacancy(vacancyDto.getVacancyName(), vacancyDto.getSalary(), requirements, vacancyDto.getEmployer());
    }

    public Vacancy vacancyDtoToVacancy(String vacancyName, Integer salary, Set<RequirementDto> requirement) {
        VacancyDto vacancyDto = new VacancyDto(vacancyName, salary);
        vacancyDto.addRequirement(requirement);
        Set<Requirement> requirements = new HashSet<>();
        for (RequirementDto requirementDto : vacancyDto.getRequirement()) {
            requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
        }
        return new Vacancy(vacancyDto.getVacancyName(), vacancyDto.getSalary(), requirements, vacancyDto.getEmployer());
    }

    private Set<Employee> employeesDtoToEmployeesSet(Set<EmployeeDto> employeesDto) {
        Set<Employee> employees = new HashSet<>();
        for (EmployeeDto emp : employeesDto) {
            employees.add(new Employee(emp.getSurname(), emp.getName(), emp.getMiddleName(), emp.getEMail(), emp.getLogin(), emp.getPassword(), emp.getToken()));
        }
        return employees;
    }

    private Set<EmployeeDto> employeesToEmployeesDtoSet(Set<Employee> employees) {
        Set<EmployeeDto> employeesDto = new HashSet<>();
        for (Employee emp : employees) {
            employeesDto.add(new EmployeeDto(emp.getSurname(), emp.getName(), emp.getMiddleName(), emp.getEMail(), emp.getLogin(), emp.getPassword(), emp.getToken()));
        }
        return employeesDto;
    }

    private Set<Requirement> requirementDtoToRequirementSet(Set<RequirementDto> requirementsDto) {
        Set<Requirement> requirements = new HashSet<>();
        for (RequirementDto requirementDto : requirementsDto) {
            requirements.add(new Requirement(requirementDto.getName(), requirementDto.getLevel(), requirementDto.getRequired()));
        }
        return requirements;
    }

    private Set<RequirementDto> requirementsToRequirementsDtoSet(Set<Requirement> requirements) {
        Set<RequirementDto> requirementsDto = new HashSet<>();
        for (Requirement requirement : requirements) {
            requirementsDto.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
        }
        return requirementsDto;
    }

}
