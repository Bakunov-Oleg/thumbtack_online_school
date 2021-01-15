package net.thumbtack.school.hiring.dto.request.employer;

import java.util.UUID;

public class RegisterEmployerDtoRequest {
    private String companyName;
    private String adress;
    private String eMail;
    private String surname;
    private String name;
    private String middleName;
    private String login;
    private String password;
    private String token;

    public RegisterEmployerDtoRequest(String companyName, String adress, String eMail, String surname, String name, String middleName, String login, String password) {
        this.companyName = companyName;
        this.adress = adress;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.eMail = eMail;
        this.login = login;
        this.password = password;
        this.token = UUID.randomUUID().toString();

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

    public String getToken() {
        return token;
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
}
