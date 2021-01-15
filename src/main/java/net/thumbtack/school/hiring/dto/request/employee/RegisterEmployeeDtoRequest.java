package net.thumbtack.school.hiring.dto.request.employee;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterEmployeeDtoRequest {
    private String surname;
    private String name;
    private String middleName;
    private String eMail;
    private String login;
    private String password;
    private String token;

    public RegisterEmployeeDtoRequest(String surname, String name, String middleName, String eMail, String login, String password) {
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
        return surname.length() > 0;
    }

    private boolean validateName() {
        return name.length() > 0;
    }

    private boolean validateEMail() {
        Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        Matcher m = p.matcher(eMail);
        boolean isEmail = m.matches();
        return m.matches();
    }

    private boolean validateLogin() {
        return login.length() > 4;
    }

    private boolean validatePassword() {
        return password.length() > 5;
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
}

