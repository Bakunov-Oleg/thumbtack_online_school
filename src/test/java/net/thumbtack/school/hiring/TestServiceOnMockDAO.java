package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hiring.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.dto.request.employee.DataEmployeeRequest;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.SkillsEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employer.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.VacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.SkillsDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.DataEmployeeResponse;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.employer.RegisterEmployerDtoResponse;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.service.EmployeeService;
import net.thumbtack.school.robot.Robot;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class TestServiceOnMockDAO {


//    В базе данных MySQL создать список некоторых городов - столиц стран. Написать метод, которые загружает этот список,
//    для каждого города выводит страну и национальную валюту используя сервис
//    http://restcountries.eu (например http://restcountries.eu/rest/v2/capital/london ). Написать тесты для этого метода.


    @Test
    public void testSubstitutionValue() throws ServerException {

        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class,
                invocation -> {
                    if (invocation.getMethod().getReturnType().equals(String.class)) {
                        return "thisIsMockToStr";
                    } else {
                        return Mockito.RETURNS_DEFAULTS.answer(invocation);
                    }
                }
        );

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112");

        String resultResponce = employeeService.registerEmployee(new Gson().toJson(employee1));

        Assert.assertTrue(resultResponce.contains("thisIsMockToStr"));
    }

    @Test
    public void testSubstitutionValue2() throws ServerException {

        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);
        Employee employee = new Employee("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112", "thisIsToken");

        when(employeeDaoImp.getEmployeeByToken("testToken")).thenReturn(employee);
        when(employeeDaoImp.changeDataEmployee(any(Employee.class), any(Employee.class))).thenReturn(employee);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);

        Set<SkillDto> skillsDto = new HashSet<>();
        skillsDto.add(new SkillDto("skill", 1));
        DataEmployeeRequest request = new DataEmployeeRequest("testToken", "Ivanov", "Petr", "Andreevich", "7722@mail.ru", "Swer8", "QWERTY69", skillsDto);
        DataEmployeeResponse response = new Gson().fromJson(employeeService.changeEmployeeData(new Gson().toJson(request)), DataEmployeeResponse.class);

        assertAll(
                () -> assertEquals(employee.getToken(), response.getToken()),
                () -> assertEquals(employee.getSurname(), response.getSurname()),
                () -> assertEquals(employee.getName(), response.getName()),
                () -> assertEquals(employee.getMiddleName(), response.getMiddleName()),
                () -> assertEquals(employee.getEMail(), response.getEMail()),
                () -> assertEquals(employee.getLogin(), response.getLogin()),
                () -> assertEquals(employee.getPassword(), response.getPassword()),
                () -> assertEquals(employee.getSkills(), response.getSkills())
        );
    }


    @Test
    public void testMatcher() throws ServerException {

        class FullNameMatcher implements ArgumentMatcher<String> {
            @Override
            public boolean matches(String argument) {
                return argument.contains("testArgument");
            }
        }

        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);
        when(employeeDaoImp.getEmployeeByToken(argThat(new FullNameMatcher()))).thenThrow(new ServerException(ServerErrorCode.INCORRECT_LOGIN));

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);

        Set<SkillDto> skillsDto = new HashSet<>();
        skillsDto.add(new SkillDto("skill", 1));
        DataEmployeeRequest request = new DataEmployeeRequest("testArgument", "Ivanov", "Petr", "Andreevich", "7722@mail.ru", "Swer8", "QWERTY69", skillsDto);

        try {
            new Gson().fromJson(employeeService.changeEmployeeData(new Gson().toJson(request)), DataEmployeeResponse.class);
            fail();
        } catch (ServerException e) {
            System.out.println(e.getErrorCode().getErrorCode());
            e.printStackTrace();
        }
    }


    @Test
    public void testException() throws ServerException {
        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);
        when(employeeDaoImp.getEmployeeByToken(any()))
                .thenThrow(new ServerException(ServerErrorCode.INCORRECT_TOKEN));
        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);


        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer2", "zawderq112");
        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(employeeService.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
        Set<SkillDto> skills = new HashSet<>();
        skills.add(new SkillDto("Java", 5));
        skills.add(new SkillDto("C++", 3));
        skills.add(new SkillDto("Python", 3));
        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);


        try {
            new Gson().fromJson(employeeService.addEmployeeSkills(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
            fail();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            System.out.println(e.getErrorCode().getErrorCode());
            e.printStackTrace();
        }
    }

    @Test
    public void testException2() throws ServerException {
        Employee employee = new Employee("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112", "thisIsTokenTest");

        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);

        when(employeeDaoImp.getEmployeeByToken(any()))
                .thenThrow(new ServerException(ServerErrorCode.INCORRECT_TOKEN));
        doReturn(employee).when(employeeDaoImp).getEmployeeByToken("testToken");

        when(employeeDaoImp.changeDataEmployee(any(Employee.class), any(Employee.class))).thenReturn(employee);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);


        Set<SkillDto> skillsDto = new HashSet<>();
        skillsDto.add(new SkillDto("skill", 1));

        DataEmployeeRequest request = new DataEmployeeRequest("testToken", "Ivanov", "Petr", "Andreevich", "7722@mail.ru", "Swer8", "QWERTY69", skillsDto);
        DataEmployeeResponse response = new Gson().fromJson(employeeService.changeEmployeeData(new Gson().toJson(request)), DataEmployeeResponse.class);
        DataEmployeeRequest request1 = new DataEmployeeRequest("testToken1", "Ivanov", "Petr", "Andreevich", "7722@mail.ru", "Swer8", "QWERTY69", skillsDto);

        assertEquals("thisIsTokenTest", response.getToken());
        try {
            new Gson().fromJson(employeeService.changeEmployeeData(new Gson().toJson(request1)), DataEmployeeResponse.class);
            fail();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            System.out.println(e.getErrorCode().getErrorCode());
            e.printStackTrace();
        }

    }


    @Test
    public void testVerify() throws ServerException {
        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);

        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(employeeDaoImp);

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112");

        employeeService.registerEmployee(new Gson().toJson(employee1));

        verify(employeeDaoImp).insert(any(), eq(employee1.getToken()));
    }

    @Test
    public void testArgumentCaptor() throws ServerException {

        EmployeeDaoImpl employeeDaoImp = mock(EmployeeDaoImpl.class);
        EmployeeService employeeService = new EmployeeService();

        employeeService.setEmployeeDao(employeeDaoImp);

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112");

        employeeService.registerEmployee(new Gson().toJson(employee1));

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Employee> userCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeDaoImp).insert(userCaptor.capture(), stringCaptor.capture());

        assertAll(
                () -> assertEquals(employee1.getToken(), userCaptor.getValue().getToken()),
                () -> assertEquals(employee1.getSurname(), userCaptor.getValue().getSurname()),
                () -> assertEquals(employee1.getName(), userCaptor.getValue().getName()),
                () -> assertEquals(employee1.getMiddleName(), userCaptor.getValue().getMiddleName()),
                () -> assertEquals(employee1.getEMail(), userCaptor.getValue().getEMail()),
                () -> assertEquals(employee1.getLogin(), userCaptor.getValue().getLogin()),
                () -> assertEquals(employee1.getPassword(), userCaptor.getValue().getPassword())
        );
    }

    @Test
    public void testRobotForSrever() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        Robot robot = new Robot(server);
        int countEntries = 0;

        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer1w2", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 1, true));
        requipmentSkills.add(new RequirementDto("Python", 1, false));

        server.addVacancy(new Gson().toJson(new VacancyDtoRequest(employer1.getToken(), "Разработчик Java1", 2500, requipmentSkills)));
        server.addVacancy(new Gson().toJson(new VacancyDtoRequest(employer1.getToken(), "Разработчик Java2", 2500, requipmentSkills)));
        server.addVacancy(new Gson().toJson(new VacancyDtoRequest(employer1.getToken(), "Разработчик Java3", 2500, requipmentSkills)));
        server.addVacancy(new Gson().toJson(new VacancyDtoRequest(employer1.getToken(), "Разработчик Java4", 2500, requipmentSkills)));
        server.addVacancy(new Gson().toJson(new VacancyDtoRequest(employer1.getToken(), "Разработчик Java5", 2500, requipmentSkills)));


        try {
            countEntries = robot.startRobot();
        } catch (ServerException e) {
            e.printStackTrace();
        }

        assertEquals(countEntries, 5);
    }


}
