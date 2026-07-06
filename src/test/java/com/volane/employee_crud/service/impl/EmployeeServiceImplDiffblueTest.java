package com.volane.employee_crud.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import com.volane.employee_crud.entity.Employee;
import com.volane.employee_crud.exception.DuplicateResourceException;
import com.volane.employee_crud.exception.ResourceNotFoundException;
import com.volane.employee_crud.repository.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplDiffblueTest {
  @MockBean private EmployeeRepository employeeRepository;

  @Autowired private EmployeeServiceImpl employeeServiceImpl;

  /**
   * Test {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test createEmployee(EmployeeRequestDto)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EmployeeResponseDto EmployeeServiceImpl.createEmployee(EmployeeRequestDto)"})
  void testCreateEmployee() {
    // Arrange
    when(employeeRepository.existsByEmployeeId(Mockito.<String>any()))
        .thenThrow(new DuplicateResourceException("An error occurred"));

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.createEmployee(new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmployeeId(null);
  }

  /**
   * Test {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Given {@link EmployeeRepository} {@link EmployeeRepository#existsByEmployeeId(String)}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test createEmployee(EmployeeRequestDto); given EmployeeRepository existsByEmployeeId(String) return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EmployeeResponseDto EmployeeServiceImpl.createEmployee(EmployeeRequestDto)"})
  void testCreateEmployee_givenEmployeeRepositoryExistsByEmployeeIdReturnTrue() {
    // Arrange
    when(employeeRepository.existsByEmployeeId(Mockito.<String>any())).thenReturn(true);

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.createEmployee(new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmployeeId(null);
  }

  /**
   * Test {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Then calls {@link EmployeeRepository#existsByEmail(String)}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test createEmployee(EmployeeRequestDto); then calls existsByEmail(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EmployeeResponseDto EmployeeServiceImpl.createEmployee(EmployeeRequestDto)"})
  void testCreateEmployee_thenCallsExistsByEmail() {
    // Arrange
    when(employeeRepository.existsByEmail(Mockito.<String>any())).thenReturn(true);
    when(employeeRepository.existsByEmployeeId(Mockito.<String>any())).thenReturn(false);

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.createEmployee(new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmail(null);
    verify(employeeRepository).existsByEmployeeId(null);
  }

  /**
   * Test {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Then return EmployeeId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#createEmployee(EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test createEmployee(EmployeeRequestDto); then return EmployeeId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EmployeeResponseDto EmployeeServiceImpl.createEmployee(EmployeeRequestDto)"})
  void testCreateEmployee_thenReturnEmployeeIdIs42() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    employee.setSalary(10.0d);
    when(employeeRepository.existsByEmail(Mockito.<String>any())).thenReturn(false);
    when(employeeRepository.existsByEmployeeId(Mockito.<String>any())).thenReturn(false);
    when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

    // Act
    EmployeeResponseDto actualCreateEmployeeResult =
        employeeServiceImpl.createEmployee(new EmployeeRequestDto());

    // Assert
    verify(employeeRepository).existsByEmail(null);
    verify(employeeRepository).existsByEmployeeId(null);
    verify(employeeRepository).save(isA(Employee.class));
    assertEquals("42", actualCreateEmployeeResult.getEmployeeId());
    assertEquals("Department", actualCreateEmployeeResult.getDepartment());
    assertEquals("Doe", actualCreateEmployeeResult.getLastName());
    assertEquals("Jane", actualCreateEmployeeResult.getFirstName());
    assertEquals("jane.doe@example.org", actualCreateEmployeeResult.getEmail());
    assertEquals(10.0d, actualCreateEmployeeResult.getSalary().doubleValue());
    assertEquals(1L, actualCreateEmployeeResult.getId().longValue());
  }

  /**
   * Test {@link EmployeeServiceImpl#deleteEmployee(Long)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  @DisplayName("Test deleteEmployee(Long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmployeeServiceImpl.deleteEmployee(Long)"})
  void testDeleteEmployee() {
    // Arrange
    when(employeeRepository.findById(Mockito.<Long>any()))
        .thenThrow(new DuplicateResourceException("An error occurred"));

    // Act and Assert
    assertThrows(DuplicateResourceException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#deleteEmployee(Long)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  @DisplayName("Test deleteEmployee(Long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmployeeServiceImpl.deleteEmployee(Long)"})
  void testDeleteEmployee2() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    employee.setSalary(10.0d);
    Optional<Employee> ofResult = Optional.of(employee);
    doThrow(new DuplicateResourceException("An error occurred"))
        .when(employeeRepository)
        .delete(Mockito.<Employee>any());
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(DuplicateResourceException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    verify(employeeRepository).delete(isA(Employee.class));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#deleteEmployee(Long)}.
   *
   * <ul>
   *   <li>Given {@link EmployeeRepository} {@link EmployeeRepository#delete(Object)} does nothing.
   *   <li>Then calls {@link EmployeeRepository#delete(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  @DisplayName(
      "Test deleteEmployee(Long); given EmployeeRepository delete(Object) does nothing; then calls delete(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmployeeServiceImpl.deleteEmployee(Long)"})
  void testDeleteEmployee_givenEmployeeRepositoryDeleteDoesNothing_thenCallsDelete() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    employee.setSalary(10.0d);
    Optional<Employee> ofResult = Optional.of(employee);
    doNothing().when(employeeRepository).delete(Mockito.<Employee>any());
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    employeeServiceImpl.deleteEmployee(1L);

    // Assert
    verify(employeeRepository).delete(isA(Employee.class));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#deleteEmployee(Long)}.
   *
   * <ul>
   *   <li>Then throw {@link ResourceNotFoundException}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  @DisplayName("Test deleteEmployee(Long); then throw ResourceNotFoundException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmployeeServiceImpl.deleteEmployee(Long)"})
  void testDeleteEmployee_thenThrowResourceNotFoundException() {
    // Arrange
    Optional<Employee> emptyResult = Optional.empty();
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    verify(employeeRepository).findById(1L);
  }
}
