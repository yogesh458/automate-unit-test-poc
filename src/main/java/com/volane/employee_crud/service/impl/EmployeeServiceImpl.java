package com.volane.employee_crud.service.impl;

import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import com.volane.employee_crud.entity.Employee;
import com.volane.employee_crud.exception.DuplicateResourceException;
import com.volane.employee_crud.exception.ResourceNotFoundException;
import com.volane.employee_crud.repository.EmployeeRepository;
import com.volane.employee_crud.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        validateUniqueEmployee(employeeRequestDto);
        Employee employee = mapToEntity(employeeRequestDto);
        return mapToResponseDto(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(Long id) {
        return mapToResponseDto(findEmployeeById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = findEmployeeById(id);
        validateUniqueEmployeeForUpdate(id, employeeRequestDto);

        employee.setEmployeeId(employeeRequestDto.getEmployeeId());
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setSalary(employeeRequestDto.getSalary());

        return mapToResponseDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }

    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private void validateUniqueEmployee(EmployeeRequestDto employeeRequestDto) {
        if (employeeRepository.existsByEmployeeId(employeeRequestDto.getEmployeeId())) {
            throw new DuplicateResourceException("Employee ID already exists: " + employeeRequestDto.getEmployeeId());
        }
        if (employeeRepository.existsByEmail(employeeRequestDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + employeeRequestDto.getEmail());
        }
    }

    private void validateUniqueEmployeeForUpdate(Long id, EmployeeRequestDto employeeRequestDto) {
        if (employeeRepository.existsByEmployeeIdAndIdNot(employeeRequestDto.getEmployeeId(), id)) {
            throw new DuplicateResourceException("Employee ID already exists: " + employeeRequestDto.getEmployeeId());
        }
        if (employeeRepository.existsByEmailAndIdNot(employeeRequestDto.getEmail(), id)) {
            throw new DuplicateResourceException("Email already exists: " + employeeRequestDto.getEmail());
        }
    }

    private Employee mapToEntity(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .employeeId(employeeRequestDto.getEmployeeId())
                .firstName(employeeRequestDto.getFirstName())
                .lastName(employeeRequestDto.getLastName())
                .email(employeeRequestDto.getEmail())
                .department(employeeRequestDto.getDepartment())
                .salary(employeeRequestDto.getSalary())
                .build();
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }
}
