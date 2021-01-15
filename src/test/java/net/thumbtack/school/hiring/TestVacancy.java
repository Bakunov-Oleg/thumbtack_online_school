package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.EmployeeDto;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.SkillsEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.RequirementsSetDto;
import net.thumbtack.school.hiring.dto.response.SkillsDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.getEmployesDtoResponse;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVacancy {
    @Test
    public void getEmployeeByAllSkills() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer4", "zawderq112");
        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);

        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Firton", "Alex", "Serg", "8238@mail.ru", "Swer1s", "zawderq112");
        RegisterEmployeeDtoResponse resultResponce1 = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee2)), RegisterEmployeeDtoResponse.class);

        Set<SkillDto> skills = new HashSet<>();
        skills.add(new SkillDto("Java", 5));
        skills.add(new SkillDto("C++", 3));
        skills.add(new SkillDto("Python", 4));

        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);

        skills.clear();
        skills.add(new SkillDto("Java", 3));
        skills.add(new SkillDto("C++", 3));
        skills.add(new SkillDto("Python", 4));
        addSkillsReq = new SkillsEmployeeDtoRequest(employee2.getToken(), skills);
        addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);

        Set<RequirementDto> allSkills = new HashSet<>();
        allSkills.add(new RequirementDto("Java", 3, true));
        allSkills.add(new RequirementDto("Python", 3, false));

        RequirementsSetDto request = new RequirementsSetDto(allSkills);
        Set<EmployeeDto> employees = new HashSet<>();
        getEmployesDtoResponse response = new Gson().fromJson(server.getEmployeesSkillsAtRequirementLevel(new Gson().toJson(request, RequirementsSetDto.class)), getEmployesDtoResponse.class); //Database.getDatabase().getEmployeesSkillsAtRequirementLevel(allSkills);
        employees = response.getEmployees();
        Set<String> employeesLogin = new HashSet<>();
        for (EmployeeDto employee : employees) {
            employeesLogin.add(employee.getLogin());
        }

        assertAll(
                () -> assertTrue(employeesLogin.contains("Swer4")),
                () -> assertTrue(employeesLogin.contains("Swer1s"))
        );
    }

}
