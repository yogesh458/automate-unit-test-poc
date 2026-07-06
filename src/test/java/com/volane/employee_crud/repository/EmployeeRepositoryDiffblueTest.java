package com.volane.employee_crud.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.volane.employee_crud.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {EmployeeRepository.class})
@DataJpaTest
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.volane.employee_crud.entity"})
class EmployeeRepositoryDiffblueTest {
  @Autowired private EmployeeRepository employeeRepository;

  /**
   * Test {@link EmployeeRepository#existsByEmployeeId(String)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} EmployeeId is {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmployeeId(String)}
   */
  @Test
  @DisplayName(
      "Test existsByEmployeeId(String); given Employee() EmployeeId is '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmployeeId_givenEmployeeEmployeeIdIs42_thenReturnTrue() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertTrue(employeeRepository.existsByEmployeeId("42"));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmployeeId(String)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} EmployeeId is empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmployeeId(String)}
   */
  @Test
  @DisplayName(
      "Test existsByEmployeeId(String); given Employee() EmployeeId is empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmployeeId_givenEmployeeEmployeeIdIsEmptyString_thenReturnFalse() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertFalse(employeeRepository.existsByEmployeeId("42"));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmail(String)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Email is {@code jane.doe@example.org}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmail(String)}
   */
  @Test
  @DisplayName(
      "Test existsByEmail(String); given Employee() Email is 'jane.doe@example.org'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmail_givenEmployeeEmailIsJaneDoeExampleOrg_thenReturnTrue() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertTrue(employeeRepository.existsByEmail("jane.doe@example.org"));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmail(String)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Email is {@code prof.einstein@example.org}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmail(String)}
   */
  @Test
  @DisplayName(
      "Test existsByEmail(String); given Employee() Email is 'prof.einstein@example.org'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmail_givenEmployeeEmailIsProfEinsteinExampleOrg_thenReturnFalse() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("prof.einstein@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertFalse(employeeRepository.existsByEmail("jane.doe@example.org"));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmployeeIdAndIdNot(String, Long)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} EmployeeId is {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmployeeIdAndIdNot(String, Long)}
   */
  @Test
  @DisplayName(
      "Test existsByEmployeeIdAndIdNot(String, Long); given Employee() EmployeeId is '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmployeeIdAndIdNot_givenEmployeeEmployeeIdIs42_thenReturnTrue() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertTrue(employeeRepository.existsByEmployeeIdAndIdNot("42", 1L));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmployeeIdAndIdNot(String, Long)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmployeeIdAndIdNot(String, Long)}
   */
  @Test
  @DisplayName("Test existsByEmployeeIdAndIdNot(String, Long); then return 'false'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmployeeIdAndIdNot_thenReturnFalse() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertFalse(employeeRepository.existsByEmployeeIdAndIdNot("42", 1L));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmailAndIdNot(String, Long)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Email is {@code jane.doe@example.org}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmailAndIdNot(String, Long)}
   */
  @Test
  @DisplayName(
      "Test existsByEmailAndIdNot(String, Long); given Employee() Email is 'jane.doe@example.org'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmailAndIdNot_givenEmployeeEmailIsJaneDoeExampleOrg_thenReturnTrue() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertTrue(employeeRepository.existsByEmailAndIdNot("jane.doe@example.org", 1L));
  }

  /**
   * Test {@link EmployeeRepository#existsByEmailAndIdNot(String, Long)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#existsByEmailAndIdNot(String, Long)}
   */
  @Test
  @DisplayName("Test existsByEmailAndIdNot(String, Long); then return 'false'")
  @Tag("MaintainedByDiffblue")
  void testExistsByEmailAndIdNot_thenReturnFalse() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("prof.einstein@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertFalse(employeeRepository.existsByEmailAndIdNot("jane.doe@example.org", 1L));
  }

  /**
   * Test {@link EmployeeRepository#count()}.
   *
   * <p>Method under test: {@link EmployeeRepository#count()}
   */
  @Test
  @DisplayName("Test count()")
  @Tag("MaintainedByDiffblue")
  void testCount() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act and Assert
    assertEquals(2L, employeeRepository.count());
  }

  /**
   * Test {@link EmployeeRepository#count(Example)} with {@code Example}.
   *
   * <p>Method under test: {@link EmployeeRepository#count(Example)}
   */
  @Test
  @DisplayName("Test count(Example) with 'Example'")
  @Tag("MaintainedByDiffblue")
  void testCountWithExample() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act and Assert
    assertEquals(1L, employeeRepository.count(example));
  }

  /**
   * Test {@link EmployeeRepository#deleteAll()}.
   *
   * <p>Method under test: {@link EmployeeRepository#deleteAll()}
   */
  @Test
  @DisplayName("Test deleteAll()")
  @Tag("MaintainedByDiffblue")
  void testDeleteAll() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    employeeRepository.deleteAll();

    // Assert
    assertTrue(employeeRepository.findAll().isEmpty());
  }

  /**
   * Test {@link EmployeeRepository#deleteAllByIdInBatch(Iterable)}.
   *
   * <p>Method under test: {@link EmployeeRepository#deleteAllByIdInBatch(Iterable)}
   */
  @Test
  @DisplayName("Test deleteAllByIdInBatch(Iterable)")
  @Tag("MaintainedByDiffblue")
  void testDeleteAllByIdInBatch() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    employeeRepository.deleteAllByIdInBatch(new ArrayList<>());

    // Assert
    List<Employee> findAllResult = employeeRepository.findAll();
    assertEquals(2, findAllResult.size());
    assertSame(employee, findAllResult.get(0));
    assertSame(employee2, findAllResult.get(1));
  }

  /**
   * Test {@link EmployeeRepository#deleteAllInBatch()}.
   *
   * <p>Method under test: {@link EmployeeRepository#deleteAllInBatch()}
   */
  @Test
  @DisplayName("Test deleteAllInBatch()")
  @Tag("MaintainedByDiffblue")
  void testDeleteAllInBatch() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    employeeRepository.deleteAllInBatch();

    // Assert
    assertTrue(employeeRepository.findAll().isEmpty());
  }

