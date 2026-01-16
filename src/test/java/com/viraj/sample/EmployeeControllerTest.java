package com.viraj.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import com.viraj.sample.controller.EmployeeController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void hello_ok() throws Exception {
        mockMvc.perform(get("/employee/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello boot"));
    }

    @Test
    void saveEmployee_ok() throws Exception {
        Employee emp = new Employee("Luis","any");

        when(employeeService.saveEmployee(emp)).thenReturn(emp);

        mockMvc.perform(post("/employee/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Luis"));
    }
}
