package net.thumbtack.school.hiring.dto.response.employee;

import net.thumbtack.school.hiring.dto.EmployeeDto;

import java.util.Set;

public class getEmployesDtoResponse {
    private Set<EmployeeDto> employees;

    public getEmployesDtoResponse(Set<EmployeeDto> employees) {
        this.employees = employees;
    }

    public Set<EmployeeDto> getEmployees(){
        return  employees;
    }
}
