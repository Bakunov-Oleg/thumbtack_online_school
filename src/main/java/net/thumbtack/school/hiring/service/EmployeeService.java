package net.thumbtack.school.hiring.service;

import com.google.common.collect.TreeMultimap;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hiring.ServerErrorCode;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.dto.VacancyDto;
import net.thumbtack.school.hiring.dto.request.GetUserByTokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.DataEmployeeRequest;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.SkillsEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.GetVacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hiring.dto.response.GetUserStatusDtoResponse;
import net.thumbtack.school.hiring.dto.response.SkillsDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.DataEmployeeResponse;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.vacancy.GetListVacancyDroResponce;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyDtoResponce;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancySortedByNumberOfSkillsDtoResponce;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Requirement;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Vacancy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.thumbtack.school.hiring.ServerErrorCode.*;

public class EmployeeService {
    private /*final*/ EmployeeDao employeeDao = new EmployeeDaoImpl();
    private static final Gson gson = new Gson();

    public EmployeeService() {
    }

    public void setEmployeeDao(EmployeeDaoImpl daoImpl) {
        this.employeeDao = daoImpl;
    }

    public String registerEmployee(String requestJsonString) throws ServerException {
        RegisterEmployeeDtoRequest regEmployeeDtoReq = classFromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
        if (regEmployeeDtoReq.validate()) {
            Employee employee = new Employee(regEmployeeDtoReq.getSurname(), regEmployeeDtoReq.getName(), regEmployeeDtoReq.getMiddleName(), regEmployeeDtoReq.getEMail(), regEmployeeDtoReq.getLogin(), regEmployeeDtoReq.getPassword(), regEmployeeDtoReq.getToken());
            RegisterEmployeeDtoResponse regResponce = new RegisterEmployeeDtoResponse(employeeDao.insert(employee, regEmployeeDtoReq.getToken()));
            return gson.toJson(regResponce);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String addEmployeeSkills(String requestJsonString) throws ServerException {
        SkillsEmployeeDtoRequest addSkillsEmployee = classFromJson(requestJsonString, SkillsEmployeeDtoRequest.class);
        if (addSkillsEmployee.validate()) {
            String token = addSkillsEmployee.getToken();
            Employee employee = employeeDao.getEmployeeByToken(token);
            Set<Skill> skills = employeeDao.addSkills(employee, addSkillsEmployee.getSkills());
            Set<SkillDto> skillsDto = new HashSet<>();
            for (Skill skill : skills) {
                skillsDto.add(new SkillDto(skill.getName(), skill.getLevel()));
            }
            SkillsDtoResponse skillsResp = new SkillsDtoResponse(skillsDto);
            return gson.toJson(skillsResp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String deleteEmployeeSkills(String requestJsonString) throws ServerException {
        SkillsEmployeeDtoRequest delSkillsEmployee = classFromJson(requestJsonString, SkillsEmployeeDtoRequest.class);
        if (delSkillsEmployee.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(delSkillsEmployee.getToken());
            Set<Skill> skills = employeeDao.delSkills(employee, delSkillsEmployee.getSkills());
            Set<SkillDto> skillsDto = new HashSet<>();
            for (Skill skill : skills) {
                skillsDto.add(new SkillDto(skill.getName(), skill.getLevel()));
            }
            SkillsDtoResponse skillsResp = new SkillsDtoResponse(skillsDto);
            return gson.toJson(skillsResp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String changeEmployeeSkillsLevel(String requestJsonString) throws ServerException {
        SkillsEmployeeDtoRequest changeSkillsEmployee = classFromJson(requestJsonString, SkillsEmployeeDtoRequest.class);
        if (changeSkillsEmployee.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(changeSkillsEmployee.getToken());
            Set<Skill> skills = employeeDao.changeLevelSkills(employee, changeSkillsEmployee.getSkills());
            Set<SkillDto> skillsDto = new HashSet<>();
            for (Skill skill : skills) {
                skillsDto.add(new SkillDto(skill.getName(), skill.getLevel()));
            }
            SkillsDtoResponse skillsResp = new SkillsDtoResponse(skillsDto);
            return gson.toJson(skillsResp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String setActiveProfile(String requestJsonString) throws ServerException {
        GetUserByTokenDtoRequest request = classFromJson(requestJsonString, GetUserByTokenDtoRequest.class);
        if (request.validate()){
            GetUserStatusDtoResponse response = new GetUserStatusDtoResponse(employeeDao.setEmployeeActive(request.getToken()));
            return gson.toJson(response);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String setNonActiveProfile(String requestJsonString) throws ServerException {
        GetUserByTokenDtoRequest request = classFromJson(requestJsonString, GetUserByTokenDtoRequest.class);
        if (request.validate()){
            GetUserStatusDtoResponse response = new GetUserStatusDtoResponse(!employeeDao.setEmployeeNonActive(request.getToken()));
            return gson.toJson(response);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String changeEmployeeData(String stringJson) throws ServerException {
        DataEmployeeRequest dataReq = classFromJson(stringJson, DataEmployeeRequest.class);
        if (dataReq.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(dataReq.getToken());
            Employee newEmployee = new Employee(dataReq.getSurname(), dataReq.getName(), dataReq.getMiddleName(), dataReq.getEMail(), dataReq.getLogin(), dataReq.getPassword(), dataReq.getToken());
            Set<Skill> newEmployeeSkills = new HashSet<>();
            for (SkillDto skill : dataReq.getSkills()) {
                newEmployeeSkills.add(new Skill(skill.getName(), skill.getLevel()));
            }
            newEmployee.addSkills(newEmployeeSkills);
            Employee respEmpl = employeeDao.changeDataEmployee(employee, newEmployee);
            Set<SkillDto> skillsDto = new HashSet<>();
            for (Skill skill : respEmpl.getSkills()) {
                skillsDto.add(new SkillDto(skill.getName(), skill.getLevel()));
            }
            DataEmployeeResponse dataResp = new DataEmployeeResponse(respEmpl.getToken(), respEmpl.getSurname(), respEmpl.getName(), respEmpl.getMiddleName(), respEmpl.getEMail(), respEmpl.getLogin(), respEmpl.getPassword(), skillsDto);
            return gson.toJson(dataResp);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String getVacanciesSkillsAtRequirementLevel(String stringJson) throws ServerException { //навыки на уровне требуемых
        GetVacancyDtoRequest vacancyReq = classFromJson(stringJson, GetVacancyDtoRequest.class);
        if (vacancyReq.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(vacancyReq.getToken());
            Set<Vacancy> vacancyes = employeeDao.getVacanciesSkillsAtRequirementLevel(employee.getSkills());
            Set<VacancyDto> vacancyesDto = new HashSet<>();
            for (Vacancy vacancy : vacancyes) {
                Set<RequirementDto> requirementDtoSet = new HashSet<>();
                for (Requirement requirement : vacancy.getRequirement()) {
                    requirementDtoSet.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
                }
                vacancyesDto.add(new VacancyDto(vacancy.getVacancyName(), vacancy.getSalary(), requirementDtoSet, vacancy.getEmployer()));
            }

            return gson.toJson(new GetVacancyDtoResponce(vacancyesDto));
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String getVacanciesRequiredSkillsNotLowerRequiredLevel(String stringJson) throws ServerException {//навыке на уровне обязательных
        GetVacancyDtoRequest vacancyReq = classFromJson(stringJson, GetVacancyDtoRequest.class);
        if (vacancyReq.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(vacancyReq.getToken());
            Set<Vacancy> vacancyes = employeeDao.getVacanciesRequiredSkillsNotLowerRequiredLevel(employee.getSkills());
            Set<VacancyDto> vacancyesDto = new HashSet<>();
            for (Vacancy vacancy : vacancyes) {
                Set<RequirementDto> requirementDtoSet = new HashSet<>();
                for (Requirement requirement : vacancy.getRequirement()) {
                    requirementDtoSet.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
                }
                vacancyesDto.add(new VacancyDto(vacancy.getVacancyName(), vacancy.getSalary(), requirementDtoSet, vacancy.getEmployer()));
            }
            return gson.toJson(new GetVacancyDtoResponce(vacancyesDto));
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String getVacanciesAnyoneSkillsAnyLevel(String stringJson) throws ServerException {
        GetVacancyDtoRequest vacancyReq = classFromJson(stringJson, GetVacancyDtoRequest.class);
        if (vacancyReq.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(vacancyReq.getToken());
            Set<Vacancy> vacancyes = employeeDao.getVacanciesAnyoneSkillsAnyLevel(employee.getSkills());
            Set<VacancyDto> vacancyesDto = new HashSet<>();
            for (Vacancy vacancy : vacancyes) {
                Set<RequirementDto> requirementDtoSet = new HashSet<>();
                for (Requirement requirement : vacancy.getRequirement()) {
                    requirementDtoSet.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
                }
                vacancyesDto.add(new VacancyDto(vacancy.getVacancyName(), vacancy.getSalary(), requirementDtoSet, vacancy.getEmployer()));
            }
            return gson.toJson(new GetVacancyDtoResponce(vacancyesDto));
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String getVacanciesSortedByNumberOfSkills(String stringJson) throws ServerException {
        GetVacancyDtoRequest vacancyReq = classFromJson(stringJson, GetVacancyDtoRequest.class);
        if (vacancyReq.validate()) {
            Employee employee = employeeDao.getEmployeeByToken(vacancyReq.getToken());
            TreeMultimap<Integer, Vacancy> vacancyes = TreeMultimap.create(Integer::compareTo, Vacancy::compareTo);
            vacancyes = employeeDao.getVacanciesSortedByNumberOfSkills(employee.getSkills());
            TreeMultimap<Integer, VacancyDto> vacancyesDto = TreeMultimap.create(Integer::compareTo, VacancyDto::compareTo);
            for(Integer numSkill : vacancyes.keySet()){
                for (Vacancy vacancy : vacancyes.get(numSkill)){
                    Set<RequirementDto> requirementDtoSet = new HashSet<>();
                    for (Requirement requirement : vacancy.getRequirement()) {
                        requirementDtoSet.add(new RequirementDto(requirement.getName(), requirement.getLevel(), requirement.getRequired()));
                    }
                    vacancyesDto.put(numSkill, new VacancyDto(vacancy.getVacancyName(), vacancy.getSalary(), requirementDtoSet, vacancy.getEmployer()));
                }
            }
            GetVacancySortedByNumberOfSkillsDtoResponce responce = new GetVacancySortedByNumberOfSkillsDtoResponce(vacancyesDto);
            List<VacancyDto> sortVacancyes = new ArrayList<>();
            int maxCountSkills = 0;
            for (Integer key: responce.getVacancyes().keySet()){
                maxCountSkills = Math.max(maxCountSkills, key);
            }
            for(int i = maxCountSkills; i > 0; i--){
                for (VacancyDto vacancyDto : responce.getVacancyes().get(i)){
                    sortVacancyes.add(vacancyDto);
                }
            }

            GetListVacancyDroResponce listResponce = new GetListVacancyDroResponce(sortVacancyes);

            return gson.toJson(listResponce);
        }
        return returnErrorDtoResponse(REQUEST_NOT_VALIDE);
    }

    public String loginLogoutOnServer(String stringJson){
        return null;
    }

    public String exitOnServer(String stringJson){
        return null;
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
}
