package net.thumbtack.school.hiring.dto.request;
import net.thumbtack.school.hiring.dto.SkillDto;

import java.util.Set;

public class SkillSetDto {
    Set<SkillDto> skillsDto;

    public SkillSetDto(Set<SkillDto> skillsDto) {
        this.skillsDto = skillsDto;
    }

    public Set<SkillDto> getSkillsDto() {
        return skillsDto;
    }

    public void setSkillsDto(Set<SkillDto> getSkillsDto) {
        this.skillsDto = getSkillsDto;
    }

    public Boolean validate() {
        return true;
    }
}
