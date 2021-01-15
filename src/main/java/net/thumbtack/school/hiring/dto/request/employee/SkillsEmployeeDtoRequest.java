package net.thumbtack.school.hiring.dto.request.employee;

// REVU как ни печально, а все же DTO не должен знать про модель
// поэтому лучше private Set<SkillDto> skills;
// то есть сделать еще один класс с теми же полями
// если DTO знает про модель , то при изменении модели изменится и формат запроса от клиента
// а нам точно это надо ?
// запрос от клиента задается внешними требованиями
// а модель - наше внутреннее дело
// аналогично по другим классам DTO, как request, так и response
// в общем, в классах DTO import net.thumbtack.school.hiring.model не должно быть

import net.thumbtack.school.hiring.dto.SkillDto;

import java.util.Set;

public class SkillsEmployeeDtoRequest {
    private Set<SkillDto> skills;
    private String token;

    public SkillsEmployeeDtoRequest(String token, Set<SkillDto> skills) {
        this.skills = skills;
        this.token = token;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public String getToken() {
        return token;
    }

    public boolean validate() {
        for (SkillDto skill : skills) {
            if (skill.getName() == null && skill.getLevel() < 0 && skill.getLevel() > 5) {
                return false;
            }
        }
        return true;
    }
}
