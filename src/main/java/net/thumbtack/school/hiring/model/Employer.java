package net.thumbtack.school.hiring.model;

import net.thumbtack.school.hiring.TypeUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employer extends User {

    private String companyName;
    private String adress;
    private String token;

    private List<Vacancy> vacancies;
    private List<Vacancy> vacanciesActive;
    private List<Vacancy> vacanciesNonActive;

    public Employer(String companyName, String adress, String eMail, String surname, String name, String middleName, String login, String password, String token) {
        super(surname, name, middleName, eMail, login, password, TypeUser.Employer);
        this.companyName = companyName;
        this.adress = adress;
        this.token = token;
        vacancies = new ArrayList<>();
        vacanciesActive = new ArrayList<>();
        vacanciesNonActive = new ArrayList<>();

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
        //vacanciesActive.add(vacancy);
    }

    public void delVacancy(Vacancy vacancy) {
        List<Vacancy> toDel = new ArrayList<>();
        for (Vacancy vacancy1 : vacancies){
            if (vacancy.getVacancyName().equals(vacancy1.getVacancyName())){
                    toDel.add(vacancy1);
            }
        }

        vacancies.removeAll(toDel);
        vacanciesActive.removeAll(toDel);
        vacanciesNonActive.removeAll(toDel);
    }

    public void setVacanciesActive(Vacancy vacancyActive) {
        vacanciesActive.add(vacancyActive);
        vacanciesNonActive.remove(vacancyActive);
    }

    public void setVacanciesNonActive(Vacancy vacancyNonActive) {
        vacanciesActive.remove(vacancyNonActive);
        vacanciesNonActive.add(vacancyNonActive);
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public List<Vacancy> getVacanciesActive() {
        return vacanciesActive;
    }

    public List<Vacancy> getVacanciesNonActive() {
        return vacanciesNonActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(adress, employer.adress) &&
                Objects.equals(token, employer.token) &&
                Objects.equals(vacancies, employer.vacancies) &&
                Objects.equals(vacanciesActive, employer.vacanciesActive) &&
                Objects.equals(vacanciesNonActive, employer.vacanciesNonActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, adress, token, vacancies, vacanciesActive, vacanciesNonActive);
    }
}
