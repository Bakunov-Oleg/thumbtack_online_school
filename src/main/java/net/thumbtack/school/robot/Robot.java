package net.thumbtack.school.robot;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.Server;
import net.thumbtack.school.hiring.ServerErrorCode;
import net.thumbtack.school.hiring.ServerException;
import net.thumbtack.school.hiring.dto.SkillDto;
import net.thumbtack.school.hiring.dto.request.GetUserByTokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.employee.SkillsEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.request.vacancy.GetVacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.GetUserStatusDtoResponse;
import net.thumbtack.school.hiring.dto.response.SkillsDtoResponse;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.vacancy.GetVacancyDtoResponce;

import java.util.HashSet;
import java.util.Set;

public class Robot {

    private Server server;

    public Robot(Server server) {
        this.server = server;
    }

    private String registerUser() throws ServerException {
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer2", "zawderq112");
        RegisterEmployeeDtoResponse resultResponse = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);

        Set<SkillDto> skills = new HashSet<>();
        skills.add(new SkillDto("Java", 5));
        skills.add(new SkillDto("C++", 3));
        skills.add(new SkillDto("Python", 3));
        SkillsEmployeeDtoRequest addSkillsReq = new SkillsEmployeeDtoRequest(employee1.getToken(), skills);
        SkillsDtoResponse addSkillsResp = new Gson().fromJson(server.addSkillsEmployee(new Gson().toJson(addSkillsReq)), SkillsDtoResponse.class);

        return resultResponse.getToken();
    }

    private int getListSize(String userToken) throws ServerException {
        GetVacancyDtoRequest request = new GetVacancyDtoRequest(userToken);
        GetVacancyDtoResponce response = new Gson().fromJson(server.getVacanciesAnyoneSkillsAnyLevel(new Gson().toJson(request)), GetVacancyDtoResponce.class);

        GetUserByTokenDtoRequest requestNonActiveEmployee = new GetUserByTokenDtoRequest(userToken);
        GetUserStatusDtoResponse responseActivity = new Gson().fromJson(server.setProfileNonActiveEmployee(new Gson().toJson(requestNonActiveEmployee)), GetUserStatusDtoResponse.class);

        if (responseActivity.getStatus()) {
            throw new ServerException(ServerErrorCode.REQUEST_NOT_VALIDE);
        }

        return response.getVacancyes().size();
    }

    public int startRobot() throws ServerException {
        return getListSize(registerUser());
    }

}
