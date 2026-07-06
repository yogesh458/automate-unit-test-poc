package com.volane.employee_crud.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import com.volane.employee_crud.exception.GlobalExceptionHandler;
import com.volane.employee_crud.security.handler.SecurityExceptionHandler;
import com.volane.employee_crud.service.EmployeeService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(
    classes = {
      EmployeeController.class,
      GlobalExceptionHandler.class,
      SecurityExceptionHandler.class
    })
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class EmployeeControllerDiffblueTest {
  @Autowired private EmployeeController employeeController;

  @MockBean private EmployeeService employeeService;

  @Autowired private GlobalExceptionHandler globalExceptionHandler;

  @Autowired private SecurityExceptionHandler securityExceptionHandler;

  /**
   * Test {@link EmployeeController#createEmployee(EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code U.U.U}.
   *   <li>Then status {@link StatusResultMatchers#isInternalServerError()}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeController#createEmployee(EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test createEmployee(EmployeeRequestDto); given array of String with 'U.U.U'; then status isInternalServerError()")
  @Tag("MaintainedByDiffblue")
  void testCreateEmployee_givenArrayOfStringWithUUU_thenStatusIsInternalServerError()
      throws Exception {
    // Arrange
    when(employeeService.createEmployee(Mockito.<EmployeeRequestDto>any()))
        .thenReturn(
            EmployeeResponseDto.builder()
                .department("Department")
                .email("jane.doe@example.org")
                .employeeId("42")
                .firstName("Jane")
                .id(1L)
                .lastName("Doe")
                .salary(10.0d)
                .build());

    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/employees");
    postResult.accept("U.U.U");

    EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
    employeeRequestDto.setDepartment("Department");
    employeeRequestDto.setEmail("jane.doe@example.org");
    employeeRequestDto.setEmployeeId("42");
    employeeRequestDto.setFirstName("Jane");
    employeeRequestDto.setLastName("Doe");
    employeeRequestDto.setSalary(10.0d);

    MockHttpServletRequestBuilder requestBuilder =
        postResult
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                JsonMapper.builder()
                    .findAndAddModules()
                    .build()
                    .writeValueAsString(employeeRequestDto));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }

  /**
   * Test {@link EmployeeController#getEmployeeById(Long)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Employee fetched successfully}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeController#getEmployeeById(Long)}
   */
  @Test
  @DisplayName(
      "Test getEmployeeById(Long); given array of String with 'Employee fetched successfully'")
  @Tag("MaintainedByDiffblue")
  void testGetEmployeeById_givenArrayOfStringWithEmployeeFetchedSuccessfully() throws Exception {
    // Arrange
    when(employeeService.getEmployeeById(Mockito.<Long>any()))
        .thenReturn(
            EmployeeResponseDto.builder()
                .department("Department")
                .email("jane.doe@example.org")
                .employeeId("42")
                .firstName("Jane")
                .id(1L)
                .lastName("Doe")
                .salary(10.0d)
                .build());

    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/employees/{id}", 1L);
    requestBuilder.accept("Employee fetched successfully");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }

  /**
   * Test {@link EmployeeController#getAllEmployees()}.
   *
   * <ul>
   *   <li>Then status {@link StatusResultMatchers#isInternalServerError()}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeController#getAllEmployees()}
   */
  @Test
  @DisplayName("Test getAllEmployees(); then status isInternalServerError()")
  @Tag("MaintainedByDiffblue")
  void testGetAllEmployees_thenStatusIsInternalServerError() throws Exception {
    // Arrange
    when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());

    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees");
    requestBuilder.accept("Employees fetched successfully");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }

  /**
   * Test {@link EmployeeController#updateEmployee(Long, EmployeeRequestDto)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code U.U.U}.
   *   <li>When {@link MockMvcRequestBuilders#put(String, Object[])} {@code /api/employees/{id}} one
   *       accept {@code U.U.U}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeController#updateEmployee(Long, EmployeeRequestDto)}
   */
  @Test
  @DisplayName(
      "Test updateEmployee(Long, EmployeeRequestDto); given array of String with 'U.U.U'; when put(String, Object[]) '/api/employees/{id}' one accept 'U.U.U'")
  @Tag("MaintainedByDiffblue")
  void testUpdateEmployee_givenArrayOfStringWithUUU_whenPutApiEmployeesIdOneAcceptUUU()
      throws Exception {
    // Arrange
    when(employeeService.updateEmployee(Mockito.<Long>any(), Mockito.<EmployeeRequestDto>any()))
        .thenReturn(
            EmployeeResponseDto.builder()
                .department("Department")
                .email("jane.doe@example.org")
                .employeeId("42")
                .firstName("Jane")
                .id(1L)
                .lastName("Doe")
                .salary(10.0d)
                .build());

    MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/employees/{id}", 1L);
    putResult.accept("U.U.U");

    EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
    employeeRequestDto.setDepartment("Department");
    employeeRequestDto.setEmail("jane.doe@example.org");
    employeeRequestDto.setEmployeeId("42");
    employeeRequestDto.setFirstName("Jane");
    employeeRequestDto.setLastName("Doe");
    employeeRequestDto.setSalary(10.0d);

    MockHttpServletRequestBuilder requestBuilder =
        putResult
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                JsonMapper.builder()
                    .findAndAddModules()
                    .build()
                    .writeValueAsString(employeeRequestDto));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }

  /**
   * Test {@link EmployeeController#deleteEmployee(Long)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Employee deleted successfully}.
   * </ul>
   *
   * <p>Method under test: {@link EmployeeController#deleteEmployee(Long)}
   */
  @Test
  @DisplayName(
      "Test deleteEmployee(Long); given array of String with 'Employee deleted successfully'")
  @Tag("MaintainedByDiffblue")
  void testDeleteEmployee_givenArrayOfStringWithEmployeeDeletedSuccessfully() throws Exception {
    // Arrange
    doNothing().when(employeeService).deleteEmployee(Mockito.<Long>any());

    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/employees/{id}", 1L);
    requestBuilder.accept("Employee deleted successfully");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }
}
