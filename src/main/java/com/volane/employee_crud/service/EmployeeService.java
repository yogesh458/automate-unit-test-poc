package com.volane.employee_crud.service;

import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(Long id);
}
