package net.thumbtack.school.hiring.model;

import net.thumbtack.school.hiring.TypeUser;

import java.io.Serializable;

public class User {
    private String surname;
    private String name;
    private String middleName;
    private String eMail;
    private String login;
    private String password;
    private TypeUser typeUser;

    public User(String surname, String name, String middleName, String eMail, String login, String password, TypeUser typeUser) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.eMail = eMail;
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeUser(TypeUser typeUser){
        this.typeUser = typeUser;
    }

    public TypeUser getTypeUser(){
        return typeUser;
    }

    public static int compare (User u1, User u2){
        return u1.getLogin().compareTo(u2.getLogin());
    }
}
