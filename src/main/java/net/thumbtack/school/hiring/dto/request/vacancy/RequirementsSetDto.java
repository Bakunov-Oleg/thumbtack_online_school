package net.thumbtack.school.hiring.dto.request.vacancy;

import net.thumbtack.school.hiring.dto.RequirementDto;

import java.util.Set;

public class RequirementsSetDto {
    private Set<RequirementDto> requirements;

    public RequirementsSetDto(Set<RequirementDto> requirements) {
        this.requirements = requirements;
    }

    public Set<RequirementDto> getRequirement() {
        return requirements;
    }

    public void setRequirement(Set<RequirementDto> requirements) {
        this.requirements = requirements;
    }

    public Boolean validate(){
        return true;
    }

}
