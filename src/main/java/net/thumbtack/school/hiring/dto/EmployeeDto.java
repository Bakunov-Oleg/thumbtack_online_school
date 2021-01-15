package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.TypeUser;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EmployeeDto extends UserDto{
    private Set<SkillDto> skills;
    private String token;

    public EmployeeDto(String surname, String name, String middleName, String eMail, String login, String password, String token) {
        super(surname, name, middleName, eMail, login, password, TypeUser.Employee);
        this.token = token;
        skills = new HashSet<>();
    }

    public void addSkills(Set<SkillDto> skills) {
        this.skills.addAll(skills);
    }

    public void delSkills(Set<SkillDto> skills) {
        this.skills.removeAll(skills);
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public void changeLevelSkills(Set<SkillDto> skills) {
        delSkills(skills);
        addSkills(skills);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static int compare(EmployeeDto e1, EmployeeDto e2) {
        return e1.getLogin().compareTo(e2.getLogin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(skills, that.skills) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, token);
    }
}
