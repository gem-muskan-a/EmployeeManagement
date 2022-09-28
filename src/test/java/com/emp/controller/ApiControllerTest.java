package com.emp.controller;

import com.emp.dto.AddressDto;
import com.emp.dto.DepartmentDto;
import com.emp.dto.EmployeeDto;
import com.emp.service.DeptServiceImpl;
import com.emp.service.EmpServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    private DeptServiceImpl deptService;
    @Mock
    private EmpServiceImpl empService;
    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    @Order(1)
    public void test_getDepartments() throws Exception {
        DepartmentDto dept1 = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);
        DepartmentDto dept2 = new DepartmentDto(2, "MM", "Marketing Managers", 1, new Date(), 1, new Date(), true, false);

        List<DepartmentDto> expectedResult = new ArrayList<>();
        expectedResult.add(dept1);
        expectedResult.add(dept2);

        when(deptService.getAllDepartment()).thenReturn(expectedResult);
        this.mockMvc
                .perform(get("/department/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void test_getDepartment() throws Exception {
        DepartmentDto dept1 = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(deptService.getDepartment(dept1.getDepartmentId())).thenReturn(dept1);
        this.mockMvc
                .perform(get("/department/{departmentId}", dept1.getDepartmentId()))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentName").value("HR"))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentDesc").value("Human Resource"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void test_addDepartment() throws Exception {
        DepartmentDto dept1 = new DepartmentDto(1, "HRA", "Human Resource", 1, new Date(), 1, new Date(), true, false);
        when(deptService.addDepartment(any())).thenReturn(dept1);
        //Converting our Java object to JSON format bcoz MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(dept1);
        System.out.println(jsonbody);
        this.mockMvc
                .perform(
                        post("/department/")
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentName").value("HRA"))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentDesc").value("Human Resource"))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void test_updateDepartment() throws Exception {
        DepartmentDto dept1 = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);
        when(deptService.updateDepartment(anyInt(), any())).thenReturn(dept1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(dept1);
        System.out.println(jsonbody);
        this.mockMvc
                .perform(
                        put("/department/{id}", dept1.getDepartmentId())
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentName").value("HR"))
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentDesc").value("Human Resource"))
                .andDo(print());
    }

    @Test
    @Order(5)
    public void test_deleteDepartment() throws Exception {
        DepartmentDto dept1 = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), false, true);

        when(deptService.deleteDepartment(anyInt())).thenReturn(dept1);

        mockMvc.perform(delete("/department/{id}", dept1.getDepartmentId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath(".deleted").value(true))
                .andDo(print());
    }

    @Test
    @Order(6)
    public void test_getEmployees() throws Exception {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        AddressDto address2 = new AddressDto(2, "Pipraich", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp2 = new EmployeeDto(2, "MUSKAN", "ASE", 1, "7998671271", address2, true, false);

        List<EmployeeDto> expectedResult = new ArrayList<>();
        expectedResult.add(emp1);
        expectedResult.add(emp2);

        when(empService.getAllEmployee()).thenReturn(expectedResult);
        this.mockMvc
                .perform(get("/employee/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(7)
    public void test_getEmployeesByDeptId() throws Exception {
        List<EmployeeDto> expectedResult = new ArrayList<>();

        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        AddressDto address2 = new AddressDto(2, "Pipraich", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp2 = new EmployeeDto(2, "MUSKAN", "ASE", 1, "7998671271", address2, true, false);

        expectedResult.add(emp1);
        expectedResult.add(emp2);

        int deptId = 1;
        when(empService.getEmployeeByDeptId(1)).thenReturn(expectedResult);
        this.mockMvc
                .perform(get("/employee/dept/{deptId}", deptId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(8)
    public void test_getEmployee() throws Exception {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empService.getEmployee(1)).thenReturn(emp1);
        this.mockMvc
                .perform(get("/employee/{id}", 1))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeName").value("MUSKAN"))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeDesignation").value("ASE"))
                .andDo(print());
    }

    @Test
    @Order(9)
    public void test_addEmployee() throws Exception {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empService.addEmployee(any())).thenReturn(emp1);

        //Converting our Java object to JSON format bcoz MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(emp1);

        this.mockMvc
                .perform(
                        post("/employee/")
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeName").value("MUSKAN"))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeDesignation").value("ASE"))
                .andDo(print());
    }

    @Test
    @Order(10)
    public void test_updateEmployee() throws Exception {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empService.updateEmployee(anyInt(), any())).thenReturn(emp1);

        //Converting our Java object to JSON format bcoz MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(emp1);

        this.mockMvc
                .perform(
                        put("/employee/{id}", emp1.getEmployeeId())
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeName").value("MUSKAN"))
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeDesignation").value("ASE"))
                .andDo(print());
    }

    @Test
    @Order(11)
    public void test_deleteEmployee() throws Exception {
        List<Boolean> res=new ArrayList();
        res.add(true);
        res.add(false);

        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto empExpected = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, false, true);
        when(empService.deleteEmployee(1)).thenReturn(empExpected);
        this.mockMvc
                .perform(delete("/employee/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".deleted").value(res))
                .andDo(print());
    }

    @Test
    @Order(12)
    public void test_addEmployeeFail() throws Exception {
        AddressDto address = new AddressDto(1, "", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "", "ASE", 1, "7998671271", address, true, false);

        when(empService.addEmployee(any())).thenReturn(emp1);

        //Converting our Java object to JSON format bcoz MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(emp1);

        this.mockMvc
                .perform(
                        post("/employee/")
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof MethodArgumentNotValidException).isEqualTo(true))
                .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).contains("Please provide employee name having length between 3 and 25"))
                .andDo(print());
    }
}
