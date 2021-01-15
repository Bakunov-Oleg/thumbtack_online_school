package net.thumbtack.school.hiring.dto.response.vacancy;

import net.thumbtack.school.hiring.dto.VacancyDto;

import java.util.HashSet;
import java.util.Set;

public class GetVacancyDtoResponce {
    Set<VacancyDto> vacancyes = new HashSet<>();

    public GetVacancyDtoResponce(Set<VacancyDto> vacancyes) {
        this.vacancyes = vacancyes;
    }

    public Set<VacancyDto> getVacancyes() {
        return vacancyes;
    }
}
