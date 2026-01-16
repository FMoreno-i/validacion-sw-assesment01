package com.viraj.sample;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.viraj.sample.service.EmployeeServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void saveEmployee_ok() {
        Employee emp = new Employee("Juan", "any");

        when(employeeRepository.save(emp)).thenReturn(emp);

        Employee result = employeeService.saveEmployee(emp);

        assertThat(result.getEmployeeName()).isEqualTo("Juan");
        verify(employeeRepository).save(emp);
    }

    @Test
    void getEmployee_ok() {
        Employee emp = new Employee("Ana", "any");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployee(1L);

        assertThat(result.getEmployeeName()).isEqualTo("Ana");
    }
}
