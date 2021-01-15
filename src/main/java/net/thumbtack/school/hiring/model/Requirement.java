package net.thumbtack.school.hiring.model;

public class Requirement extends Skill {
    private boolean required;

    public Requirement(String skillName, Integer skillLevel, Boolean skillIsRequired) {
        super(skillName, skillLevel);
        this.required = skillIsRequired;
    }

    public void setRequired(Boolean required){
        this.required = required;
    }

    public boolean getRequired(){
        return required;
    }
}
