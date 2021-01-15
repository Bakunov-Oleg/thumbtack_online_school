package net.thumbtack.school.hiring.model;

import net.thumbtack.school.hiring.TypeUser;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employee extends User {
    private Set<Skill> skills;
    private String token;
    private boolean statusActive;

    public Employee(String surname, String name, String middleName, String eMail, String login, String password, String token) {
        super(surname, name, middleName, eMail, login, password, TypeUser.Employee);
        this.token = token;
        skills = new HashSet<>();
    }

    public void addSkills(Set<Skill> skills) {
        this.skills.addAll(skills);
    }

    public void delSkills(Set<Skill> skills) {
        this.skills.removeAll(skills);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void changeLevelSkills(Set<Skill> skills) {
        Set<Skill> skillsForDel = new HashSet<>();
        for (Skill skill : this.skills) {
            for (Skill newSkill : skills) {
                if (skill.getName().equals(newSkill.getName())) {
                    skillsForDel.add(skill);
                }
            }
        }
        addSkills(skills);
        delSkills(skillsForDel);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static int compare(Employee e1, Employee e2) {
        return e1.getLogin().compareTo(e2.getLogin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(skills, employee.skills) &&
                Objects.equals(token, employee.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, token);
    }

    public int compareTo(Object o) {
        return 0;
    }

    public void setProfileActive() {
        this.statusActive = true;
    }

    public void setProfileNonActive() {
        this.statusActive = false;
    }
}
