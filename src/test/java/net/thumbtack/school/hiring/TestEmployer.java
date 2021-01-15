package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.RequirementDto;
import net.thumbtack.school.hiring.dto.VacancyDto;
import net.thumbtack.school.hiring.dto.request.GetUserByLoginDtoRequest;
import net.thumbtack.school.hiring.dto.request.GetUserByTokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employer.DataEmployerRequest;
import net.thumbtack.school.hiring.dto.request.employer.GetEmployerByTokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.employer.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.ChangeActivityVacancyDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.VacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.GetUserStatusDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.employer.RegisterEmployerDtoResponse;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyDtoResponce;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyStatusDto;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployer {
    @Test
    public void employerAdd() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "harik858", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);
        GetUserByTokenDtoRequest userByTokenReq = new GetUserByTokenDtoRequest(resultRegister.getToken());

        //assertEquals(resultRegister.getToken(), Database.getDatabase().getEmployerByLogin("harik858").getToken());
    }

    @Test
    public void changeDataEmployer() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "harik8587", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        DataEmployerRequest request = new DataEmployerRequest(resultRegister.getToken(), "Banchen", "Sergey", "tarkonovich", "Karton654@mail.ru", "harik8587", "kegh8554", "Exists", "omskya 88");
        DataEmployerRequest response = new Gson().fromJson(server.changeDataEmployer(new Gson().toJson(request)), DataEmployerRequest.class);

        assertAll(
                () -> assertEquals(response.getAdress(), "omskya 88"),
                () -> assertEquals(response.getCompanyName(), "Exists"),
                () -> assertEquals(response.getSurname(), "Banchen"),
                () -> assertEquals(response.getName(), "Sergey"),
                () -> assertEquals(response.getMiddleName(), "tarkonovich"),
                () -> assertEquals(response.getEMail(), "Karton654@mail.ru"),
                () -> assertEquals(response.getLogin(), "harik8587"),
                () -> assertEquals(response.getPassword(), "kegh8554")
        );
    }

    @Test
    public void addVacancy() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drovq", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer12", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        VacancyDto vavc = null;
        for (VacancyDto vacancyDto : vacancyResp.getVacancyes()){
            vavc = vacancyDto;
        }
        VacancyDto finalVavc = vavc;
        assertAll(
                () -> assertTrue(finalVavc.getVacancyName().equals("Разработчик Java")),
                () -> assertTrue(finalVavc.getSalary().equals(2500)),
                () -> assertTrue(finalVavc.getRequirement().equals(requipmentSkills))
        );

    }

    @Test
    public void delVacancy() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer22", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        requipmentSkills.clear();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 5, false));

        VacancyDtoRequest addVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq1)), GetVacancyDtoResponce.class);

        VacancyDtoRequest delVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
        GetVacancyDtoResponce delVacancyResp1 = new Gson().fromJson(server.delVacancy(new Gson().toJson(delVacancyReq1)), GetVacancyDtoResponce.class);

        requipmentSkills.clear();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDto vavc = null;
        for (VacancyDto vacancyDto : vacancyResp.getVacancyes()){
            vavc = vacancyDto;
        }
        VacancyDto finalVavc = vavc;
        assertAll(
                () -> assertTrue(finalVavc.getVacancyName().equals("Разработчик Java")),
                () -> assertTrue(finalVavc.getSalary().equals(2500)),
                () -> assertTrue(finalVavc.getRequirement().equals(requipmentSkills))
        );
    }

    @Test
    public void addVacancySkill() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer3", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);


        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        Set<RequirementDto> addRequipmentSkills = new HashSet<>();
        addRequipmentSkills.add(new RequirementDto("C++", 3, true));
        addRequipmentSkills.add(new RequirementDto("VBA", 5, false));

        VacancyDtoRequest addRequipmentReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, addRequipmentSkills);
        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancySkill(new Gson().toJson(addRequipmentReq1)), GetVacancyDtoResponce.class);

        VacancyDto vavc = null;
        for (VacancyDto vacancyDto : vacancyResp1.getVacancyes()){
            vavc = vacancyDto;
        }
        VacancyDto finalVavc = vavc;
        assertAll(
                () -> assertTrue(finalVavc.getVacancyName().equals("Разработчик Java")),
                () -> assertTrue(finalVavc.getSalary().equals(2500)),
                () -> assertTrue(finalVavc.getRequirement().equals(addRequipmentSkills))
        );

    }

    @Test
    public void removeVacancySkill() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer40", "keghHdD854");

        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);


        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        Set<RequirementDto> dellRequipmentSkills = new HashSet<>();
        dellRequipmentSkills.add(new RequirementDto("Java", 3, true));

        requipmentSkills.removeAll(dellRequipmentSkills);

        VacancyDtoRequest addRequipmentReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, dellRequipmentSkills);
        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.removeVacancySkill(new Gson().toJson(addRequipmentReq1)), GetVacancyDtoResponce.class);

        VacancyDto vavc = null;
        for (VacancyDto vacancyDto : vacancyResp1.getVacancyes()){
            vavc = vacancyDto;
        }
        VacancyDto finalVavc = vavc;
        assertAll(
                () -> assertTrue(finalVavc.getVacancyName().equals("Разработчик Java")),
                () -> assertTrue(finalVavc.getSalary().equals(2500)),
                () -> assertTrue(finalVavc.getRequirement().equals(dellRequipmentSkills))
        );
    }

    @Test
    public void changeVacancySkill() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer41", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        Set<RequirementDto> changeRequipmentSkills = new HashSet<>();
        changeRequipmentSkills.add(new RequirementDto("Java", 5, true));

        VacancyDtoRequest addRequipmentReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, changeRequipmentSkills);
        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.changeVacancySkill(new Gson().toJson(addRequipmentReq1)), GetVacancyDtoResponce.class);

        VacancyDto vavc = null;
        for (VacancyDto vacancyDto : vacancyResp1.getVacancyes()){
            vavc = vacancyDto;
        }
        VacancyDto finalVavc = vavc;
        assertAll(
                () -> assertTrue(finalVavc.getVacancyName().equals("Разработчик Java")),
                () -> assertTrue(finalVavc.getSalary().equals(2500)),
                () -> assertTrue(finalVavc.getRequirement().equals(changeRequipmentSkills))
        );
    }

    @Test
    public void setVacancyActive() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer42", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        VacancyDto vacancy = null;
        for (VacancyDto vacancyDto : vacancyResp.getVacancyes()){
            vacancy = vacancyDto;
        }

        ChangeActivityVacancyDtoRequest request = new ChangeActivityVacancyDtoRequest(resultRegister.getToken(), vacancy);
        GetVacancyStatusDto response = new Gson().fromJson(server.setVacancyActive(new Gson().toJson(request)), GetVacancyStatusDto.class);

        assertTrue(response.getStatusVacancy());
    }

    @Test
    public void setVacancyNonActive() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer43", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        VacancyDto vacancy = null;
        for (VacancyDto vacancyDto : vacancyResp.getVacancyes()){
            vacancy = vacancyDto;
        }

        ChangeActivityVacancyDtoRequest request = new ChangeActivityVacancyDtoRequest(resultRegister.getToken(), vacancy);
        GetVacancyStatusDto response = new Gson().fromJson(server.setVacancyNonActive(new Gson().toJson(request)), GetVacancyStatusDto.class);

        assertFalse(response.getStatusVacancy());
    }

    @Test
    public void getVacancyActive() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer44", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        GetEmployerByTokenDtoRequest request = new GetEmployerByTokenDtoRequest(resultRegister.getToken());
        GetVacancyDtoResponce response = new Gson().fromJson(server.getVacancyActive(new Gson().toJson(request)), GetVacancyDtoResponce.class);

        VacancyDto retVacancy = null;
        for (VacancyDto vacancyDto : response.getVacancyes()){
            retVacancy = vacancyDto;
        }

        assertTrue(retVacancy.getVacancyName().equals("Разработчик Java"));
    }

    @Test
    public void getVacancyNonActive() throws ServerException {
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer45", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        VacancyDto vacancy = null;
        for (VacancyDto vacancyDto : vacancyResp.getVacancyes()){
            vacancy = vacancyDto;
        }

        ChangeActivityVacancyDtoRequest nonActiveRequest = new ChangeActivityVacancyDtoRequest(resultRegister.getToken(), vacancy);
        GetVacancyStatusDto nonActiveResponse = new Gson().fromJson(server.setVacancyNonActive(new Gson().toJson(nonActiveRequest)), GetVacancyStatusDto.class);

        GetEmployerByTokenDtoRequest request = new GetEmployerByTokenDtoRequest(resultRegister.getToken());
        GetVacancyDtoResponce response = new Gson().fromJson(server.getVacancyActive(new Gson().toJson(request)), GetVacancyDtoResponce.class);

        VacancyDto retVacancy = null;
        for (VacancyDto vacancyDto : response.getVacancyes()){
            retVacancy = vacancyDto;
        }

        assertTrue(retVacancy.getVacancyName().equals("Разработчик Java"));
    }

    @Test
    public void getVacancyAll() throws ServerException{
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer47", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        Set<RequirementDto> requipmentSkills = new HashSet<>();
        requipmentSkills.add(new RequirementDto("Java", 3, true));
        requipmentSkills.add(new RequirementDto("Python", 3, false));

        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);

        GetEmployerByTokenDtoRequest request = new GetEmployerByTokenDtoRequest(resultRegister.getToken());
        GetVacancyDtoResponce response = new Gson().fromJson(server.getVacancyAll(new Gson().toJson(request)), GetVacancyDtoResponce.class);

        VacancyDto retVacancy = null;
        for (VacancyDto vacancyDto : response.getVacancyes()){
            retVacancy = vacancyDto;
        }

        assertTrue(retVacancy.getVacancyName().equals("Разработчик Java"));
    }

    @Test
    public void acceptEmployeeToJob() throws ServerException{
        Server server = new Server();
        server.startServer(null);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Drov", "omskaya 136", "Ivanovich@mail.ru", "Vertelov", "Alexey", "Antonovich", "employer49", "keghHdD854");
        RegisterEmployerDtoResponse resultRegister = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "satak321", "zawderq112");
        RegisterEmployeeDtoResponse regEmployeeResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);

        GetUserByLoginDtoRequest request = new GetUserByLoginDtoRequest("satak321");
        GetUserStatusDtoResponse response = new Gson().fromJson(server.acceptEmployeeToJob(new Gson().toJson(request)), GetUserStatusDtoResponse.class);

        assertFalse(response.getStatus());
    }
}