  /**
   * Test {@link EmployeeRepository#exists(Example)}.
   *
   * <ul>
   *   <li>Given {@link Employee#Employee()} Department is {@code Department}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#exists(Example)}
   */
  @Test
  @DisplayName(
      "Test exists(Example); given Employee() Department is 'Department'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  void testExists_givenEmployeeDepartmentIsDepartment_thenReturnTrue() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act and Assert
    assertTrue(employeeRepository.exists(example));
  }

  /**
   * Test {@link EmployeeRepository#exists(Example)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#exists(Example)}
   */
  @Test
  @DisplayName("Test exists(Example); then return 'false'")
  @Tag("MaintainedByDiffblue")
  void testExists_thenReturnFalse() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("42");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act and Assert
    assertFalse(employeeRepository.exists(example));
  }

  /**
   * Test {@link EmployeeRepository#findAll()}.
   *
   * <p>Method under test: {@link EmployeeRepository#findAll()}
   */
  @Test
  @DisplayName("Test findAll()")
  @Tag("MaintainedByDiffblue")
  void testFindAll() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    List<Employee> actualFindAllResult = employeeRepository.findAll();

    // Assert
    assertEquals(2, actualFindAllResult.size());
    assertSame(employee, actualFindAllResult.get(0));
    assertSame(employee2, actualFindAllResult.get(1));
  }

  /**
   * Test {@link EmployeeRepository#findAll(Example)} with {@code example}.
   *
   * <p>Method under test: {@link EmployeeRepository#findAll(Example)}
   */
  @Test
  @DisplayName("Test findAll(Example) with 'example'")
  @Tag("MaintainedByDiffblue")
  void testFindAllWithExample() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act
    List<Employee> actualFindAllResult = employeeRepository.findAll(example);

    // Assert
    assertEquals(1, actualFindAllResult.size());
    assertSame(employee, actualFindAllResult.get(0));
  }

  /**
   * Test {@link EmployeeRepository#findAll(Example, Pageable)} with {@code example}, {@code
   * pageable}.
   *
   * <p>Method under test: {@link EmployeeRepository#findAll(Example, Pageable)}
   */
  @Test
  @DisplayName("Test findAll(Example, Pageable) with 'example', 'pageable'")
  @Tag("MaintainedByDiffblue")
  void testFindAllWithExamplePageable() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act
    Page<Employee> actualFindAllResult = employeeRepository.findAll(example, Pageable.unpaged());

    // Assert
    assertTrue(actualFindAllResult instanceof PageImpl);
    List<Employee> toListResult = actualFindAllResult.toList();
    assertEquals(1, toListResult.size());
    assertSame(employee, toListResult.get(0));
  }

  /**
   * Test {@link EmployeeRepository#findAll(Example, Sort)} with {@code example}, {@code sort}.
   *
   * <ul>
   *   <li>When unsorted.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#findAll(Example, Sort)}
   */
  @Test
  @DisplayName(
      "Test findAll(Example, Sort) with 'example', 'sort'; when unsorted; then return size is one")
  @Tag("MaintainedByDiffblue")
  void testFindAllWithExampleSort_whenUnsorted_thenReturnSizeIsOne() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act
    List<Employee> actualFindAllResult = employeeRepository.findAll(example, Sort.unsorted());

    // Assert
    assertEquals(1, actualFindAllResult.size());
    assertSame(employee, actualFindAllResult.get(0));
  }

  /**
   * Test {@link EmployeeRepository#findAll(Pageable)} with {@code pageable}.
   *
   * <p>Method under test: {@link EmployeeRepository#findAll(Pageable)}
   */
  @Test
  @DisplayName("Test findAll(Pageable) with 'pageable'")
  @Tag("MaintainedByDiffblue")
  void testFindAllWithPageable() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    Page<Employee> actualFindAllResult = employeeRepository.findAll(Pageable.unpaged());

    // Assert
    assertTrue(actualFindAllResult instanceof PageImpl);
    List<Employee> toListResult = actualFindAllResult.toList();
    assertEquals(2, toListResult.size());
    assertSame(employee, toListResult.get(0));
    assertSame(employee2, toListResult.get(1));
  }

  /**
   * Test {@link EmployeeRepository#findAll(Sort)} with {@code sort}.
   *
   * <ul>
   *   <li>When unsorted.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeRepository#findAll(Sort)}
   */
  @Test
  @DisplayName("Test findAll(Sort) with 'sort'; when unsorted; then return size is two")
  @Tag("MaintainedByDiffblue")
  void testFindAllWithSort_whenUnsorted_thenReturnSizeIsTwo() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    List<Employee> actualFindAllResult = employeeRepository.findAll(Sort.unsorted());

    // Assert
    assertEquals(2, actualFindAllResult.size());
    assertSame(employee, actualFindAllResult.get(0));
    assertSame(employee2, actualFindAllResult.get(1));
  }

  /**
   * Test {@link EmployeeRepository#findOne(Example)}.
   *
   * <p>Method under test: {@link EmployeeRepository#findOne(Example)}
   */
  @Test
  @DisplayName("Test findOne(Example)")
  @Tag("MaintainedByDiffblue")
  void testFindOne() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setDepartment("Department");
    employee3.setEmail("jane.doe@example.org");
    employee3.setEmployeeId("42");
    employee3.setFirstName("Jane");
    employee3.setLastName("Doe");
    employee3.setSalary(10.0d);
    Example<Employee> example = Example.of(employee3);

    // Act
    Optional<Employee> actualFindOneResult = employeeRepository.findOne(example);

    // Assert
    assertTrue(actualFindOneResult.isPresent());
    assertSame(employee, actualFindOneResult.get());
  }

  /**
   * Test {@link EmployeeRepository#flush()}.
   *
   * <p>Method under test: {@link EmployeeRepository#flush()}
   */
  @Test
  @DisplayName("Test flush()")
  @Tag("MaintainedByDiffblue")
  void testFlush() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    employeeRepository.flush();

    // Assert
    List<Employee> findAllResult = employeeRepository.findAll();
    assertEquals(2, findAllResult.size());
    assertSame(employee, findAllResult.get(0));
    assertSame(employee2, findAllResult.get(1));
  }

  /**
   * Test {@link EmployeeRepository#getOne(Object)}.
   *
   * <p>Method under test: {@link EmployeeRepository#getOne(Object)}
   */
  @Test
  @DisplayName("Test getOne(Object)")
  @Tag("MaintainedByDiffblue")
  void testGetOne() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    Employee employee2 = new Employee();
    employee2.setDepartment("42");
    employee2.setEmail("john.smith@example.org");
    employee2.setEmployeeId("Employee Id");
    employee2.setFirstName("John");
    employee2.setLastName("Smith");
    employee2.setSalary(0.5d);
    employeeRepository.save(employee);
    employeeRepository.save(employee2);

    // Act
    Employee actualOne = employeeRepository.getOne(1L);

    // Assert
    assertEquals(1L, actualOne.getId().longValue());
  }

  /**
   * Test {@link EmployeeRepository#save(Object)}.
   *
   * <p>Method under test: {@link EmployeeRepository#save(Object)}
   */
  @Test
  @DisplayName("Test save(Object)")
  @Tag("MaintainedByDiffblue")
  void testSave() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    // Act
    Employee actualSaveResult = employeeRepository.save(employee);

    // Assert
    assertSame(employee, actualSaveResult);
  }

  /**
   * Test {@link EmployeeRepository#saveAndFlush(Object)}.
   *
   * <p>Method under test: {@link EmployeeRepository#saveAndFlush(Object)}
   */
  @Test
  @DisplayName("Test saveAndFlush(Object)")
  @Tag("MaintainedByDiffblue")
  void testSaveAndFlush() {
    // Arrange
    Employee employee = new Employee();
    employee.setDepartment("Department");
    employee.setEmail("jane.doe@example.org");
    employee.setEmployeeId("42");
    employee.setFirstName("Jane");
    employee.setLastName("Doe");
    employee.setSalary(10.0d);

    // Act
    Employee actualSaveAndFlushResult = employeeRepository.saveAndFlush(employee);

    // Assert
    assertSame(employee, actualSaveAndFlushResult);
  }
}
