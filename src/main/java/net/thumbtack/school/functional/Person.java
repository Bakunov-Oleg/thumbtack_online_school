package net.thumbtack.school.functional;

import java.util.Optional;

public class Person {

    private String name;
    private Person father;
    private Person mother;
    private Optional<Person> fatherOpt;
    private Optional<Person> motherOpt;
    private int age;


    public Person(String name) {
        this.name = name;
    }

    public Person(Person father, Person mother) {
        this.father = father;
        this.mother = mother;
    }

    public Person(Optional<Person> fatherOpt, Optional<Person> motherOpt) {
        this.fatherOpt = fatherOpt;
        this.motherOpt = motherOpt;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Optional<Person> getFatherOpt() {
        return fatherOpt;
    }

    public void setFatherOpt(Person fatherOpt) {
        this.fatherOpt = Optional.ofNullable(fatherOpt);
    }

    public Optional<Person> getMotherOpt() {
        return motherOpt;
    }

    public void setMotherOpt(Person motherOpt) {
        this.motherOpt = Optional.ofNullable(motherOpt);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
