package net.thumbtack.school.hiring.dto.response.vacancy;


import net.thumbtack.school.hiring.dto.VacancyDto;

import java.util.ArrayList;
import java.util.List;

public class GetListVacancyDroResponce {
    List<VacancyDto> vacancyes = new ArrayList<>();

    public GetListVacancyDroResponce(List<VacancyDto> vacancyes) {
        this.vacancyes = vacancyes;
    }

    public List<VacancyDto> getVacancyes() {
        return vacancyes;
    }
}
