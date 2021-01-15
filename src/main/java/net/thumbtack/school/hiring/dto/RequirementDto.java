package net.thumbtack.school.hiring.dto;

public class RequirementDto extends SkillDto{
    private boolean required;

    public RequirementDto(String skillName, Integer skillLevel, Boolean skillIsRequired) {
        super(skillName, skillLevel);
        this.required = skillIsRequired;
    }

    public void setRequired(Boolean required){
        this.required = required;
    }

    public boolean getRequired(){
        return required;
    }

    public static int compare (RequirementDto s1, RequirementDto s2){
        if(!s1.getName().equals(s2.getName())){
            return s1.getName().compareTo(s2.getName());
        } else {
            return s1.getLevel().compareTo(s2.getLevel());
        }
    }

    public Boolean validate (){
        return true;
    }
}
