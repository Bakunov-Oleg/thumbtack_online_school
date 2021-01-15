package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.dto.SkillDto;

import java.util.Set;

public class SkillsDtoResponse {
    private Set<SkillDto> skills;

    public SkillsDtoResponse(Set<SkillDto> skills) {
        this.skills = skills;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }
}
