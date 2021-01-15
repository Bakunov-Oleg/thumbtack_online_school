package net.thumbtack.school.hiring.dto.response.employer;

public class DataEmployerResponse {
    private String surname;
    private String name;
    private String middleName;
    private String eMail;
    private String login;
    private String password;
    private String token;
    private String typeUser;

    private String companyName;
    private String adress;

    public DataEmployerResponse(String token, String surname, String name, String middleName, String eMail, String login, String password, String companyName, String adress) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.eMail = eMail;
        this.login = login;
        this.password = password;
        this.token = token;
        this.companyName = companyName;
        this.adress = adress;
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

    public String getToken() {
        return token;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAdress() {
        return adress;
    }

    public Boolean validate() {
        return true;
    }
}