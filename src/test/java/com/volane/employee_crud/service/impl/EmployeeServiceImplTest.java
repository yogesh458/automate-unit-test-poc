package com.volane.employee_crud.service.impl;

import com.volane.employee_crud.dto.EmployeeRequestDto;
import com.volane.employee_crud.dto.EmployeeResponseDto;
import com.volane.employee_crud.entity.Employee;
import com.volane.employee_crud.exception.DuplicateResourceException;
import com.volane.employee_crud.exception.ResourceNotFoundException;
import com.volane.employee_crud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeServiceImpl Test Suite")
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeRequestDto employeeRequestDto;
    private Employee employee;
    private EmployeeResponseDto employeeResponseDto;

    @BeforeEach
    void setUp() {
        employeeRequestDto = EmployeeRequestDto.builder()
                .employeeId("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .department("IT")
                .salary(50000.0)
                .build();

        employee = Employee.builder()
                .id(1L)
                .employeeId("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .department("IT")
                .salary(50000.0)
                .build();

        employeeResponseDto = EmployeeResponseDto.builder()
                .id(1L)
                .employeeId("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .department("IT")
                .salary(50000.0)
                .build();
    }

    @Nested
    @DisplayName("createEmployee Tests")
    class CreateEmployeeTests {

        @Test
        @Tag("smoke")
        @DisplayName("Should successfully create an employee with valid input")
        void testCreateEmployeeHappyPath() {
            when(employeeRepository.existsByEmployeeId(employeeRequestDto.getEmployeeId()))
                    .thenReturn(false);
            when(employeeRepository.existsByEmail(employeeRequestDto.getEmail()))
                    .thenReturn(false);
            when(employeeRepository.save(any(Employee.class)))
                    .thenReturn(employee);

            EmployeeResponseDto result = employeeService.createEmployee(employeeRequestDto);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("EMP001", result.getEmployeeId());
            assertEquals("John", result.getFirstName());
            assertEquals("Doe", result.getLastName());
            assertEquals("john.doe@example.com", result.getEmail());
            assertEquals("IT", result.getDepartment());
            assertEquals(50000.0, result.getSalary());

            verify(employeeRepository, times(1)).existsByEmployeeId(employeeRequestDto.getEmployeeId());
            verify(employeeRepository, times(1)).existsByEmail(employeeRequestDto.getEmail());
            verify(employeeRepository, times(1)).save(any(Employee.class));
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when employee ID already exists")
        void testCreateEmployeeWithDuplicateEmployeeId() {
            when(employeeRepository.existsByEmployeeId(employeeRequestDto.getEmployeeId()))
                    .thenReturn(true);

            assertThrows(DuplicateResourceException.class,
                    () -> employeeService.createEmployee(employeeRequestDto),
                    "Should throw DuplicateResourceException for duplicate employee ID");

            verify(employeeRepository, times(1)).existsByEmployeeId(employeeRequestDto.getEmployeeId());
            verify(employeeRepository, never()).existsByEmail(anyString());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when email already exists")
        void testCreateEmployeeWithDuplicateEmail() {
            when(employeeRepository.existsByEmployeeId(employeeRequestDto.getEmployeeId()))
                    .thenReturn(false);
            when(employeeRepository.existsByEmail(employeeRequestDto.getEmail()))
                    .thenReturn(true);

            assertThrows(DuplicateResourceException.class,
                    () -> employeeService.createEmployee(employeeRequestDto),
                    "Should throw DuplicateResourceException for duplicate email");

            verify(employeeRepository, times(1)).existsByEmployeeId(employeeRequestDto.getEmployeeId());
            verify(employeeRepository, times(1)).existsByEmail(employeeRequestDto.getEmail());
            verify(employeeRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw NullPointerException when null EmployeeRequestDto is provided")
        void testCreateEmployeeWithNullInput() {
            assertThrows(NullPointerException.class,
                    () -> employeeService.createEmployee(null),
                    "Should throw NullPointerException for null input");
        }

        @Test
        @DisplayName("Should throw NullPointerException when employee ID is null")
        void testCreateEmployeeWithNullEmployeeId() {
            employeeRequestDto.setEmployeeId(null);

            assertThrows(NullPointerException.class,
                    () -> employeeService.createEmployee(employeeRequestDto),
                    "Should throw NullPointerException for null employee ID");
        }

        @Test
        @DisplayName("Should throw NullPointerException when email is null")
        void testCreateEmployeeWithNullEmail() {
            employeeRequestDto.setEmail(null);

            assertThrows(NullPointerException.class,
                    () -> employeeService.createEmployee(employeeRequestDto),
                    "Should throw NullPointerException for null email");
        }
    }

    @Nested
    @DisplayName("getEmployeeById Tests")
    class GetEmployeeByIdTests {

        @Test
        @Tag("smoke")
        @DisplayName("Should successfully retrieve an employee by ID")
        void testGetEmployeeByIdHappyPath() {
            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));

            EmployeeResponseDto result = employeeService.getEmployeeById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("EMP001", result.getEmployeeId());
            assertEquals("John", result.getFirstName());
            assertEquals("Doe", result.getLastName());
            assertEquals("john.doe@example.com", result.getEmail());
            assertEquals("IT", result.getDepartment());
            assertEquals(50000.0, result.getSalary());

            verify(employeeRepository, times(1)).findById(1L);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when employee is not found")
        void testGetEmployeeByIdNotFound() {
            Long nonExistentId = 999L;
            when(employeeRepository.findById(nonExistentId))
                    .thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                    () -> employeeService.getEmployeeById(nonExistentId),
                    "Should throw ResourceNotFoundException when employee not found");

            assertTrue(exception.getMessage().contains("Employee not found with id:"));

            verify(employeeRepository, times(1)).findById(nonExistentId);
        }

        @Test
        @DisplayName("Should throw NullPointerException when null ID is provided")
        void testGetEmployeeByIdWithNullId() {
            when(employeeRepository.findById(null))
                    .thenThrow(new NullPointerException());

            assertThrows(NullPointerException.class,
                    () -> employeeService.getEmployeeById(null),
                    "Should throw NullPointerException for null ID");
        }
    }

    @Nested
    @DisplayName("getAllEmployees Tests")
    class GetAllEmployeesTests {

        @Test
        @Tag("smoke")
        @DisplayName("Should successfully retrieve all employees")
        void testGetAllEmployeesHappyPath() {
            Employee employee2 = Employee.builder()
                    .id(2L)
                    .employeeId("EMP002")
                    .firstName("Jane")
                    .lastName("Smith")
                    .email("jane.smith@example.com")
                    .department("HR")
                    .salary(45000.0)
                    .build();

            when(employeeRepository.findAll())
                    .thenReturn(Arrays.asList(employee, employee2));

            List<EmployeeResponseDto> result = employeeService.getAllEmployees();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("EMP001", result.get(0).getEmployeeId());
            assertEquals("EMP002", result.get(1).getEmployeeId());

            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("Should return empty list when no employees exist")
        void testGetAllEmployeesEmptyList() {
            when(employeeRepository.findAll())
                    .thenReturn(Arrays.asList());

            List<EmployeeResponseDto> result = employeeService.getAllEmployees();

            assertNotNull(result);
            assertEquals(0, result.size());

            verify(employeeRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("Should handle single employee correctly")
        void testGetAllEmployeesWithSingleEmployee() {
            when(employeeRepository.findAll())
                    .thenReturn(Arrays.asList(employee));

            List<EmployeeResponseDto> result = employeeService.getAllEmployees();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("EMP001", result.get(0).getEmployeeId());

            verify(employeeRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("updateEmployee Tests")
    class UpdateEmployeeTests {

        @Test
        @DisplayName("Should successfully update an employee with valid input")
        void testUpdateEmployeeHappyPath() {
            EmployeeRequestDto updateDto = EmployeeRequestDto.builder()
                    .employeeId("EMP001")
                    .firstName("Jonathan")
                    .lastName("Doe-Smith")
                    .email("jonathan.doe@example.com")
                    .department("IT-Management")
                    .salary(60000.0)
                    .build();

            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));
            when(employeeRepository.existsByEmployeeIdAndIdNot("EMP001", 1L))
                    .thenReturn(false);
            when(employeeRepository.existsByEmailAndIdNot("jonathan.doe@example.com", 1L))
                    .thenReturn(false);
            when(employeeRepository.save(any(Employee.class)))
                    .thenReturn(employee);

            EmployeeResponseDto result = employeeService.updateEmployee(1L, updateDto);

            assertNotNull(result);
            assertEquals(1L, result.getId());

            verify(employeeRepository, times(1)).findById(1L);
            verify(employeeRepository, times(1)).existsByEmployeeIdAndIdNot("EMP001", 1L);
            verify(employeeRepository, times(1)).existsByEmailAndIdNot("jonathan.doe@example.com", 1L);
            verify(employeeRepository, times(1)).save(any(Employee.class));
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when employee to update is not found")
        void testUpdateEmployeeNotFound() {
            Long nonExistentId = 999L;
            when(employeeRepository.findById(nonExistentId))
                    .thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                    () -> employeeService.updateEmployee(nonExistentId, employeeRequestDto),
                    "Should throw ResourceNotFoundException when employee not found");

            assertTrue(exception.getMessage().contains("Employee not found with id:"));

            verify(employeeRepository, times(1)).findById(nonExistentId);
            verify(employeeRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when new employee ID already exists for another employee")
        void testUpdateEmployeeWithDuplicateEmployeeId() {
            EmployeeRequestDto updateDto = EmployeeRequestDto.builder()
                    .employeeId("EMP002")
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .department("IT")
                    .salary(50000.0)
                    .build();

            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));
            when(employeeRepository.existsByEmployeeIdAndIdNot("EMP002", 1L))
                    .thenReturn(true);

            assertThrows(DuplicateResourceException.class,
                    () -> employeeService.updateEmployee(1L, updateDto),
                    "Should throw DuplicateResourceException for duplicate employee ID");

            verify(employeeRepository, times(1)).findById(1L);
            verify(employeeRepository, times(1)).existsByEmployeeIdAndIdNot("EMP002", 1L);
            verify(employeeRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when new email already exists for another employee")
        void testUpdateEmployeeWithDuplicateEmail() {
            EmployeeRequestDto updateDto = EmployeeRequestDto.builder()
                    .employeeId("EMP001")
                    .firstName("John")
                    .lastName("Doe")
                    .email("existing.email@example.com")
                    .department("IT")
                    .salary(50000.0)
                    .build();

            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));
            when(employeeRepository.existsByEmployeeIdAndIdNot("EMP001", 1L))
                    .thenReturn(false);
            when(employeeRepository.existsByEmailAndIdNot("existing.email@example.com", 1L))
                    .thenReturn(true);

            assertThrows(DuplicateResourceException.class,
                    () -> employeeService.updateEmployee(1L, updateDto),
                    "Should throw DuplicateResourceException for duplicate email");

            verify(employeeRepository, times(1)).findById(1L);
            verify(employeeRepository, times(1)).existsByEmployeeIdAndIdNot("EMP001", 1L);
            verify(employeeRepository, times(1)).existsByEmailAndIdNot("existing.email@example.com", 1L);
            verify(employeeRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw NullPointerException when null EmployeeRequestDto is provided")
        void testUpdateEmployeeWithNullInput() {
            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));

            assertThrows(NullPointerException.class,
                    () -> employeeService.updateEmployee(1L, null),
                    "Should throw NullPointerException for null input");
        }

        @Test
        @DisplayName("Should throw NullPointerException when null ID is provided")
        void testUpdateEmployeeWithNullId() {
            when(employeeRepository.findById(null))
                    .thenThrow(new NullPointerException());

            assertThrows(NullPointerException.class,
                    () -> employeeService.updateEmployee(null, employeeRequestDto),
                    "Should throw NullPointerException for null ID");
        }
    }

    @Nested
    @DisplayName("deleteEmployee Tests")
    class DeleteEmployeeTests {

        @Test
        @DisplayName("Should successfully delete an employee")
        void testDeleteEmployeeHappyPath() {
            when(employeeRepository.findById(1L))
                    .thenReturn(Optional.of(employee));

            assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));

            verify(employeeRepository, times(1)).findById(1L);
            verify(employeeRepository, times(1)).delete(employee);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when employee to delete is not found")
        void testDeleteEmployeeNotFound() {
            Long nonExistentId = 999L;
            when(employeeRepository.findById(nonExistentId))
                    .thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                    () -> employeeService.deleteEmployee(nonExistentId),
                    "Should throw ResourceNotFoundException when employee not found");

            assertTrue(exception.getMessage().contains("Employee not found with id:"));

            verify(employeeRepository, times(1)).findById(nonExistentId);
            verify(employeeRepository, never()).delete(any());
        }

        @Test
        @DisplayName("Should throw NullPointerException when null ID is provided")
        void testDeleteEmployeeWithNullId() {
            when(employeeRepository.findById(null))
                    .thenThrow(new NullPointerException());

            assertThrows(NullPointerException.class,
                    () -> employeeService.deleteEmployee(null),
                    "Should throw NullPointerException for null ID");
        }
    }
}
