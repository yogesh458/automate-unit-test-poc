package com.volane.employee_crud.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import com.volane.employee_crud.entity.Employee;
import com.volane.employee_crud.exception.DuplicateResourceException;
import com.volane.employee_crud.exception.ResourceNotFoundException;
import com.volane.employee_crud.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
   * Test {@link EmployeeServiceImpl#getEmployeeById(Long)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Department is {@code Department}.
   *   <li>Then return EmployeeId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  @DisplayName(
      "Test getEmployeeById(Long); given Employee() Department is 'Department'; then return EmployeeId is '42'")
  @Tag("MaintainedByDiffblue")
  void testGetEmployeeById_givenEmployeeDepartmentIsDepartment_thenReturnEmployeeIdIs42() {
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
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    EmployeeResponseDto actualEmployeeById = employeeServiceImpl.getEmployeeById(1L);

    // Assert
    verify(employeeRepository).findById(1L);
    assertEquals("42", actualEmployeeById.getEmployeeId());
    assertEquals("Department", actualEmployeeById.getDepartment());
    assertEquals("Doe", actualEmployeeById.getLastName());
    assertEquals("Jane", actualEmployeeById.getFirstName());
    assertEquals("jane.doe@example.org", actualEmployeeById.getEmail());
    assertEquals(10.0d, actualEmployeeById.getSalary().doubleValue());
    assertEquals(1L, actualEmployeeById.getId().longValue());
  }

  /**
   * Test {@link EmployeeServiceImpl#getEmployeeById(Long)}.
   *
   * <ul>
   *   <li>Then throw {@link DuplicateResourceException}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  @DisplayName("Test getEmployeeById(Long); then throw DuplicateResourceException")
  @Tag("MaintainedByDiffblue")
  void testGetEmployeeById_thenThrowDuplicateResourceException() {
    // Arrange
    when(employeeRepository.findById(Mockito.<Long>any()))
        .thenThrow(new DuplicateResourceException("An error occurred"));

    // Act and Assert
    assertThrows(DuplicateResourceException.class, () -> employeeServiceImpl.getEmployeeById(1L));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#getEmployeeById(Long)}.
   *
   * <ul>
   *   <li>Then throw {@link ResourceNotFoundException}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
   */
  @Test
  @DisplayName("Test getEmployeeById(Long); then throw ResourceNotFoundException")
  @Tag("MaintainedByDiffblue")
  void testGetEmployeeById_thenThrowResourceNotFoundException() {
    // Arrange
    Optional<Employee> emptyResult = Optional.empty();
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(1L));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#getAllEmployees()}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Department is {@code 42}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  @DisplayName(
      "Test getAllEmployees(); given Employee() Department is '42'; then return size is two")
  @Tag("MaintainedByDiffblue")
  void testGetAllEmployees_givenEmployeeDepartmentIs42_thenReturnSizeIsTwo() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setId(2L);
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);

    ArrayList<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee2);
    employeeList.add(employee);
    when(employeeRepository.findAll()).thenReturn(employeeList);

    // Act
    List<EmployeeResponseDto> actualAllEmployees = employeeServiceImpl.getAllEmployees();

    // Assert
    verify(employeeRepository).findAll();
    assertEquals(2, actualAllEmployees.size());
    EmployeeResponseDto getResult = actualAllEmployees.get(0);
    assertEquals("42", getResult.getDepartment());
    EmployeeResponseDto getResult2 = actualAllEmployees.get(1);
    assertEquals("42", getResult2.getEmployeeId());
    assertEquals("Department", getResult2.getDepartment());
    assertEquals("Doe", getResult2.getLastName());
    assertEquals("Employee Id", getResult.getEmployeeId());
    assertEquals("Jane", getResult2.getFirstName());
    assertEquals("John", getResult.getFirstName());
    assertEquals("Smith", getResult.getLastName());
    assertEquals("jane.doe@example.org", getResult2.getEmail());
    assertEquals("john.smith@example.org", getResult.getEmail());
    assertEquals(0.5d, getResult.getSalary().doubleValue());
    assertEquals(10.0d, getResult2.getSalary().doubleValue());
    assertEquals(1L, getResult2.getId().longValue());
    assertEquals(2L, getResult.getId().longValue());
  }

  /**
   * Test {@link EmployeeServiceImpl#getAllEmployees()}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Department is {@code Department}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  @DisplayName(
      "Test getAllEmployees(); given Employee() Department is 'Department'; then return size is one")
  @Tag("MaintainedByDiffblue")
  void testGetAllEmployees_givenEmployeeDepartmentIsDepartment_thenReturnSizeIsOne() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setId(1L);
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    ArrayList<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee);
    when(employeeRepository.findAll()).thenReturn(employeeList);

    // Act
    List<EmployeeResponseDto> actualAllEmployees = employeeServiceImpl.getAllEmployees();

    // Assert
    verify(employeeRepository).findAll();
    assertEquals(1, actualAllEmployees.size());
    EmployeeResponseDto getResult = actualAllEmployees.get(0);
    assertEquals("42", getResult.getEmployeeId());
    assertEquals("Department", getResult.getDepartment());
    assertEquals("Doe", getResult.getLastName());
    assertEquals("Jane", getResult.getFirstName());
    assertEquals("jane.doe@example.org", getResult.getEmail());
    assertEquals(10.0d, getResult.getSalary().doubleValue());
    assertEquals(1L, getResult.getId().longValue());
  }

  /**
   * Test {@link EmployeeServiceImpl#getAllEmployees()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  @DisplayName("Test getAllEmployees(); then return Empty")
  @Tag("MaintainedByDiffblue")
  void testGetAllEmployees_thenReturnEmpty() {
    // Arrange
    when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

    // Act
    List<EmployeeResponseDto> actualAllEmployees = employeeServiceImpl.getAllEmployees();

    // Assert
    verify(employeeRepository).findAll();
    assertTrue(actualAllEmployees.isEmpty());
  }

  /**
   * Test {@link EmployeeServiceImpl#getAllEmployees()}.
   *
   * <ul>
   *   <li>Then throw {@link DuplicateResourceException}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#getAllEmployees()}
   */
  @Test
  @DisplayName("Test getAllEmployees(); then throw DuplicateResourceException")
  @Tag("MaintainedByDiffblue")
  void testGetAllEmployees_thenThrowDuplicateResourceException() {
    // Arrange
    when(employeeRepository.findAll())
        .thenThrow(new DuplicateResourceException("An error occurred"));

    // Act and Assert
    assertThrows(DuplicateResourceException.class, () -> employeeServiceImpl.getAllEmployees());
    verify(employeeRepository).findAll();
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test updateEmployee(Long, EmployeeRequestDto)")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee() {
    // Arrange
    when(employeeRepository.findById(Mockito.<Long>any()))
        .thenThrow(new DuplicateResourceException("An error occurred"));

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto()));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test updateEmployee(Long, EmployeeRequestDto)")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee2() {
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
    when(employeeRepository.existsByEmployeeIdAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenThrow(new DuplicateResourceException("An error occurred"));
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmployeeIdAndIdNot(null, 1L);
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Given {@link EmployeeRepository} {@link EmployeeRepository#existsByEmailAndIdNot(String,
   *       Long)} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test updateEmployee(Long, EmployeeRequestDto); given EmployeeRepository existsByEmailAndIdNot(String, Long) return 'true'")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee_givenEmployeeRepositoryExistsByEmailAndIdNotReturnTrue() {
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
    when(employeeRepository.existsByEmailAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenReturn(true);
    when(employeeRepository.existsByEmployeeIdAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenReturn(false);
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmailAndIdNot(null, 1L);
    verify(employeeRepository).existsByEmployeeIdAndIdNot(null, 1L);
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Given {@link EmployeeRepository} {@link
   *       EmployeeRepository#existsByEmployeeIdAndIdNot(String, Long)} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test updateEmployee(Long, EmployeeRequestDto); given EmployeeRepository existsByEmployeeIdAndIdNot(String, Long) return 'true'")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee_givenEmployeeRepositoryExistsByEmployeeIdAndIdNotReturnTrue() {
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
    when(employeeRepository.existsByEmployeeIdAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenReturn(true);
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(
        DuplicateResourceException.class,
        () -> employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto()));
    verify(employeeRepository).existsByEmployeeIdAndIdNot(null, 1L);
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Then return EmployeeId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName("Test updateEmployee(Long, EmployeeRequestDto); then return EmployeeId is '42'")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee_thenReturnEmployeeIdIs42() {
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

    Employee employee2 = new Employee();
    employee2.setDepartment("Department");
    employee2.setEmail("jane.doe@example.org");
    employee2.setEmployeeId("42");
    employee2.setFirstName("Jane");
    employee2.setId(1L);
    employee2.setLastName("Doe");
    employee2.setSalary(10.0d);
    when(employeeRepository.existsByEmailAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenReturn(false);
    when(employeeRepository.existsByEmployeeIdAndIdNot(Mockito.<String>any(), Mockito.<Long>any()))
        .thenReturn(false);
    when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    EmployeeResponseDto actualUpdateEmployeeResult =
        employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto());

    // Assert
    verify(employeeRepository).existsByEmailAndIdNot(null, 1L);
    verify(employeeRepository).existsByEmployeeIdAndIdNot(null, 1L);
    verify(employeeRepository).findById(1L);
    verify(employeeRepository).save(isA(Employee.class));
    assertEquals("42", actualUpdateEmployeeResult.getEmployeeId());
    assertEquals("Department", actualUpdateEmployeeResult.getDepartment());
    assertEquals("Doe", actualUpdateEmployeeResult.getLastName());
    assertEquals("Jane", actualUpdateEmployeeResult.getFirstName());
    assertEquals("jane.doe@example.org", actualUpdateEmployeeResult.getEmail());
    assertEquals(10.0d, actualUpdateEmployeeResult.getSalary().doubleValue());
    assertEquals(1L, actualUpdateEmployeeResult.getId().longValue());
  }

  /**
   * Test {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Then throw {@link ResourceNotFoundException}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeServiceImpl#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test updateEmployee(Long, EmployeeRequestDto); then throw ResourceNotFoundException")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee_thenThrowResourceNotFoundException() {
    // Arrange
    Optional<Employee> emptyResult = Optional.empty();
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(
        ResourceNotFoundException.class,
        () -> employeeServiceImpl.updateEmployee(1L, new EmployeeRequestDto()));
    verify(employeeRepository).findById(1L);
  }

  /**
   * Test {@link EmployeeServiceImpl#deleteEmployee(Long)}.
   *
   * <p>Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
   */
  @Test
  @DisplayName("Test deleteEmployee(Long)")
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
  void testDeleteEmployee_thenThrowResourceNotFoundException() {
    // Arrange
    Optional<Employee> emptyResult = Optional.empty();
    when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    verify(employeeRepository).findById(1L);
  }
}
