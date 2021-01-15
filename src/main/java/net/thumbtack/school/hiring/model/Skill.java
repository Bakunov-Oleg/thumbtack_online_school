package net.thumbtack.school.hiring.model;

import java.util.Objects;

public class Skill {
    private String name;
    private Integer level;

    public Skill(String skillName, Integer skillLevel) {
        this.name = skillName;
        this.level = skillLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name) &&
                Objects.equals(level, skill.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }


    public static int compare (Skill s1, Skill s2){
        if(!s1.getName().equals(s2.getName())){
            return s1.getName().compareTo(s2.getName());
        } else {
            return s1.getLevel().compareTo(s2.getLevel());
        }
    }
}
