package net.thumbtack.school.hiring.dto.response.vacancy;

import com.google.common.collect.TreeMultimap;
import net.thumbtack.school.hiring.dto.VacancyDto;


public class GetVacancySortedByNumberOfSkillsDtoResponce {
    TreeMultimap<Integer, VacancyDto>  vacancyes = TreeMultimap.create(Integer::compareTo, VacancyDto::compareTo);

    public GetVacancySortedByNumberOfSkillsDtoResponce(TreeMultimap<Integer, VacancyDto> vacancyes) {
        this.vacancyes = vacancyes;
    }

    public TreeMultimap<Integer, VacancyDto> getVacancyes() {
        return vacancyes;
    }
}
