//package net.thumbtack.school.hiring;
//
//import com.google.gson.Gson;
//import net.thumbtack.school.hiring.dto.EmployeeDto;
//import net.thumbtack.school.hiring.dto.RequirementDto;
//import net.thumbtack.school.hiring.dto.SkillDto;
//import net.thumbtack.school.hiring.dto.VacancyDto;
//import net.thumbtack.school.hiring.dto.request.GetUserByTokenDtoRequest;
//import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
//import net.thumbtack.school.hiring.dto.request.employee.SkillsEmployeeDtoRequest;
//import net.thumbtack.school.hiring.dto.request.employer.RegisterEmployerDtoRequest;
//import net.thumbtack.school.hiring.dto.request.vacancy.GetVacancyDtoRequest;
//import net.thumbtack.school.hiring.dto.request.vacancy.VacancyDtoRequest;
//import net.thumbtack.school.hiring.dto.response.ActivityProfileResponse;
//import net.thumbtack.school.hiring.dto.response.SkillsDtoResponse;
//import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
//import net.thumbtack.school.hiring.dto.response.employer.RegisterEmployerDtoResponse;
//import net.thumbtack.school.hiring.dto.response.vacancy.GetListVacancyDroResponce;
//import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyDtoResponce;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestEmployee {
////    @Test
////    public void employeeAdd() throws ServerException {
////        Server server = new Server();
////        server.startServer(null);
////        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112");
////
////        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
////        assertEquals(resultResponce.getToken(), Database.getDatabase().getEmployeeByLogin("Swer1").getToken());
////    }
//
//    @Test
//    public void addSkills() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer2", "zawderq112");
//
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 3));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//        assertEquals(skills, addSkillsResp.getSkills());
//    }
//
//    @Test
//    public void delSkills() throws ServerException {
//
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer3", "zawderq112");
//
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 3));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        Set<SkillDto> delSkills = new HashSet<>();
//        delSkills.add(new SkillDto("Java", 5));
//        delSkills.add(new SkillDto("Python", 3));
//
//        skills.removeAll(delSkills);
//
//        SkillsEmployeeDtoRequest delSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), delSkills);
//        SkillsDtoResponse delSkillsResp = new Gson().fromJson(server.deleteSkillsEmployee(new Gson().toJson(delSkillsReq)), SkillsDtoResponse.class);
//
//        assertEquals(skills, delSkillsResp.getSkills());
//    }
//
//    @Test
//    public void changeLevelSkills() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer5", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 4));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        skills.clear();
//        skills.add(new SkillDto("Java", 1));
//        skills.add(new SkillDto("C++", 1));
//        skills.add(new SkillDto("Python", 1));
//
//
//        SkillsEmployeeDtoRequest request = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        Set<EmployeeDto> employees = new HashSet<>();
//        SkillsDtoResponse response = new Gson().fromJson(server.changeLevelSkillsEmployee(new Gson().toJson(request)), SkillsDtoResponse.class); //Database.getDatabase().getEmployeesSkillsAtRequirementLevel(allSkills);
//
//        assertEquals(skills, response.getSkills());
//    }
//
//    @Test
//    public void setEmployeeActive() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer6", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        GetUserByTokenDtoRequest request = new GetUserByTokenDtoRequest(employee1.getToken());
//        ActivityProfileResponse response = new Gson().fromJson(server.setProfileActiveEmployee(new Gson().toJson(request)), ActivityProfileResponse.class);
//
//        assertTrue(response.getStatus());
//    }
//
//    @Test
//    public void setEmployeeNonActive() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer7", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        GetUserByTokenDtoRequest request = new GetUserByTokenDtoRequest(employee1.getToken());
//        ActivityProfileResponse response = new Gson().fromJson(server.setProfileNonActiveEmployee(new Gson().toJson(request)), ActivityProfileResponse.class);
//
//        assertFalse(response.getStatus());
//    }
//
////    @Test
////    public void changeDataEmployee() throws ServerException {
////        Server server = new Server();
////        server.startServer(null);
////        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer8", "zawderq112");
////        RegisterEmployeeDtoResponse resultRegister = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
////
////        Set<SkillDto> skills = new HashSet<>();
////        skills.add(new SkillDto("C#", 5));
////        skills.add(new SkillDto("Java", 1));
////        DataEmployeeRequest request = new DataEmployeeRequest(resultRegister.getToken(), "Ivanov", "Petr", "Andreevich", "7722@mail.ru", "Swer8", "QWERTY69", skills);
////
////        DataEmployeeResponse response = new Gson().fromJson(server.changeDataEmployee(new Gson().toJson(request)), DataEmployeeResponse.class);
////
////        Employee employee = Database.getDatabase().getEmployeeByToken(resultRegister.getToken());
////        assertAll(
////                () -> assertEquals(employee.getToken(), response.getToken()),
////                () -> assertEquals(employee.getSurname(), response.getSurname()),
////                () -> assertEquals(employee.getName(), response.getName()),
////                () -> assertEquals(employee.getMiddleName(), response.getMiddleName()),
////                () -> assertEquals(employee.getEMail(), response.getEMail()),
////                () -> assertEquals(employee.getLogin(), response.getLogin()),
////                () -> assertEquals(employee.getPassword(), response.getPassword()),
////                () -> assertEquals(skills, response.getSkills())
////        );
////    }
//
//
//    @Test
//    void getVacanciesSkillsAtRequirementLevel() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "satak32", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 4));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        Set<RequirementDto> requipmentSkills = new HashSet<>();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 3, false));
//
//        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Vitar", "Omskaya 136", "Vitar@mail.ru", "Kortashev", "Ivan", "Sergeevich", "employer33", "ASdws89");
//        RegisterEmployerDtoResponse registerEmployer = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);
//
//        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);
//
//        requipmentSkills.clear();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 5, false));
//
//        VacancyDtoRequest addVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq1)), GetVacancyDtoResponce.class);
//
//        GetVacancyDtoRequest request = new GetVacancyDtoRequest(employee1.getToken());
//        GetVacancyDtoResponce responce = new Gson().fromJson(server.getVacanciesSkillsAtRequirementLevel(new Gson().toJson(request)), GetVacancyDtoResponce.class);
//
//        Set<String> vacancyName = new HashSet<>();
//        for (VacancyDto vacancy : responce.getVacancyes()) {
//            vacancyName.add(vacancy.getVacancyName());
//        }
//
//        assertAll(
//                () -> assertTrue(vacancyName.contains("Разработчик Java")),
//                () -> assertFalse(vacancyName.contains("Разработчик Python"))
//        );
//
//
//    }
//
//    @Test
//    void getVacanciesRequiredSkillsNotLowerRequiredLevel() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "satak2", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 4));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        Set<RequirementDto> requipmentSkills = new HashSet<>();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 3, false));
//
//        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Vitar", "Omskaya 136", "Vitar@mail.ru", "Kortashev", "Ivan", "Sergeevich", "employer2", "ASdws89");
//        RegisterEmployerDtoResponse registerEmployer = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);
//
//        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);
//
//        requipmentSkills.clear();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 5, false));
//
//        VacancyDtoRequest addVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq1)), GetVacancyDtoResponce.class);
//
//        GetVacancyDtoRequest request = new GetVacancyDtoRequest(employee1.getToken());
//        GetVacancyDtoResponce responce = new Gson().fromJson(server.getVacanciesRequiredSkillsNotLowerRequiredLevel(new Gson().toJson(request)), GetVacancyDtoResponce.class);
//
//        Set<String> vacancyName = new HashSet<>();
//        for (VacancyDto vacancy : responce.getVacancyes()) {
//            vacancyName.add(vacancy.getVacancyName());
//        }
//
//        assertAll(
//                () -> assertTrue(vacancyName.contains("Разработчик Java")),
//                () -> assertTrue(vacancyName.contains("Разработчик Python"))
//        );
//    }
//
//    @Test
//    void getVacanciesAnyoneSkillsAnyLevel() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "satak1", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 4));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        Set<RequirementDto> requipmentSkills = new HashSet<>();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 3, false));
//
//        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Vitar", "Omskaya 136", "Vitar@mail.ru", "Kortashev", "Ivan", "Sergeevich", "employer1", "ASdws89");
//        RegisterEmployerDtoResponse registerEmployer = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);
//
//        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);
//
//        requipmentSkills.clear();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 5, false));
//
//        VacancyDtoRequest addVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq1)), GetVacancyDtoResponce.class);
//
//        GetVacancyDtoRequest request = new GetVacancyDtoRequest(employee1.getToken());
//        GetVacancyDtoResponce responce = new Gson().fromJson(server.getVacanciesAnyoneSkillsAnyLevel(new Gson().toJson(request)), GetVacancyDtoResponce.class);
//
//        Set<String> vacancyName = new HashSet<>();
//        for (VacancyDto vacancy : responce.getVacancyes()) {
//            vacancyName.add(vacancy.getVacancyName());
//        }
//
//        assertAll(
//                () -> assertTrue(vacancyName.contains("Разработчик Java")),
//                () -> assertTrue(vacancyName.contains("Разработчик Python"))
//        );
//    }
//
//    @Test
//    void getVacanciesSortedByNumberOfSkills() throws ServerException {
//        Server server = new Server();
//        server.startServer(null);
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "satak4", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        Set<SkillDto> skills = new HashSet<>();
//        skills.add(new SkillDto("Java", 5));
//        skills.add(new SkillDto("C++", 3));
//        skills.add(new SkillDto("Python", 4));
//
//        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
//        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);
//
//        Set<RequirementDto> requipmentSkills = new HashSet<>();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 3, false));
//
//        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("Vitar", "Omskaya 136", "Vitar@mail.ru", "Kortashev", "Ivan", "Sergeevich", "employer4", "ASdws89");
//        RegisterEmployerDtoResponse registerEmployer = new Gson().fromJson(server.registerEmployer(new Gson().toJson(employer1)), RegisterEmployerDtoResponse.class);
//
//        VacancyDtoRequest addVacancyReq = new VacancyDtoRequest(employer1.getToken(), "Разработчик Java", 2500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq)), GetVacancyDtoResponce.class);
//
//        requipmentSkills.clear();
//        requipmentSkills.add(new RequirementDto("Java", 3, true));
//        requipmentSkills.add(new RequirementDto("Python", 5, false));
//
//        VacancyDtoRequest addVacancyReq1 = new VacancyDtoRequest(employer1.getToken(), "Разработчик Python", 1500, requipmentSkills);
//        GetVacancyDtoResponce vacancyResp1 = new Gson().fromJson(server.addVacancy(new Gson().toJson(addVacancyReq1)), GetVacancyDtoResponce.class);
//
//        GetVacancyDtoRequest request = new GetVacancyDtoRequest(employee1.getToken());
//        String jsonResp = server.getVacanciesSortedByNumberOfSkills(new Gson().toJson(request));
//        GetListVacancyDroResponce responce = new Gson().fromJson(jsonResp, GetListVacancyDroResponce.class);
//
//        Set<String> vacancyName = new HashSet<>();
//
//        for (VacancyDto vacancyDto : responce.getVacancyes()) {
//            vacancyName.add(vacancyDto.getVacancyName());
//        }
//
//
//        assertAll(
//                () -> assertTrue(vacancyName.contains("Разработчик Java")),
//                () -> assertTrue(vacancyName.contains("Разработчик Python"))
//        );
//    }
//}
