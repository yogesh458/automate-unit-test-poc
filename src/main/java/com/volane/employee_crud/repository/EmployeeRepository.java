package com.volane.employee_crud.repository;

import com.volane.employee_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmployeeId(String employeeId);

    boolean existsByEmail(String email);

    boolean existsByEmployeeIdAndIdNot(String employeeId, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
