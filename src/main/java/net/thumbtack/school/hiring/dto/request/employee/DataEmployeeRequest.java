package net.thumbtack.school.hiring.dto.request.employee;

import net.thumbtack.school.hiring.dto.SkillDto;

import java.util.Set;

public class DataEmployeeRequest {
    private String surname;
    private String name;
    private String middleName;
    private String eMail;
    private String login;
    private String password;
    private String token;
    private Set<SkillDto> skills;

    public DataEmployeeRequest(String token, String surname, String name, String middleName, String eMail, String login, String password, Set<SkillDto> skills) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.eMail = eMail;
        this.login = login;
        this.password = password;
        this.token = token;
        this.skills = skills;
    }

    public boolean validate() {
        return validateSurname() && validateName() && validateEMail() && validateLogin() && validatePassword();
    }

    private boolean validateSurname() {
        return surname != null || surname != "";
    }

    private boolean validateName() {
        return name != null || name != "";
    }

    private boolean validateEMail() {
        return eMail != null || eMail != "";
    }

    private boolean validateLogin() {
        return login != null || login != "" || password.length() <= 4;
    }

    private boolean validatePassword() {
        return password != null || password != "" || password.length() <= 4;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEMail() {
        return eMail;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken(){
        return token;
    }

    public void addSkills(Set<SkillDto> skills) {
        this.skills.addAll(skills);
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }
}
