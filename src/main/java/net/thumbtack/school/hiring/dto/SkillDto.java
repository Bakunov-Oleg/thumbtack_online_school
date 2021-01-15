package net.thumbtack.school.hiring.dto;

import java.util.Objects;

public class SkillDto {
    private String name;
    private Integer level;

    public SkillDto(String skillName, Integer skillLevel) {
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
        SkillDto skillDto = (SkillDto) o;
        return Objects.equals(name, skillDto.name) &&
                Objects.equals(level, skillDto.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
